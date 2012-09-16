/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.dto;

import java.io.Serializable;

/**
 * <p>Represents a user DTO.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class UserDTO implements Serializable {

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
     * <p>Represents the user name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
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
    private String userPassword;

    /**
     * <p>Represents the user email address.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
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
     * <p>Represents the user role id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long userRoleId;

    /**
     * <p>Represents the user role name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String userRoleName;

    /**
     * <p>Creates new instance of {@link UserDTO} class.</p>
     */
    public UserDTO() {
        // empty constructor
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
     * <p>Retrieves the user id.</p>
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
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
     * <p>Sets the user email address.</p>
     *
     * @param enabled the user email address
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * <p>Retrieves the use role id.</p>
     *
     * @return the user role id
     */
    public long getUserRoleId() {
        return userRoleId;
    }

    /**
     * <p>Sets the user role id.</p>
     *
     * @param userRoleId the role id
     */
    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * <p>Retrieves the user role name.</p>
     *
     * @return the user role name
     */
    public String getUserRoleName() {
        return userRoleName;
    }

    /**
     * <p>Sets the user role name.</p>
     *
     * @param userRoleName the user role name
     */
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
