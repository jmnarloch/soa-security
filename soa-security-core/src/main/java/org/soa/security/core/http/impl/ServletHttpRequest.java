/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.http.impl;

import org.soa.security.core.http.HttpHeader;
import org.soa.security.core.http.HttpMethod;
import org.soa.security.core.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>An implementation of {@link HttpRequest} that is wrapper of {@link HttpServletRequest}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServletHttpRequest implements HttpRequest {

    /**
     * <p>Represents the instance of {@link HttpServletRequest}.</p>
     */
    private HttpServletRequest httpServletRequest;

    /**
     * <p>Creates new instance of {@link HttpServletRequest}.</p>
     *
     * @param httpServletRequest the http servlet request
     */
    public ServletHttpRequest(HttpServletRequest httpServletRequest) {
        // TODO validate not null

        this.httpServletRequest = httpServletRequest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRequestUri() {

        return httpServletRequest.getRequestURI().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRequestUrl() {

        String uri = httpServletRequest.getRequestURI().toString();
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null) {
            uri += "?" + queryString;
        }
        return uri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpMethod getMethod() {

        return Enum.valueOf(HttpMethod.class, httpServletRequest.getMethod());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<HttpHeader> getHeaders() {

        List<HttpHeader> headers = new ArrayList<HttpHeader>();

        String headerName;
        Enumeration<String> headerEnumeration = httpServletRequest.getHeaderNames();
        while (headerEnumeration.hasMoreElements()) {

            headerName = headerEnumeration.nextElement();

            headers.add(new HttpHeaderImpl(headerName, httpServletRequest.getHeader(headerName)));
        }

        return headers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getInputStream() throws IOException {

        // TODO decide if the input stream should not be buffered to allow multiple reads
        return httpServletRequest.getInputStream();
    }
}
