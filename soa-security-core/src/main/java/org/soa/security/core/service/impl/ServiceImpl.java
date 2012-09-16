/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.impl;

import org.soa.security.core.interceptor.MessageInterceptor;
import org.soa.security.core.service.Service;
import org.soa.security.core.service.ServiceType;

import javax.security.auth.callback.CallbackHandler;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * <p>A default implementation of {@link Service} class.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServiceImpl implements Service {

    /**
     * <p>Represents the service name.</p>
     */
    private String name;

    /**
     * <p>Represents the service url.</p>
     */
    private String url;

    /**
     * <p>Represents the endpoint url.</p>
     */
    private String endpointUrl;

    /**
     * <p>Represents the service contract url.</p>
     */
    private String contractUrl;

    /**
     * <p>Represents the service type.</p>
     */
    private ServiceType serviceType;

    /**
     * <p>Represents the message interceptors.</p>
     */
    private List<MessageInterceptor> messageInterceptors;

    /**
     * <p>Represents the security policy.</p>
     */
    private String securityPolicyContent;

    /**
     * <p>Represents the callback handler.</p>
     */
    private CallbackHandler callbackHandler;

    /**
     * <p>Represents the id of the service configuration.</p>
     */
    private long serviceConfigurationId;

    /**
     * <p>Creates new instance of {@link ServiceImpl} class.</p>
     */
    public ServiceImpl() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <p>Sets the service name.</p>
     *
     * @param name the service name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * <p>Sets the service url.</p>
     *
     * @param url the service url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEndpointUrl() {
        return endpointUrl;
    }

    /**
     * <p>Sets the endpoint url.</p>
     *
     * @param endpointUrl the endpoint url
     */
    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContractUrl() {
        return contractUrl;
    }

    /**
     * <p>Sets the contract url.</p>
     *
     * @param contractUrl the contract url
     */
    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * <p>Sets the service type.</p>
     *
     * @param serviceType the service type
     */
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MessageInterceptor> getMessageInterceptors() {
        return messageInterceptors;
    }

    /**
     * <p>Sets the list of message interceptors.</p>
     *
     * @param messageInterceptors list of message interceptors
     */
    public void setMessageInterceptors(List<MessageInterceptor> messageInterceptors) {
        this.messageInterceptors = messageInterceptors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getSecurityPolicy() {
        return new ByteArrayInputStream(securityPolicyContent.getBytes());
    }

    /**
     * <p>Sets the security policy.</p>
     *
     * @param securityPolicy the security policy
     */
    public void setSecurityPolicyContent(String securityPolicy) {
        this.securityPolicyContent = securityPolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CallbackHandler getCallbackHandler() {
        return callbackHandler;
    }

    /**
     * <p>Sets the callback handler.</p>
     *
     * @param callbackHandler the callback handler
     */
    public void setCallbackHandler(CallbackHandler callbackHandler) {
        this.callbackHandler = callbackHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getServiceConfigurationId() {
        return serviceConfigurationId;
    }

    /**
     * <p>Sets the service configuration id.</p>
     *
     * @param serviceConfigurationId the service configuration id
     */
    public void setServiceConfigurationId(long serviceConfigurationId) {
        this.serviceConfigurationId = serviceConfigurationId;
    }
}
