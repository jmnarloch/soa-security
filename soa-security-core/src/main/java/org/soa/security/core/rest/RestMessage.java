/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.rest;

import org.soa.security.core.Message;
import org.soa.security.core.http.HttpHeader;
import org.soa.security.core.http.HttpMethod;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>Represents a REST message.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class RestMessage implements Message {

    /**
     * <p>Represents the url of the target service.</p>
     */
    private String url;

    /**
     * <p>The request url.</p>
     */
    private String requestUrl;

    /**
     * <p>Represents the http method.</p>
     */
    private HttpMethod httpMethod;

    /**
     * <p>Represents the list of http headers.</p>
     */
    private List<HttpHeader> httpHeaders;

    /**
     * <p>Represents the message content.</p>
     */
    private byte[] content;

    /**
     * <p>Creates new instance of {@link RestMessage} class.</p>
     *
     * @param url         the target web service url
     * @param requestUrl  the service request url
     * @param httpMethod  http method
     * @param httpHeaders the http headers
     * @param content     the request content
     */
    public RestMessage(String url, String requestUrl, HttpMethod httpMethod, List<HttpHeader> httpHeaders, InputStream content) {

        this.url = url;
        this.requestUrl = requestUrl;
        this.httpMethod = httpMethod;
        this.httpHeaders = httpHeaders;

        this.content = toByteArray(content);
    }

    /**
     * <p>Retrieves the service url.</p>
     *
     * @return the service url
     */
    public String getUrl() {
        return url;
    }

    /**
     * <p>Retrieves the request url.</p>
     *
     * @return the request url
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * <p>Retrieves the http method.</p>
     *
     * @return the http method
     */
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    /**
     * <p>Retrieves the http headers.</p>
     *
     * @return the http headers
     */
    public List<HttpHeader> getHttpHeaders() {
        return httpHeaders;
    }

    /**
     * <p>Retrieves the content of the message.</p>
     *
     * @return the message content
     */
    public InputStream getContent() {

        return new ByteArrayInputStream(content);
    }

    /**
     * <p>Writes the message to the given output stream.</p>
     *
     * @param outputStream the output stream
     */
    public void writeTo(OutputStream outputStream) throws IOException {

        // if there is no authentication then does nothing
        if (content != null && content.length > 0) {

            outputStream.write(content);
        }
    }

    /**
     * <p>Reads the content of the input stream and returns it as </p>
     *
     * @param content the input stream
     *
     * @return the content of the input stream
     */
    private byte[] toByteArray(InputStream content) {

        int bufferSize = 2048;
        ByteArrayOutputStream outputStream;
        byte[] buffer;
        int count;

        if (content != null) {

            outputStream = new ByteArrayOutputStream();
            buffer = new byte[bufferSize];

            try {
                while ((count = content.read(buffer)) != -1) {

                    outputStream.write(buffer, 0, count);
                }

                return outputStream.toByteArray();
            } catch (IOException e) {

                // ignores exception
            }
        }

        return new byte[0];
    }
}
