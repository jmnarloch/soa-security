/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * <p>Describes the service security.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class ServiceSecurity extends IdentifiableEntity {

    /**
     * <p>Represents the authentication source.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @OneToOne
    private DataSource dataSource;

    /**
     * <p>Represents the key store.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @OneToOne
    private KeyStore keyStore;

    /**
     * <p>Represents the server key alias.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String serverAlias;

    /**
     * <p>Represents the client key alias.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String clientAlias;

    /**
     * <p>Represents the security type.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    @ManyToOne
    private SecurityType securityType;

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
     * <p>Creates new instance of {@link ServiceSecurity} class.</p>
     */
    public ServiceSecurity() {
        // empty constructor
    }

    /**
     * <p>Retrieves the authentication source.</p>
     *
     * @return the authentication source
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * <p>Sets the authentication source.</p>
     *
     * @param dataSource the authentication source
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * <p>Retrieves the key store.</p>
     *
     * @return the key store
     */
    public KeyStore getKeyStore() {
        return keyStore;
    }

    /**
     * <p>Retrieves the key store.</p>
     *
     * @param keyStore the key store
     */
    public void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    /**
     * <p>Retrieves the server key alias.</p>
     *
     * @return the key alias
     */
    public String getServerAlias() {
        return serverAlias;
    }

    /**
     * <p>Sets the server key alias.</p>
     *
     * @param serverAlias the key alias
     */
    public void setServerAlias(String serverAlias) {
        this.serverAlias = serverAlias;
    }

    /**
     * <p>Retrieves the client key alias.</p>
     *
     * @return the client key alias
     */
    public String getClientAlias() {
        return clientAlias;
    }

    /**
     * <p>Sets the client key alias</p>
     *
     * @param clientAlias the client key alias
     */
    public void setClientAlias(String clientAlias) {
        this.clientAlias = clientAlias;
    }

    /**
     * <p>Retrieves the security type.</p>
     *
     * @return the security type
     */
    public SecurityType getSecurityType() {
        return securityType;
    }

    /**
     * <p>Sets the security type.</p>
     *
     * @param securityType the security type
     */
    public void setSecurityType(SecurityType securityType) {
        this.securityType = securityType;
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
