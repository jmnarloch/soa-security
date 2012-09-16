/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>Represents the http request.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface HttpRequest {

    /**
     * <p>Retrieves the request uri.</p>
     *
     * @return the request uri
     */
    String getRequestUri();

    /**
     * <p>Retrieves the request url.</p>
     *
     * @return the request url
     */
    String getRequestUrl();

    /**
     * <p>Retrieves the http method.</p>
     *
     * @return the http method
     */
    HttpMethod getMethod();

    /**
     * <p>Retrieves the http headers.</p>
     *
     * @return the http headers
     */
    List<HttpHeader> getHeaders();

    /**
     * <p>Retrieves the content input stream.</p>
     *
     * @return the content input stream
     *
     * @throws IOException if any error occurs when opening the input stream
     */
    InputStream getInputStream() throws IOException;
}
