/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao;

import org.soa.security.model.exception.SoaException;

/**
 * <p>This exception is used by the DAO layer to indicate an error.</p>
 *
 * <p><strong>Thread safety:</strong> This class is not thread safe because the base class is not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class SoaDAOException extends SoaException {

    /**
     * <p>Creates new instance of {@link SoaDAOException} with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public SoaDAOException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link SoaDAOException} with detailed error message and inner cause.</p>
     *
     * @param message the detailed error message
     * @param cause   the inner cause
     */
    public SoaDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
