/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.authentication.impl;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.authentication.UserDataSource;
import org.soa.security.core.authentication.UserInfo;

/**
 * <p>A abstract {@link UserDataSource}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public abstract class AbstractUserDataSource implements UserDataSource {

    /**
     * <p>Creates new instance of {@link AbstractUserDataSource} class.</p>
     */
    protected AbstractUserDataSource() {
        // empty constructor
    }

    /**
     * <p>Retrieves the user info.</p>
     *
     * @param login the user login
     *
     * @return the user info
     */
    protected abstract UserInfo getUserInfo(String login) throws ServiceExecutionException;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean authenticate(String login, String password) throws ServiceExecutionException {

        UserInfo userInfo = getUserInfo(login);

        if (userInfo != null) {

            return login.equals(userInfo.getLogin()) && password.equals(userInfo.getPassword());
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo getUser(String login) throws ServiceExecutionException {

        return getUserInfo(login);
    }
}
