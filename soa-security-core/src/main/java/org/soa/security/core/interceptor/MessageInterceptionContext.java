/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.interceptor;

import org.soa.security.core.Message;
import org.soa.security.core.service.Service;

/**
 * <p>A interception context, that stores that shares the state between execution of the interceptors.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class MessageInterceptionContext<T extends Message> {

    /**
     * <p>Represents the target service.</p>
     */
    private Service service;

    /**
     * <p>Represents the intercepted message.</p>
     */
    private T message;

    /**
     * <p>Creates new instance of {@link MessageInterceptionContext} class.</p>
     *
     * @param service the target service
     * @param message the intercepted message
     */
    public MessageInterceptionContext(Service service, T message) {

        this.service = service;
        this.message = message;
    }

    /**
     * <p>Retrieves the target service.</p>
     *
     * @return the target service
     */
    public Service getService() {
        return service;
    }

    /**
     * <p>Retrieves the message.</p>
     *
     * @return the message
     */
    public T getMessage() {
        return message;
    }

    /**
     * <p>Sets the message.</p>
     *
     * @param message the message
     */
    public void setMessage(T message) {
        this.message = message;
    }
}
