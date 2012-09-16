/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * <p>Represents a database authentication source.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class DatabaseDataSource extends DataSource {

    /**
     * <p>Represents the database connection string.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String connectionUrl;

    /**
     * <p>Represents the database driver class name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String driverClass;

    /**
     * <p>Represents the database query.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String userQuery;

    /**
     * <p>Creates new instance of {@link DatabaseDataSource} class.</p>
     */
    public DatabaseDataSource() {
        // empty constructor
    }

    /**
     * <p>Retrieves the database connection string.</p>
     *
     * @return the database connection string
     */
    public String getConnectionUrl() {
        return connectionUrl;
    }

    /**
     * <p>Sets the database connection string.</p>
     *
     * @param connectionUrl the database connection string
     */
    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    /**
     * <p>Retrieves the database driver class name.</p>
     *
     * @return the database driver class name
     */
    public String getDriverClass() {
        return driverClass;
    }

    /**
     * <p>Sets the database driver class name.</p>
     *
     * @param driverClass the database driver class name
     */
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    /**
     * <p>Retrieves the database user query.</p>
     *
     * @return the database user query
     */
    public String getUserQuery() {
        return userQuery;
    }

    /**
     * <p>Sets the database schema name.</p>
     *
     * @param userQuery the database user query
     */
    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }
}
