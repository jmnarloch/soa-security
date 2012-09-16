/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core;

/**
 * <p>The base exception used for reporting the error situations.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServiceExecutionException extends Exception {

    /**
     * <p>Creates new instance of {@link ServiceExecutionException} class.</p>
     */
    public ServiceExecutionException() {
        // empty constructor
    }

    /**
     * <p>Creates new instance of {@link ServiceExecutionException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public ServiceExecutionException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link ServiceExecutionException} class with detailed error message and inner
     * cause.</p>
     *
     * @param message the detailed error message
     * @param cause   the inner cause
     */
    public ServiceExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
