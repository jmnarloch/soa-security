/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.fault;

import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.service.ServiceType;
import org.soa.security.core.service.error.ServiceErrorHandler;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>An error handler that creates a soap fault with information regarding the nature of the error.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class SoapFaultErrorHandler implements ServiceErrorHandler {

    /**
     * <p>Creates new instance of {@link SoapFaultErrorHandler} class.</p>
     */
    public SoapFaultErrorHandler() {
        // empty constructor
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
    public void handleError(Exception exc, HttpResponse httpResponse) {

        SOAPMessage message = null;
        QName faultCode = new QName("SERVER");

        try {
            message = createFaultMessage(faultCode, exc);

            writeMessage(message, httpResponse);
        } catch (SOAPException e) {
            // ignores exception
        } catch (IOException e) {
            // ignores exception
        }
    }

    /**
     * <p>Creates a fault soap message out of the exception.</p>
     *
     * @param faultCode the fault code
     * @param exc       the exception
     *
     * @return the soap message
     *
     * @throws SOAPException if any error occurs
     */
    private SOAPMessage createFaultMessage(QName faultCode, Exception exc) throws SOAPException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();

        message.getSOAPBody().addFault(faultCode, printException(exc));

        return message;
    }

    /**
     * <p>Writes the message to response stream.</p>
     *
     * @param message      the fault message
     * @param httpResponse the response stream
     *
     * @throws IOException   if any error occurs when writing the message to output stream
     * @throws SOAPException if any error occurs when writing the soap message
     */
    private void writeMessage(SOAPMessage message, HttpResponse httpResponse) throws IOException, SOAPException {

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

    /**
     * <p>Returns the exception message.</p>
     *
     * @param exc {@link Exception} to use
     *
     * @return the exception message
     */
    private static String printException(Throwable exc) {

        return exc.getMessage();
    }
}
