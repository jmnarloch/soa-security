/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * <p>Represents a user.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class UserInfo extends IdentifiableEntity {

    /**
     * <p>Represents the user name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String userName;

    /**
     * <p>Represents the user password.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String password;

    /**
     * <p>Represents the user email address.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String emailAddress;

    /**
     * <p>Represents whether user account is enabled.</p>
     *
     * <p>False by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean enabled;

    /**
     * <p>Represents whether user account has been been removed.</p>
     *
     * <p>False by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean removed;

    /**
     * <p>Represents the user role.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    @ManyToOne
    private UserRole userRole;

    /**
     * <p>Creates new instance of {@link UserInfo} class.</p>
     */
    public UserInfo() {
        // empty constructor
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
    public String getPassword() {
        return password;
    }

    /**
     * <p>Sets the user password.</p>
     *
     * @param password the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * <p>Retrieves the user email address.</p>
     *
     * @return the user email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * <p>Sets the user email address.</p>
     *
     * @param emailAddress the user email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * <p>Retrieves whether the user account is enabled.</p>
     *
     * @return whether the user account is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <p>Sets whether the user account is enabled.</p>
     *
     * @param enabled whether the user account is enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * <p>Retrieves whether the user account is removed.</p>
     *
     * @return whether the user account is removed
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * <p>Sets whether the user account is removed.</p>
     *
     * @param removed whether the user account is removed
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    /**
     * <p>Retrieves the user role.</p>
     *
     * @return the user role
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * <p>Sets the user role.</p>
     *
     * @param userRole the user role
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
