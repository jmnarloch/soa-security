/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Represents a service configuration DTO.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServiceConfigurationDTO implements Serializable {

    /**
     * <p>Represents the service configuration id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long serviceConfigurationId;

    /**
     * <p>Represents the service configuration name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String serviceName;

    /**
     * <p>Represents the service url.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String serviceUrl;

    /**
     * <p>Represents the service contract address.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String serviceContractAddress;

    /**
     * <p>Represents the service endpoints.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private List<String> endpoints;

    /**
     * <p>Represents the service type id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long serviceTypeId;

    /**
     * <p>Represents the service type name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String serviceTypeName;

    /**
     * <p>Represents whether the service is enabled.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean enabled;

    /**
     * <p>Represents whether the service security is enabled.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean securityEnabled;

    /**
     * <p>Represents the list of all configured security means.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private List<ServiceSecurityDTO> serviceSecurities;

    /**
     * <p>Represents the user id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long userId;

    /**
     * <p>Creates new instance of {@link ServiceSecurityDTO}.</p>
     */
    public ServiceConfigurationDTO() {
        // empty constructor
    }

    /**
     * <p>Retrieves the service configuration id.</p>
     *
     * @return the service configuration id
     */
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

    /**
     * <p>Retrieves the service configuration name.</p>
     *
     * @return the service configuration name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * <p>Sets the service configuration name.</p>
     *
     * @param serviceName the service configuration name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * <p>Retrieves the service url.</p>
     *
     * @return the service url
     */
    public String getServiceUrl() {
        return serviceUrl;
    }

    /**
     * <p>Sets the service url.</p>
     *
     * @param serviceUrl the service url
     */
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    /**
     * <p>Retrieves the service contract address.</p>
     *
     * @return the service contract address
     */
    public String getServiceContractAddress() {
        return serviceContractAddress;
    }

    /**
     * <p>Sets the service contract address</p>
     *
     * @param serviceContractAddress the service contract address
     */
    public void setServiceContractAddress(String serviceContractAddress) {
        this.serviceContractAddress = serviceContractAddress;
    }

    /**
     * <p>Retrieves the service endpoints.</p>
     *
     * @return the service endpoints
     */
    public List<String> getEndpoints() {
        return endpoints;
    }

    /**
     * <p>Sets the service endpoints.</p>
     *
     * @param endpoints the service endpoints
     */
    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    /**
     * <p>Retrieves the service type id.</p>
     *
     * @return the service type id
     */
    public long getServiceTypeId() {
        return serviceTypeId;
    }

    /**
     * <p>Retrieves the service type id.</p>
     *
     * @return the service type id
     */
    public void setServiceTypeId(long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * <p>Retrieves the service type name.</p>
     *
     * @return the service type name
     */
    public String getServiceTypeName() {
        return serviceTypeName;
    }

    /**
     * <p>Sets the service type name.</p>
     *
     * @param serviceTypeName the service type name
     */
    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    /**
     * <p>Retrieves whether the service configuration is enabled.</p>
     *
     * @return whether the service configuration is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <p>Retrieves whether the service configuration is enabled.</p>
     *
     * @param enabled whether the service configuration is enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * <p>Retrieves whether the service security is enabled.</p>
     *
     * @return whether the service security is enabled
     */
    public boolean isSecurityEnabled() {
        return securityEnabled;
    }

    /**
     * <p>Sets whether the service security is enabled.</p>
     *
     * @param securityEnabled whether the service security is enabled
     */
    public void setSecurityEnabled(boolean securityEnabled) {
        this.securityEnabled = securityEnabled;
    }

    /**
     * <p>Retrieves the service securities.</p>
     *
     * @return the service securities
     */
    public List<ServiceSecurityDTO> getServiceSecurities() {
        return serviceSecurities;
    }

    /**
     * <p>Sets the service securities.</p>
     *
     * @param serviceSecurities the service securities
     */
    public void setServiceSecurities(List<ServiceSecurityDTO> serviceSecurities) {
        this.serviceSecurities = serviceSecurities;
    }

    /**
     * <p>Retrieves the user id.</p>
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * <p>Sets the user id.</p>
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
