/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * <p>Describes the service configuration.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class ServiceConfiguration extends IdentifiableEntity {

    /**
     * <p>Represents the service name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String serviceName;

    /**
     * <p>Represents the address under which the service will be accessible.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
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
     * <p>Represents the list of service endpoints.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceEndpoint> endpoints;

    /**
     * <p>Represents the user.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    @ManyToOne
    private UserInfo user;

    /**
     * <p>Represents whether this service configuration is enabled.</p>
     *
     * <p>False by default.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean enabled;

    /**
     * <p>Represents whether this service configuration has been removed.</p>
     *
     * <p>False by default.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean removed;

    /**
     * <p>Represents the service type.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    @ManyToOne
    private ServiceType serviceType;

    /**
     * <p>Represents the security associated with this service.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ServiceSecurityConfiguration serviceSecurityConfiguration;

    /**
     * <p>Creates new instance of {@link ServiceConfiguration} class.</p>
     */
    public ServiceConfiguration() {
        // empty constructor
    }

    /**
     * <p>Retrieves the service name.</p>
     *
     * @return the service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * <p>Sets the service name.</p>
     *
     * @param serviceName the service name
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
     * <p>Sets the service contract address.</p>
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
    public List<ServiceEndpoint> getEndpoints() {
        return endpoints;
    }

    /**
     * <p>Sets the service endpoints.</p>
     *
     * @param endpoints the service endpoints
     */
    public void setEndpoints(List<ServiceEndpoint> endpoints) {
        this.endpoints = endpoints;
    }

    /**
     * <p>Retrieves the user.</p>
     *
     * @return the user
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * <p>Sets the user.</p>
     *
     * @param user the user
     */
    public void setUser(UserInfo user) {
        this.user = user;
    }

    /**
     * <p>Retrieves whether this configuration is enabled.</p>
     *
     * @return whether this configuration is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <p>Sets whether this configuration is enabled.</p>
     *
     * @param enabled whether this configuration is enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * <p>Retrieves whether this configuration has been removed.</p>
     *
     * @return whether this configuration has been removed
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * <p>Sets whether this configuration has been removed.</p>
     *
     * @param removed whether this configuration has been removed
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    /**
     * <p>Retrieves the service type.</p>
     *
     * @return the service type
     */
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
     * <p>Retrieves the security configuration.</p>
     *
     * @return the security configuration
     */
    public ServiceSecurityConfiguration getServiceSecurityConfiguration() {
        return serviceSecurityConfiguration;
    }

    /**
     * <p>Sets the security configuration.</p>
     *
     * @param serviceSecurityConfiguration the security configuration
     */
    public void setServiceSecurityConfiguration(ServiceSecurityConfiguration serviceSecurityConfiguration) {
        this.serviceSecurityConfiguration = serviceSecurityConfiguration;
    }
}
