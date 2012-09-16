/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.authentication;

/**
 * <p>Represents the user info.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class UserInfo {

    /**
     * <p>Represents the user login.</p>
     */
    private String login;

    /**
     * <p>Represents the user password.</p>
     */
    private String password;

    /**
     * <p>Creates new instance of {@link UserInfo} class.</p>
     */
    public UserInfo() {
        // empty constructor
    }

    /**
     * <p>Retrieves the login.</p>
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * <p>Sets the login</p>
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * <p>Retrieves the password.</p>
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * <p>Sets the password</p>
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
