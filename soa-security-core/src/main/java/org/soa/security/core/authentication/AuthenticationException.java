/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.authentication;

import org.soa.security.core.ServiceExecutionException;

/**
 * <p>An exception that is used for raising error when the authentication fails.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class AuthenticationException extends ServiceExecutionException {

    /**
     * <p>Creates new instance of {@link AuthenticationException} class.</p>
     */
    public AuthenticationException() {
        // empty constructor
    }

    /**
     * <p>Creates new instance of {@link AuthenticationException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link AuthenticationException} class with detailed error message and inner
     * cause.</p>
     *
     * @param message the detailed error message
     * @param cause   the inner cause
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
