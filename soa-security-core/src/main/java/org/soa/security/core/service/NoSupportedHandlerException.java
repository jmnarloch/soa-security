/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service;

import org.soa.security.core.ServiceExecutionException;

/**
 * <p>An exception used for reporting missing handlers in the service configuration.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class NoSupportedHandlerException extends ServiceExecutionException {

    /**
     * <p>Creates new instance of {@link NoSupportedHandlerException} class.</p>
     */
    public NoSupportedHandlerException() {
        // empty constructor
    }

    /**
     * <p>Creates new instance of {@link NoSupportedHandlerException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public NoSupportedHandlerException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link NoSupportedHandlerException} class with detailed error message and inner
     * cause.</p>
     *
     * @param message the detailed error message
     * @param cause   the inner cause
     */
    public NoSupportedHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
