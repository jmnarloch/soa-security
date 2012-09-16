/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service;

import org.soa.security.core.interceptor.MessageInterceptor;

import javax.security.auth.callback.CallbackHandler;
import java.io.InputStream;
import java.util.List;

/**
 * <p>Represents a service.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface Service {

    /**
     * <p>Retrieves the service name.</p>
     *
     * @return the service name
     */
    String getName();

    /**
     * <p>Retrieves the service url.</p>
     *
     * @return the service url
     */
    String getUrl();

    /**
     * <p>Represents the service endpoint url.</p>
     *
     * @return the endpoint url
     */
    String getEndpointUrl();

    /**
     * <p>Retrieves the service contract url.</p>
     *
     * @return the service contract url
     */
    String getContractUrl();

    /**
     * <p>Retrieves the service type.</p>
     *
     * @return the service type
     */
    ServiceType getServiceType();

    /**
     * <p>Retrieves the list of message interceptors.</p>
     *
     * @return the list of message interceptors
     */
    List<MessageInterceptor> getMessageInterceptors();

    /**
     * <p>Retrieves the security policy.</p>
     *
     * @return the security policy
     */
    InputStream getSecurityPolicy();

    /**
     * <p>Retrieves the callback handler.</p>
     *
     * @return the callback handler
     */
    CallbackHandler getCallbackHandler();

    /**
     * <p>Retrieves the service configuration id.</p>
     * @return the service configuration id
     */
    long getServiceConfigurationId();
}
