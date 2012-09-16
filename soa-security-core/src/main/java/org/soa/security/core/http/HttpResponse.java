/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>Represents the http response.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface HttpResponse {

    /**
     * <p>Retrieves the status code.</p>
     *
     * @return the status code
     */
    int getStatusCode();

    /**
     * <p>Retrieves the status code.</p>
     *
     * @param status the status code
     */
    void setStatusCode(int status);

    /**
     * <p>Sets the http header.</p>
     *
     * @param header the http header
     */
    void setHttpHeader(HttpHeader header);

    /**
     * <p>Retrieves the list of http headers.</p>
     *
     * @return the list of http headers
     */
    List<HttpHeader> getHttpHeaders();

    /**
     * <p>Retrieves the input stream.</p>
     *
     * @return the input stream
     *
     * @throws IOException if any error occurs
     */
    InputStream getInputStream() throws IOException;

    /**
     * <p>Retrieves the output stream.</p>
     *
     * @return the output stream
     *
     * @throws IOException if any error occurs
     */
    OutputStream getOutputStream() throws IOException;
}
