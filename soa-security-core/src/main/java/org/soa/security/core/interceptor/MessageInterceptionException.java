/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.interceptor;

import org.soa.security.core.ServiceExecutionException;

/**
 * <p>An exception throws in case of any error that occurs during message interception.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class MessageInterceptionException extends ServiceExecutionException {

    /**
     * <p>Creates new instance of {@link MessageInterceptionException} class.</p>
     */
    public MessageInterceptionException() {
        // empty constructor
    }

    /**
     * <p>Creates new instance of {@link MessageInterceptionException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public MessageInterceptionException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link MessageInterceptionException} class with detailed error message and inner
     * cause.</p>
     *
     * @param message the detailed error message
     * @param cause   the inner cause
     */
    public MessageInterceptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
