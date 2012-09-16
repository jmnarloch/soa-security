/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.json;

/**
 * <p>Represents a response, a wrapper for the target object.</p>
 *
 * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class Response<T> {

    /**
     * <p>Represents the response value.</p>
     */
    private final T value;

    /**
     * <p>Represents the response status.</p>
     */
    private final boolean status;

    /**
     * <p>Creates the new instance of {@link Response}.</p>
     *
     * @param value the response value
     */
    public Response(T value) {

        this(true, value);
    }

    /**
     * <p>Creates new instance of {@link Response}.</p>
     *
     * @param status the response status
     * @param value  the response value
     */
    public Response(boolean status, T value) {

        this.status = status;
        this.value = value;
    }

    /**
     * <p>Retrieves the response value.</p>
     *
     * @return the response value
     */
    public T getValue() {
        return value;
    }

    /**
     * <p>Retrieves the response status.</p>
     *
     * @return the response status
     */
    public boolean isStatus() {
        return status;
    }
}
