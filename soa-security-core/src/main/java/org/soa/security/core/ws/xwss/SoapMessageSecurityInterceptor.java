/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.xwss;

import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;
import com.sun.xml.wss.XWSSecurityException;
import org.soa.security.core.interceptor.MessageInterceptionContext;
import org.soa.security.core.interceptor.MessageInterceptionException;
import org.soa.security.core.interceptor.MessageInterceptor;
import org.soa.security.core.service.Service;
import org.soa.security.core.ws.SoapMessage;

import javax.security.auth.callback.CallbackHandler;
import java.io.InputStream;

/**
 * <p>A soap message security interceptor.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class SoapMessageSecurityInterceptor implements MessageInterceptor<SoapMessage> {

    /**
     * <p>Creates new instance of {@link SoapMessageSecurityInterceptor} class.</p>
     */
    public SoapMessageSecurityInterceptor() {

        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interceptInboundMessage(MessageInterceptionContext<SoapMessage> interceptionContext)
            throws MessageInterceptionException {

        try {
            // creates the security processor
            XWSSProcessor xwssProcessor = createProcessorFactory(interceptionContext.getService());

            // validates the message
            validateMessage(xwssProcessor, interceptionContext);
        } catch (XWSSecurityException e) {

            throw new MessageInterceptionException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interceptOutboundMessage(MessageInterceptionContext<SoapMessage> interceptionContext)
            throws MessageInterceptionException {

        try {
            // creates the security processor
            XWSSProcessor xwssProcessor = createProcessorFactory(interceptionContext.getService());

            // secures the message
            secureMessage(xwssProcessor, interceptionContext);
        } catch (XWSSecurityException e) {

            throw new MessageInterceptionException("An error occurred when validating the message.", e);
        }
    }

    /**
     * <p>Validates the message using specified security rules.</p>
     *
     * @param xwssProcessor       the security processor
     * @param interceptionContext the interception context
     *
     * @throws XWSSecurityException if any error occurs
     */
    private void validateMessage(XWSSProcessor xwssProcessor,
                                 MessageInterceptionContext<SoapMessage> interceptionContext)
            throws XWSSecurityException {

        ProcessingContext processingContext = xwssProcessor.createProcessingContext(
                interceptionContext.getMessage().getSaajSoapMessage());

        SoapMessage result = new SoapMessage(xwssProcessor.verifyInboundMessage(processingContext));

        interceptionContext.setMessage(result);
    }

    /**
     * <p>Secures the message using specified security rules.</p>
     *
     * @param xwssProcessor       the security processor
     * @param interceptionContext the interception context
     *
     * @throws XWSSecurityException if any error occurs
     */
    private void secureMessage(XWSSProcessor xwssProcessor, MessageInterceptionContext<SoapMessage> interceptionContext)
            throws XWSSecurityException {

        ProcessingContext processingContext = xwssProcessor.createProcessingContext(
                interceptionContext.getMessage().getSaajSoapMessage());

        SoapMessage result = new SoapMessage(xwssProcessor.secureOutboundMessage(processingContext));

        interceptionContext.setMessage(result);
    }

    /**
     * <p>Creates xwss security processor for handling the service message security.</p>
     *
     * @param service the service
     *
     * @return the service xwss security processor
     *
     * @throws XWSSecurityException if any error occurs when creating the processor
     */
    private XWSSProcessor createProcessorFactory(Service service) throws XWSSecurityException {

        InputStream securityPolicy = getServiceSecurityPolity(service);
        CallbackHandler callbackHandler = getServiceCallbackHandler(service);

        XWSSProcessorFactory processorFactory = XWSSProcessorFactory.newInstance();
        return processorFactory.createProcessorForSecurityConfiguration(securityPolicy, callbackHandler);
    }

    /**
     * <p>Retrieves the security policy for the given service.</p>
     *
     * @param service the service
     *
     * @return the service security policy
     */
    private InputStream getServiceSecurityPolity(Service service) {

        return service.getSecurityPolicy();
    }

    /**
     * <p>Retrieves the service security callback handler.</p>
     *
     * @param service the service
     *
     * @return the service security callback handler
     */
    private CallbackHandler getServiceCallbackHandler(Service service) {

        return service.getCallbackHandler();
    }
}
