/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.dto;

import java.io.Serializable;

/**
 * <p>Represents a authentication source DTO.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class DataSourceDTO implements Serializable {

    /**
     * <p>Represents the authentication source id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long dataSourceId;

    /**
     * <p>Represents the authentication source dataSourceName.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String dataSourceName;

    /**
     * <p>Represents the authentication source type id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long dataSourceTypeId;

    /**
     * <p>Represents the authentication source type dataSourceName.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String dataSourceTypeName;

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
     * <p>Creates new instance of {@link DataSourceDTO} class.</p>
     */
    public DataSourceDTO() {
        // empty constructor
    }

    /**
     * <p>Retrieves the authentication source id.</p>
     *
     * @return the authentication source id
     */
    public long getDataSourceId() {
        return dataSourceId;
    }

    /**
     * <p>Sets the authentication source id.</p>
     *
     * @param dataSourceId the authentication source id
     */
    public void setDataSourceId(long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    /**
     * <p>Retrieves the authentication source dataSourceName.</p>
     *
     * @return the authentication source dataSourceName
     */
    public String getDataSourceName() {
        return dataSourceName;
    }

    /**
     * <p>Sets the authentication source dataSourceName.</p>
     *
     * @param dataSourceName the authentication source dataSourceName
     */
    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    /**
     * <p>Retrieves the data source type id.</p>
     *
     * @return the data source type id
     */
    public long getDataSourceTypeId() {
        return dataSourceTypeId;
    }

    /**
     * <p>Sets the data source type id.</p>
     *
     * @param dataSourceTypeId the data source type id
     */
    public void setDataSourceTypeId(long dataSourceTypeId) {
        this.dataSourceTypeId = dataSourceTypeId;
    }

    /**
     * <p>Retrieves the authentication source type name.</p>
     *
     * @return the authentication source type name
     */
    public String getDataSourceTypeName() {
        return dataSourceTypeName;
    }

    /**
     * <p>Sets the authentication source type name.</p>
     *
     * @param dataSourceTypeName the authentication source type name
     */
    public void setDataSourceTypeName(String dataSourceTypeName) {
        this.dataSourceTypeName = dataSourceTypeName;
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
