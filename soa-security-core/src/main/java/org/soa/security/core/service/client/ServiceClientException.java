/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.client;

import org.soa.security.core.ServiceExecutionException;

/**
 * <p>An exception raised by the service client if any error occurs when invoking remote service.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServiceClientException extends ServiceExecutionException {

    /**
     * <p>Creates new instance of {@link ServiceClientException} class.</p>
     */
    public ServiceClientException() {
        // empty constructor
    }

    /**
     * <p>Creates new instance of {@link ServiceClientException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public ServiceClientException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link ServiceClientException} class with detailed error message and inner
     * cause.</p>
     *
     * @param message the detailed error message
     * @param cause   the inner cause
     */
    public ServiceClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
