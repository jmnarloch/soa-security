/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.http.impl;

import org.soa.security.core.http.HttpHeader;

/**
 * <p>A default implementation of {@link HttpHeader}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class HttpHeaderImpl implements HttpHeader {

    /**
     * <p>Represents the header name.</p>
     */
    private String name;

    /**
     * <p>Represents the header value.</p>
     */
    private String value;

    /**
     * <p>Creates new instance of {@link HttpHeaderImpl} class.</p>
     *
     * @param name  the header name
     * @param value the header value
     */
    public HttpHeaderImpl(String name, String value) {

        this.name = name;
        this.value = value;
    }

    /**
     * <p>Retrieves the header name.</p>
     *
     * @return the header name
     */
    @Override
    public String getName() {

        return name;
    }

    /**
     * <p>Retrieves the header value.</p>
     *
     * @return the header value
     */
    @Override
    public String getValue() {

        return value;
    }
}
