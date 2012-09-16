/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.dto;

import java.io.Serializable;

/**
 * <p>Represents a service security DTO.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServiceSecurityDTO implements Serializable {

    /**
     * <p>Represents the service security type id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long securityTypeId;

    /**
     * <p>Represents the service security type name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String securityTypeName;

    /**
     * <p>Represents a data source id.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long dataSourceId;

    /**
     * <p>Represents a key store.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long keyStoreId;

    /**
     * <p>Represents the key store alias.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String serverAlias;

    /**
     * <p>Represents the key store alias.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String clientAlias;

    /**
     * <p>Creates new instance of {@link ServiceSecurityDTO} class.</p>
     */
    public ServiceSecurityDTO() {
        // empty constructor
    }

    /**
     * <p>Retrieves the security type name.</p>
     *
     * @return the security type name
     */
    public long getSecurityTypeId() {
        return securityTypeId;
    }

    /**
     * <p>Sets the security type name.</p>
     *
     * @param securityTypeId the security type name
     */
    public void setSecurityTypeId(long securityTypeId) {
        this.securityTypeId = securityTypeId;
    }

    /**
     * <p>Retrieves the security type name.</p>
     *
     * @return the security type name
     */
    public String getSecurityTypeName() {
        return securityTypeName;
    }

    /**
     * <p>Sets the security type name</p>
     *
     * @param securityTypeName the security type name
     */
    public void setSecurityTypeName(String securityTypeName) {
        this.securityTypeName = securityTypeName;
    }

    /**
     * <p>Retrieves the data source.</p>
     *
     * @return the data source
     */
    public long getDataSourceId() {
        return dataSourceId;
    }

    /**
     * <p>Sets a data source.</p>
     *
     * @param dataSourceId the data source
     */
    public void setDataSourceId(long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    /**
     * <p>Retrieves the key store.</p>
     *
     * @return the key store
     */
    public long getKeyStoreId() {
        return keyStoreId;
    }

    /**
     * <p>Sets the key store.</p>
     *
     * @param keyStoreId the key store
     */
    public void setKeyStoreId(long keyStoreId) {
        this.keyStoreId = keyStoreId;
    }

    /**
     * <p>Retrieves the server alias.</p>
     *
     * @return the server alias
     */
    public String getServerAlias() {
        return serverAlias;
    }

    /**
     * <p>Sets the server alias.</p>
     *
     * @param serverAlias the server alias
     */
    public void setServerAlias(String serverAlias) {
        this.serverAlias = serverAlias;
    }

    /**
     * <p>Retrieves the client alias.</p>
     *
     * @return the client alias
     */
    public String getClientAlias() {
        return clientAlias;
    }

    /**
     * <p>Sets the client alias.</p>
     *
     * @param clientAlias the client alias
     */
    public void setClientAlias(String clientAlias) {
        this.clientAlias = clientAlias;
    }
}
