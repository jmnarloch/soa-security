/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.model.dto;

/**
 * <p>Represents a database authentication source DTO.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class DatabaseDataSourceDTO extends DataSourceDTO {

    /**
     * <p>Represents the connection url.</p>
     */
    private String connectionUrl;

    /**
     * <p>Represents the driver class name.</p>
     */
    private String driverClass;

    /**
     * <p>Represents the user name.</p>
     */
    private String userName;

    /**
     * <p>Represents the user password.</p>
     */
    private String userPassword;

    /**
     * <p>Represents the database user query.</p>
     */
    private String userQuery;

    /**
     * <p>Creates new instance of {@link DatabaseDataSourceDTO} class.</p>
     */
    public DatabaseDataSourceDTO() {
        // empty constructor
    }

    /**
     * <p>Retrieves the connection url.</p>
     *
     * @return the connection url
     */
    public String getConnectionUrl() {
        return connectionUrl;
    }

    /**
     * <p>Sets the connection url.</p>
     *
     * @param connectionUrl the connection url
     */
    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    /**
     * <p>Retrieves the driver class.</p>
     *
     * @return the driver class
     */
    public String getDriverClass() {
        return driverClass;
    }

    /**
     * <p>Sets the driver class.</p>
     *
     * @param driverClass the driver class
     */
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    /**
     * <p>Retrieves the user name.</p>
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <p>Sets the user name.</p>
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * <p>Retrieves the user password.</p>
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * <p>Sets the user password.</p>
     *
     * @param userPassword the user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * <p>Retrieves the user query.</p>
     *
     * @return the user query
     */
    public String getUserQuery() {
        return userQuery;
    }

    /**
     * <p>Sets the user query.</p>
     *
     * @param userQuery the user query
     */
    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }
}
