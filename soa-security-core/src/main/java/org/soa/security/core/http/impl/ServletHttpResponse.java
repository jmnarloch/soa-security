/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.http.impl;

import org.soa.security.core.http.HttpHeader;
import org.soa.security.core.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>An implementation of {@link HttpResponse} that is wrapper of {@link HttpServletResponse}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServletHttpResponse implements HttpResponse {

    /**
     * <p>Represents the instance of {@link HttpServletResponse}.</p>
     */
    private HttpServletResponse httpServletResponse;

    /**
     * <p>Creates new instance of {@link ServletHttpResponse} class.</p>
     *
     * @param httpServletResponse the http servlet response
     */
    public ServletHttpResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStatusCode() {

        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatusCode(int status) {

        httpServletResponse.setStatus(status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHttpHeader(HttpHeader header) {

        httpServletResponse.addHeader(header.getName(), header.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<HttpHeader> getHttpHeaders() {

        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getInputStream() {

        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutputStream getOutputStream() throws IOException {

        return httpServletResponse.getOutputStream();
    }
}
