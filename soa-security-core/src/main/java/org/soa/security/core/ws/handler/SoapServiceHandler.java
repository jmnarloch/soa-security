/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.handler;

import org.soa.security.core.Helper;
import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.http.HttpRequest;
import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.interceptor.MessageInterceptionContext;
import org.soa.security.core.interceptor.MessageInterceptionException;
import org.soa.security.core.interceptor.MessageInterceptor;
import org.soa.security.core.service.Service;
import org.soa.security.core.service.ServiceType;
import org.soa.security.core.service.client.ServiceClientException;
import org.soa.security.core.service.handler.ServiceHandler;
import org.soa.security.core.ws.SoapMessage;
import org.soa.security.core.ws.client.SoapServiceClient;
import org.soa.security.core.ws.wsdl.WsdlPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>A web service handler that process the incoming message and dispatches it to a target service.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class SoapServiceHandler implements ServiceHandler {

    /**
     * <p>Represents the service client.</p>
     */
    @Autowired
    private SoapServiceClient serviceClient;

    /**
     * <p>Represents the wsdl publisher, which retrieves and modifies the contract of the remote service.</p>
     */
    @Autowired
    private WsdlPublisher wsdlPublisher;

    /**
     * <p>Creates new instance of {@link SoapServiceHandler} class.</p>
     */
    public SoapServiceHandler() {
        // empty constructor
    }

    /**
     * <p>Validates that the class has been correctly initialized.</p>
     *
     * @throws IllegalStateException if one of the required fields hasn't been set
     */
    @PostConstruct
    public void init() {

        Helper.checkStateNotNull(serviceClient, "serviceClient");
        Helper.checkStateNotNull(wsdlPublisher, "wsdlPublisher");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceType getSupportedServiceType() {
        return ServiceType.SOAP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleRequest(Service service, HttpRequest httpRequest, HttpResponse httpResponse)
            throws ServiceExecutionException {

        if (isWsdlRequest(httpRequest)) {

            // for WSDL requests returns the modified WSDL
            processWsdlRequest(service, httpResponse);
        } else {

            // for service requests calls the remote service
            processServiceRequest(service, httpRequest, httpResponse);
        }
    }

    /**
     * <p>Processes the incoming request and dispatches it to the target service.</p>
     *
     * @param service      the target service
     * @param httpRequest  the http request
     * @param httpResponse the http response
     *
     * @throws ServiceExecutionException if any error occurs
     */
    private void processServiceRequest(Service service, HttpRequest httpRequest, HttpResponse httpResponse)
            throws ServiceExecutionException {

        try {
            // creates initial message that was send to the service
            SoapMessage initialMessage = createMessage(httpRequest);

            // process the inbound message
            SoapMessage inboundMessage = processInboundMessage(service, initialMessage);

            // calls the remote service and handles
            SoapMessage outboundMessage = callService(service, inboundMessage);

            // process the outbound message
            SoapMessage response = processOutboundMessage(service, outboundMessage);

            // writes the message to response
            writeResponse(response, httpResponse);
        } catch (IOException e) {

            throw new ServiceExecutionException("An error occurred when processing the response.", e);
        }
    }

    /**
     * <p>Creates a soap message from the http request.</p>
     *
     * @param httpRequest the http request
     *
     * @return the created soap message
     *
     * @throws ServiceExecutionException if any error occurs
     */
    private SoapMessage createMessage(HttpRequest httpRequest) throws ServiceExecutionException {

        // creates SAAJ soap message from the request
        InputStream input = null;

        try {
            input = httpRequest.getInputStream();
            MessageFactory messageFactory = MessageFactory.newInstance();
            return new SoapMessage(messageFactory.createMessage(null, input));
        } catch (SOAPException e) {

            throw new ServiceExecutionException("The soap message could not be created.", e);
        } catch (IOException e) {

            throw new ServiceExecutionException("The soap message could not be created.", e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                // ignores exception
            }
        }
    }

    /**
     * <p>Processes the inbound message.</p>
     *
     * @param service the service
     * @param message the inbound message
     *
     * @return the message that is result of processing
     *
     * @throws MessageInterceptionException if any error occurs when intercepting a message
     */
    private SoapMessage processInboundMessage(Service service, SoapMessage message) throws MessageInterceptionException {

        // gets the list of service interceptors
        List<MessageInterceptor> messageInterceptors = getInboundMessageInterceptors(service);

        // creates the interception context
        MessageInterceptionContext<SoapMessage> messageInterceptionContext =
                new MessageInterceptionContext<SoapMessage>(service, message);

        // for each interceptor
        for (MessageInterceptor<SoapMessage> interceptor : messageInterceptors) {

            // intercepts the inbound message
            interceptor.interceptInboundMessage(messageInterceptionContext);
        }

        // returns the result message
        return messageInterceptionContext.getMessage();
    }

    /**
     * <p>Processes outbound message.</p>
     *
     * @param service the target service
     * @param message the outbound message
     *
     * @return the message that is result of processing
     *
     * @throws MessageInterceptionException if any error occurs when intercepting a message
     */
    private SoapMessage processOutboundMessage(Service service, SoapMessage message)
            throws MessageInterceptionException {

        // gets the list of service interceptors
        List<MessageInterceptor> messageInterceptors = getOutboundMessageInterceptors(service);

        // creates the interception context
        MessageInterceptionContext<SoapMessage> messageInterceptionContext =
                new MessageInterceptionContext<SoapMessage>(service, message);

        // for each interceptor, in reverse order
        for (int index = messageInterceptors.size() - 1; index >= 0; index--) {

            MessageInterceptor<SoapMessage> interceptor = messageInterceptors.get(index);

            // intercepts the outbound message
            interceptor.interceptOutboundMessage(messageInterceptionContext);
        }

        // returns the result message
        return messageInterceptionContext.getMessage();
    }

    /**
     * <p>Calls the remote service.</p>
     *
     * @param service the remote service
     * @param message the message
     *
     * @return result of the invocation
     *
     * @throws ServiceClientException if any error occurs when calling remote service
     */
    private SoapMessage callService(Service service, SoapMessage message) throws ServiceClientException {

        return serviceClient.sendMessage(service.getEndpointUrl(), message);
    }

    /**
     * <p>Retrieves the list of inbound interceptors.</p>
     *
     * @param service the service
     *
     * @return the list of inbound service interceptors
     */
    private List<MessageInterceptor> getInboundMessageInterceptors(Service service) {

        return service.getMessageInterceptors();
    }

    /**
     * <p>Retrieves the list of outbound interceptors.</p>
     *
     * @param service the service
     *
     * @return the list of outbound service interceptors
     */
    private List<MessageInterceptor> getOutboundMessageInterceptors(Service service) {

        return service.getMessageInterceptors();
    }

    /**
     * <p>Processes the wsdl request.</p>
     *
     * @param service      the service
     * @param httpResponse the http response
     *
     * @throws ServiceExecutionException if any error occurs when processing wsdl request
     */
    private void processWsdlRequest(Service service, HttpResponse httpResponse) throws ServiceExecutionException {

        try {
            // publishes the wsdl
            String wsdl = wsdlPublisher.publishWsdl(service);

            // writes the wsdl to the output
            writeWsdl(wsdl, httpResponse);

        } catch (IOException e) {

            throw new ServiceExecutionException("An error occurred when writing the wsdl.");
        }
    }

    /**
     * <p>Writes the wsdl to the response.</p>
     *
     * @param wsdl         the service wsdl
     * @param httpResponse the http response
     *
     * @throws IOException if any error occurs when writing the wsdl
     */
    private void writeWsdl(String wsdl, HttpResponse httpResponse) throws IOException {

        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(httpResponse.getOutputStream());
            printWriter.print(wsdl);
            printWriter.flush();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    /**
     * <p>Whether the incoming request is request to service WSDL.</p>
     *
     * @param httpRequest the http request
     *
     * @return true if incoming request is request to WSDL
     */
    private boolean isWsdlRequest(HttpRequest httpRequest) {
        Pattern pattern = Pattern.compile("[.?]WSDL$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(httpRequest.getRequestUrl());

        return matcher.find();
    }

    /**
     * <p>Writes the message to the response stream.</p>
     *
     * @param message      the message
     * @param httpResponse the http response
     */
    private void writeResponse(SoapMessage message, HttpResponse httpResponse) throws IOException {

        OutputStream outputStream = null;

        try {
            outputStream = httpResponse.getOutputStream();
            message.writeTo(outputStream);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException exc) {
                    // ignores exception
                }
            }
        }
    }
}

