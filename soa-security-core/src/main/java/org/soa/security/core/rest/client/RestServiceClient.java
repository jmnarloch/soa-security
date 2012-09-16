/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.rest.client;

import org.soa.security.core.Helper;
import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.http.client.HttpClient;
import org.soa.security.core.http.client.HttpClientException;
import org.soa.security.core.rest.RestMessage;
import org.soa.security.core.service.client.ServiceClient;
import org.soa.security.core.service.client.ServiceClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * <p>A service client for executing REST services and returning it's result.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class RestServiceClient implements ServiceClient<RestMessage> {

    /**
     * <p>Represents the http client used for retrieving the remote service wsdl.</p>
     */
    @Autowired
    private HttpClient httpClient;

    /**
     * <p>Creates new instance of {@link RestServiceClient} class.</p>
     */
    public RestServiceClient() {
        // empty constructor
    }

    /**
     * <p>Sets the http client.</p>
     *
     * @param httpClient the http client
     */
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * <p>Validates that all required properties has been autowired.</p>
     *
     * @throws IllegalStateException if any required property is missing
     */
    @PostConstruct
    protected void init() {

        Helper.checkStateNotNull(httpClient, "httpClient");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestMessage sendMessage(String serviceUrl, RestMessage message) throws ServiceClientException {

        try {

            HttpResponse httpResponse = httpClient.makeRequest(serviceUrl, message.getHttpMethod(),
                    message.getHttpHeaders(), message.getContent());

            return createRestMessage(httpResponse);
        } catch (IOException e) {

            throw new ServiceClientException("An error occurred when calling the target service.", e);
        } catch (HttpClientException e) {

            throw new ServiceClientException("An error occurred when calling the target service.", e);
        }
    }

    /**
     * <p>Creates a rest message out of the http response.</p>
     *
     * @param httpResponse the http response
     *
     * @return the created rest message
     */
    private RestMessage createRestMessage(HttpResponse httpResponse) throws IOException {

        return new RestMessage(null, null, null, httpResponse.getHttpHeaders(), httpResponse.getInputStream());
    }
}
