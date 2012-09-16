/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.client;

import org.soa.security.core.Message;

/**
 * <p>Represents a client of a remote service.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceClient<T extends Message> {

    /**
     * <p>Performs call of a remote service.</p>
     *
     * @param serviceUrl the service url
     * @param message    the message
     *
     * @return the service invocation result
     *
     * @throws ServiceClientException if any error occurs when executing the remote service
     */
    public T sendMessage(String serviceUrl, T message) throws ServiceClientException;
}
