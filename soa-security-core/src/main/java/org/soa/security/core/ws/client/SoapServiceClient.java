/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.client;

import org.soa.security.core.service.client.ServiceClient;
import org.soa.security.core.service.client.ServiceClientException;
import org.soa.security.core.ws.SoapMessage;
import org.springframework.stereotype.Component;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

/**
 * <p>A service client for executing web services and returning it's result.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class SoapServiceClient implements ServiceClient<SoapMessage> {

    /**
     * <p>Creates new instance of {@link SoapServiceClient}.</p>
     */
    public SoapServiceClient() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SoapMessage sendMessage(String serviceUrl, SoapMessage message) throws ServiceClientException {

        try {
            SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = factory.createConnection();

            return new SoapMessage(connection.call(message.getSaajSoapMessage(), new URL(serviceUrl)));
        } catch (MalformedURLException e) {

            throw new ServiceClientException(MessageFormat.format(
                    "An error occurred when executing remote service: {0}.", serviceUrl), e);
        } catch (SOAPException e) {

            throw new ServiceClientException(MessageFormat.format(
                    "An error occurred when executing remote service: {0}.", serviceUrl), e);
        }
    }
}
