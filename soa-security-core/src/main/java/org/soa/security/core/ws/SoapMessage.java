/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws;

import org.soa.security.core.Message;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Represents the SOAP message.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class SoapMessage implements Message {

    /**
     * <p>Retrieves the SAAJ soap message.</p>
     */
    private SOAPMessage saajSoapMessage;

    /**
     * <p>Creates new instance of {@link SoapMessage} class.</p>
     *
     * @param saajSoapMessage the SAAJ soap message
     */
    public SoapMessage(SOAPMessage saajSoapMessage) {
        this.saajSoapMessage = saajSoapMessage;
    }

    /**
     * <p>Retrieves the SAAJ soap message.</p>
     *
     * @return the SAAJ soap message
     */
    public SOAPMessage getSaajSoapMessage() {
        return saajSoapMessage;
    }

    /**
     * <p>Sets the SAAJ soap message.</p>
     *
     * @param saajSoapMessage the SAAJ soap message
     */
    public void setSaajSoapMessage(SOAPMessage saajSoapMessage) {
        this.saajSoapMessage = saajSoapMessage;
    }

    /**
     * <p>Writes the output stream.</p>
     *
     * @param outputStream the output stream
     *
     * @throws IOException if any error occurs when writing the response
     */
    public void writeTo(OutputStream outputStream) throws IOException {

        try {
            saajSoapMessage.writeTo(outputStream);
        } catch (SOAPException e) {
            throw new IOException("Could not write the soap message.", e);
        }
    }
}
