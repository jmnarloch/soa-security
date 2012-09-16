/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.http.client;

import org.soa.security.core.http.HttpHeader;
import org.soa.security.core.http.HttpMethod;
import org.soa.security.core.http.HttpResponse;

import java.io.InputStream;
import java.util.List;

/**
 * <p>A http client that can be used for performing request to http servers.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface HttpClient {

    /**
     * <p>Makes a GET request to specify url.</p>
     *
     * @param url the remote host address
     *
     * @return the content returned by the remote host
     *
     * @throws HttpClientException if any error occurs when executing the request
     */
    String makeGetRequest(String url) throws HttpClientException;

    /**
     * <p>Makes a request to a given url.</p>
     *
     * @param url         the service url
     * @param httpMethod  the http method
     * @param httpHeaders the list of http headers
     * @param content     the request content
     *
     * @return the http response
     *
     * @throws HttpClientException if any error occurs
     */
    HttpResponse makeRequest(String url, HttpMethod httpMethod, List<HttpHeader> httpHeaders,
                             InputStream content) throws HttpClientException;
}
