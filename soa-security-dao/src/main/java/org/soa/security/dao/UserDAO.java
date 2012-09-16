/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao;

import org.soa.security.model.entities.UserInfo;

/**
 * <p>Defines method need for manipulating {@link org.soa.security.model.entities.UserInfo} in the persistence. It
 * extends the {@link
 * GenericDAO}.</p>
 *
 * <p><strong>Thread safety:</strong> The implementation of this interface should be thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface UserDAO extends GenericDAO<UserInfo> {

    /**
     * <p>Retrieves the user id by it's name.</p>
     *
     * @param userName the user name
     *
     * @return the user
     *
     * @throws SoaDAOException if any error occurs
     */
    UserInfo getByUserName(String userName) throws SoaDAOException;
}
