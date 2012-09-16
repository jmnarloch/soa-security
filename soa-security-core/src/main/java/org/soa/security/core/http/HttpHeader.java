/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.core.http;

/**
 * <p>Represents the http header.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface HttpHeader {

    /**
     * <p>Retrieves the header name.</p>
     *
     * @return the header name
     */
    String getName();

    /**
     * <p>Retrieves the header value.</p>
     *
     * @return the header value
     */
    String getValue();
}
