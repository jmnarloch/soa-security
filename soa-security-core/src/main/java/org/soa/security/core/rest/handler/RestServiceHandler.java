/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.rest.handler;

import org.soa.security.core.Helper;
import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.http.HttpHeader;
import org.soa.security.core.http.HttpMethod;
import org.soa.security.core.http.HttpRequest;
import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.http.impl.HttpHeaderImpl;
import org.soa.security.core.interceptor.MessageInterceptionContext;
import org.soa.security.core.interceptor.MessageInterceptionException;
import org.soa.security.core.interceptor.MessageInterceptor;
import org.soa.security.core.rest.RestMessage;
import org.soa.security.core.rest.client.RestServiceClient;
import org.soa.security.core.service.Service;
import org.soa.security.core.service.ServiceType;
import org.soa.security.core.service.client.ServiceClientException;
import org.soa.security.core.service.handler.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>A REST service handler that process the incoming message and delegates it to target service.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class RestServiceHandler implements ServiceHandler {

    /**
     * <p>Represents the service client.</p>
     */
    @Autowired
    private RestServiceClient serviceClient;

    /**
     * <p>Creates new instance of {@link RestServiceHandler} class.</p>
     */
    public RestServiceHandler() {
        // empty constructor
    }

    /**
     * <p>Validates that all required properties has been autowired.</p>
     *
     * @throws IllegalStateException if any required property is missing
     */
    @PostConstruct
    protected void init() {

        Helper.checkStateNotNull(serviceClient, "serviceClient");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceType getSupportedServiceType() {
        return ServiceType.REST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleRequest(Service service, HttpRequest httpRequest, HttpResponse httpResponse) throws ServiceExecutionException {

        // simply proceeds the request to the target service
        processServiceRequest(service, httpRequest, httpResponse);
    }

    /**
     * <p>Processes the incoming request and dispatches it to the target service.</p>
     *
     * @param service      the target service
     * @param httpRequest  the http request
     * @param httpResponse the http response
     *
     * @throws ServiceExecutionException if any error occurs when processing the request
     */
    private void processServiceRequest(Service service, HttpRequest httpRequest, HttpResponse httpResponse)
            throws ServiceExecutionException {

        try {
            // creates initial message that was send to the service
            RestMessage initialMessage = createMessage(service, httpRequest);

            // process the inbound message
            RestMessage inboundMessage = processInboundMessage(service, initialMessage);

            // calls the remote service and handles
            RestMessage outboundMessage = callService(service, inboundMessage);

            // process the outbound message
            RestMessage response = processOutboundMessage(service, outboundMessage);

            // writes the message to response
            writeResponse(response, httpResponse);
        } catch (IOException e) {

            throw new ServiceExecutionException("An error occurred when processing the response.", e);
        }
    }

    /**
     * <p>Creates a new REST message out of the request.</p>
     *
     * @param service     the service
     * @param httpRequest the http request
     *
     * @return the create rest message
     *
     * @throws IOException if any error occurs
     */
    private RestMessage createMessage(Service service, HttpRequest httpRequest) throws IOException {

        return new RestMessage(service.getEndpointUrl(), getRequestUrl(httpRequest), httpRequest.getMethod(),
                httpRequest.getHeaders(), httpRequest.getInputStream());
    }

    /**
     * <p>Processes the inbound message.</p>
     *
     * @param service the service
     * @param message the message
     *
     * @return the message that is the result of processing
     *
     * @throws MessageInterceptionException if any error occurs during processing
     */
    private RestMessage processInboundMessage(Service service, RestMessage message) throws MessageInterceptionException {

        // gets the list of service interceptors
        List<MessageInterceptor> messageInterceptors = getInboundMessageInterceptors(service);

        // creates the interception context
        MessageInterceptionContext<RestMessage> messageInterceptionContext =
                new MessageInterceptionContext<RestMessage>(service, message);

        // for each interceptor
        for (MessageInterceptor<RestMessage> interceptor : messageInterceptors) {

            // intercepts the inbound message
            interceptor.interceptInboundMessage(messageInterceptionContext);
        }

        // returns the result message
        return messageInterceptionContext.getMessage();
    }

    /**
     * <p>Processes the outbound message.</p>
     *
     * @param service the service
     * @param message the message
     *
     * @return the message that is result of processing
     *
     * @throws MessageInterceptionException if any error occurs during processing
     */
    private RestMessage processOutboundMessage(Service service, RestMessage message) throws MessageInterceptionException {

        // gets the list of service interceptors
        List<MessageInterceptor> messageInterceptors = getOutboundMessageInterceptors(service);

        // creates the interception context
        MessageInterceptionContext<RestMessage> messageInterceptionContext =
                new MessageInterceptionContext<RestMessage>(service, message);

        // for each interceptor, in reverse order
        for (int index = messageInterceptors.size() - 1; index >= 0; index--) {

            MessageInterceptor<RestMessage> interceptor = messageInterceptors.get(index);

            // intercepts the outbound message
            interceptor.interceptOutboundMessage(messageInterceptionContext);
        }

        // returns the result message
        return messageInterceptionContext.getMessage();
    }

    /**
     * <p>Calls the target service.</p>
     *
     * @param service the service
     * @param message the message
     *
     * @return the result of the invocation
     *
     * @throws ServiceClientException if any error occurs when calling remote service
     */
    private RestMessage callService(Service service, RestMessage message) throws ServiceClientException {

        return serviceClient.sendMessage(combineRequestUrl(service.getEndpointUrl(), message.getRequestUrl()), message);
    }

    /**
     * <p>Retrieves the list of inbound message interceptors.</p>
     *
     * @param service the service
     *
     * @return the list of message interceptors
     */
    private List<MessageInterceptor> getInboundMessageInterceptors(Service service) {

        return service.getMessageInterceptors();
    }

    /**
     * <p>Retrieves the list of outbound message interceptors.</p>
     *
     * @param service the service
     *
     * @return the list of message interceptors
     */
    private List<MessageInterceptor> getOutboundMessageInterceptors(Service service) {

        return service.getMessageInterceptors();
    }

    /**
     * <p>Writes the content of the message to the response stream.</p>
     *
     * @param message      the message
     * @param httpResponse the http response
     *
     * @throws IOException if any error occurs
     */
    private void writeResponse(RestMessage message, HttpResponse httpResponse) throws IOException {

        OutputStream outputStream = null;

        try {
            copyHeaders(httpResponse, message.getHttpHeaders());

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

    /**
     * <p>Copies the http headers into the output.</p>
     *
     * @param httpResponse the http response
     * @param httpHeaders the http headers
     */
    private void copyHeaders(HttpResponse httpResponse, List<HttpHeader> httpHeaders) {

        if(httpHeaders != null && httpHeaders.size() > 0) {

            for(HttpHeader httpHeader : httpHeaders) {

                httpResponse.setHttpHeader(new HttpHeaderImpl(httpHeader.getName(), httpHeader.getValue()));
            }
        }
    }

    /**
     * <p>Returns the actual request url for the given service.</p>
     *
     * @param httpRequest the http request
     *
     * @return the request url
     */
    public String getRequestUrl(HttpRequest httpRequest) {

        String requestUrl;
        int index = -1;

        requestUrl = httpRequest.getRequestUrl();

        for(int i = 0; i < 3; i++) {

            index = requestUrl.indexOf("/", index + 1);
        }

        // otherwise the whole request contains only the service name
        if (index == -1) {
            index = requestUrl.length();
        }

        return requestUrl.substring(index);
    }

    /**
     * <p>Combines the request parts.</p>
     *
     * @param endpointUrl the service endpoint url
     * @param requestUrl  the request url
     *
     * @return the combined service url
     */
    private String combineRequestUrl(String endpointUrl, String requestUrl) {

        StringBuilder stringBuilder = new StringBuilder(endpointUrl);
        if (stringBuilder.charAt(stringBuilder.length() - 1) == '/') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        stringBuilder.append(requestUrl);

        return stringBuilder.toString();
    }
}
