/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.json;

/**
 * <p>Represents a error response.</p>
 *
 * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ErrorResponse extends Response {

    /**
     * <p>Represents the error message.</p>
     */
    private final String message;

    /**
     * <p>Creates new instance of {@link ErrorResponse} class.</p>
     *
     * @param message the error message
     */
    public ErrorResponse(String message) {
        super(false, null);
        this.message = message;
    }

    /**
     * <p>Retrieves the error message.</p>
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }
}
