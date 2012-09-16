/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.http.client.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.soa.security.core.http.HttpHeader;
import org.soa.security.core.http.HttpMethod;
import org.soa.security.core.http.client.HttpClient;
import org.soa.security.core.http.client.HttpClientException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;

/**
 * <p>The default implementation of {@link HttpClient}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class HttpClientImpl implements HttpClient {

    /**
     * <p>Represents the size of buffer used for reading the response.</p>
     */
    public static final int BUFFER_SIZE = 1024;

    /**
     * <p>Creates new instance of {@link HttpClientImpl}.</p>
     */
    public HttpClientImpl() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeGetRequest(String url) throws HttpClientException {

        DefaultHttpClient httpClient;
        HttpGet httpGet;
        HttpResponse httpResponse;
        HttpEntity httpEntity = null;

        try {
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(url);

            // makes the request
            httpResponse = httpClient.execute(httpGet);

            // checks the response status code
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

                throw new HttpClientException(MessageFormat.format("The host respond with {0} code.",
                        httpResponse.getStatusLine().getStatusCode()));
            }

            // retrieves response content
            httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {

                throw new HttpClientException("Retrieved response didn't had any content.");
            }

            return readToEnd(httpEntity.getContent());
        } catch (ClientProtocolException e) {

            throw new HttpClientException(MessageFormat.format("Error occurred when making request to {0}.", url));
        } catch (IOException e) {

            throw new HttpClientException(MessageFormat.format("Error occurred when making request to {0}.", url));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.soa.security.core.http.HttpResponse makeRequest(String url, HttpMethod httpMethod,
                                                               List<HttpHeader> httpHeaders, InputStream content)
            throws HttpClientException {

        DefaultHttpClient httpClient;
        HttpRequestBase httpRequest;
        HttpResponse httpResponse;

        try {
            httpClient = new DefaultHttpClient();
            httpRequest = createHttpRequest(url, httpMethod, content);

            copyHeaders(httpRequest, httpHeaders);

            // makes the request
            httpResponse = httpClient.execute(httpRequest);

            // returns the response
            return new ClientHttpResponse(httpResponse);
        } catch (ClientProtocolException e) {

            throw new HttpClientException(MessageFormat.format("Error occurred when making request to {0}.", url));
        } catch (IOException e) {

            throw new HttpClientException(MessageFormat.format("Error occurred when making request to {0}.", url));
        }
    }

    /**
     * <p>The http headers.</p>
     *
     * @param httpRequest the http request
     * @param httpHeaders the http headers
     */
    private void copyHeaders(HttpRequestBase httpRequest, List<HttpHeader> httpHeaders) {

        if (httpHeaders != null && httpHeaders.size() > 0) {

            for (HttpHeader httpHeader : httpHeaders) {
                httpRequest.setHeader(httpHeader.getName(), httpHeader.getValue());
            }
        }
    }

    /**
     * <p>Creates http request based on the passed parameters.</p>
     *
     * @param url        the url
     * @param httpMethod the http method
     * @param content    the content of the request
     *
     * @return the created request
     *
     * @throws HttpClientException if any error occurs when creating the request
     */
    private HttpRequestBase createHttpRequest(String url, HttpMethod httpMethod, InputStream content)
            throws HttpClientException {

        if (httpMethod == HttpMethod.GET) {

            return new HttpGet(url);
        } else if (httpMethod == HttpMethod.POST) {

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new InputStreamEntity(content, -1));

            return httpPost;
        } else if (httpMethod == HttpMethod.PUT) {

            HttpPut httpPut = new HttpPut(url);
            httpPut.setEntity(new InputStreamEntity(content, -1));

            return httpPut;
        } else if (httpMethod == HttpMethod.DELETE) {

            return new HttpDelete(url);
        }

        throw new HttpClientException("The provided http method is not supported.");
    }

    /**
     * <p>Reads the whole input stream.</p>
     *
     * @param input the input stream
     *
     * @return the content of the response as a string
     *
     * @throws IOException if any error occurs when reading the response content
     */
    private String readToEnd(InputStream input) throws IOException {
        StringBuilder stringBuilder;
        BufferedReader bufferedReader;
        char[] buffer = new char[BUFFER_SIZE];
        int count;

        stringBuilder = new StringBuilder();
        bufferedReader = new BufferedReader(new InputStreamReader(input));

        while ((count = bufferedReader.read(buffer)) != -1) {

            stringBuilder.append(buffer, 0, count);
        }

        return stringBuilder.toString();
    }
}
