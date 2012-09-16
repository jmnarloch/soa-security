/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.http.client.impl;

import org.apache.http.Header;
import org.soa.security.core.http.HttpHeader;
import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.http.impl.HttpHeaderImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Implementation of {@link HttpResponse} that is wrapper of {@link org.apache.http.HttpResponse}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
class ClientHttpResponse implements HttpResponse {

    /**
     * <p>Represents the wrapped instance of {@link org.apache.http.HttpResponse}.</p>
     */
    private org.apache.http.HttpResponse httpResponse;

    /**
     * <p>Creates new instance of {@link HttpResponse} class.</p>
     *
     * @param httpResponse the http response
     */
    public ClientHttpResponse(org.apache.http.HttpResponse httpResponse) {

        this.httpResponse = httpResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStatusCode() {

        return httpResponse.getStatusLine().getStatusCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatusCode(int status) {

        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHttpHeader(HttpHeader header) {

        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<HttpHeader> getHttpHeaders() {

        List<HttpHeader> httpHeaders = new ArrayList<HttpHeader>();
        for(Header header : httpResponse.getAllHeaders()) {

            httpHeaders.add(new HttpHeaderImpl(header.getName(), header.getValue()));
        }

        return httpHeaders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getInputStream() throws IOException {

        return httpResponse.getEntity() != null ? httpResponse.getEntity().getContent() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutputStream getOutputStream() throws IOException {

        throw new UnsupportedOperationException();
    }
}
