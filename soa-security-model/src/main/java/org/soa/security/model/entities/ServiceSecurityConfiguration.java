/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * <p>Represents the configured service security.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class ServiceSecurityConfiguration extends IdentifiableEntity {

    /**
     * <p>Represents the list of all configured security means.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceSecurity> serviceSecurities;

    /**
     * <p>Represents whether the security has been enabled.</p>
     *
     * <p>False by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean enabled;

    /**
     * <p>Creates new instance of {@link ServiceSecurityConfiguration} class.</p>
     */
    public ServiceSecurityConfiguration() {
        // empty constructor
    }

    /**
     * <p>Retrieves the list of all security means.</p>
     *
     * @return the list of all security means
     */
    public List<ServiceSecurity> getServiceSecurities() {
        return serviceSecurities;
    }

    /**
     * <p>Sets the list of all security means.</p>
     *
     * @param serviceSecurities the list of all security means
     */
    public void setServiceSecurities(List<ServiceSecurity> serviceSecurities) {
        this.serviceSecurities = serviceSecurities;
    }

    /**
     * <p>Retrieves whether the security has been enabled.</p>
     *
     * @return whether the security has been enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <p>Sets whether the security has been enabled.</p>
     *
     * @param enabled whether the security has been enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}