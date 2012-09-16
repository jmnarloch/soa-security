/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.http.client;

import org.soa.security.core.ServiceExecutionException;

/**
 * <p>An exception used by the {@link HttpClient} for reporting errors.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class HttpClientException extends ServiceExecutionException {

    /**
     * <p>Creates new instance of {@link HttpClientException} class.</p>
     */
    public HttpClientException() {
        // empty constructor
    }

    /**
     * <p>Creates new instance of {@link HttpClientException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public HttpClientException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link HttpClientException} class with detailed error message and inner
     * cause.</p>
     *
     * @param message the detailed error message
     * @param cause   the inner cause
     */
    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
