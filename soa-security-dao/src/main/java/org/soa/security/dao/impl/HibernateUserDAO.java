/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao.impl;

import org.hibernate.HibernateException;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.dao.UserDAO;
import org.soa.security.model.entities.UserInfo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

/**
 * <p>Defines methods for persisting {@link org.soa.security.model.entities.UserInfo} entity.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Repository
public class HibernateUserDAO extends HibernateGenericDAO<UserInfo> implements UserDAO {

    /**
     * <p>Creates new instance of {@link HibernateUserDAO} class.</p>
     */
    public HibernateUserDAO() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo get(Serializable id) throws SoaDAOException {

        try {
            return (UserInfo) getSession().createQuery("from UserInfo usr where usr.id = :id and usr.removed = false")
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when retrieving user.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfo> getAll() throws SoaDAOException {

        try {
            return (List<UserInfo>) getSession().createQuery("from UserInfo usr where usr.removed = false")
                    .list();
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when retrieving user list.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Serializable id) throws SoaDAOException {

        // retrieve the user by it's id
        UserInfo userInfo = get(id);

        // checks if user exists
        if (userInfo != null) {

            userInfo.setRemoved(true);

            update(userInfo);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo getByUserName(String userName) throws SoaDAOException {

        try {
            return (UserInfo) getSession().createQuery("from UserInfo u where u.userName = :userName")
                    .setParameter("userName", userName).uniqueResult();
        } catch (HibernateException e) {
            throw new SoaDAOException("Error occurred when retrieving the user.", e);
        }
    }
}
