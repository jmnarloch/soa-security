/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service;

import org.soa.security.model.exception.SoaException;

/**
 * <p>Represents a exception used in service layer.</p>
 *
 * <p><strong>Thread safety:</strong> This class is not thread safety because the base class is not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class SoaServiceException extends SoaException {

    /**
     * <p>Creates new instance of {@link SoaServiceException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public SoaServiceException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link SoaServiceException} class with detailed error message and inner cause.</p>
     *
     * @param message the detailed error message
     * @param cause   inner cause
     */
    public SoaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
