/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * <p>Represents a authentication source.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DataSource extends IdentifiableEntity {

    /**
     * <p>Represents the authentication source name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String name;

    /**
     * <p>Represents the authentication source type.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    @ManyToOne
    private DataSourceType dataSourceType;

    /**
     * <p>Represents the authentication source user name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String userName;

    /**
     * <p>Represents the authentication source user password.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String userPassword;

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
     * <p>Represents whether this key store has been removed.</p>
     *
     * <p>False by default.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean removed;

    /**
     * <p>Creates new instance of {@link DataSource} class.</p>
     */
    protected DataSource() {
        // empty constructor
    }

    /**
     * <p>Retrieves the authentication source name.</p>
     *
     * @return the authentication source name
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Sets the authentication source name.</p>
     *
     * @param name the authentication source name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Retrieves the authentication source type.</p>
     *
     * @return the authentication source type
     */
    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    /**
     * <p>Sets the authentication source type.</p>
     *
     * @param dataSourceType the authentication source type
     */
    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    /**
     * <p>Retrieves the authentication source user name.</p>
     *
     * @return the authentication source user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <p>Sets the authentication source user name.</p>
     *
     * @param userName the authentication source user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * <p>Retrieves the authentication source user password.</p>
     *
     * @return the authentication source user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * <p>Sets the authentication source user password.</p>
     *
     * @param userPassword the authentication source user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
     * <p>Retrieves whether the authentication source has been removed.</p>
     *
     * @return whether the authentication source has been removed
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * <p>Sets whether the authentication source has been removed.</p>
     *
     * @param removed whether the authentication source has been removed
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
