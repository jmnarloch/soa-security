/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.authentication;

import org.soa.security.core.ServiceExecutionException;

/**
 * <p>Represents a user data source used for authenticating users in application.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface UserDataSource {

    /**
     * <p>Authenticates user in the given data source.</p>
     *
     * @param login    the user login
     * @param password the user password
     *
     * @return returns whether the user has been authenticated
     *
     * @throws ServiceExecutionException if any error occurs when authenticating user
     */
    boolean authenticate(String login, String password) throws ServiceExecutionException;

    /**
     * <p>Retrieves the user by it's login</p>
     *
     * @param login the user login
     *
     * @return the user
     *
     * @throws ServiceExecutionException if any error occurs when retrieving the user
     */
    UserInfo getUser(String login) throws ServiceExecutionException;
}
