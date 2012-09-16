/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.exception;

/**
 * <p>Represents a base exception used by this component for reporting custom errors.</p>
 *
 * <p><strong>Thread safety:</strong> This class is not thread safety because the base class is not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class SoaException extends Exception {

    /**
     * <p>Creates new instance of {@link SoaException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public SoaException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link SoaException} class with detailed error message and inner cause.</p>
     *
     * @param message the detailed error message
     * @param cause   inner cause
     */
    public SoaException(String message, Throwable cause) {
        super(message, cause);
    }
}
