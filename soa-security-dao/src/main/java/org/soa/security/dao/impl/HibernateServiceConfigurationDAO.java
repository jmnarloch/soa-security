/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao.impl;

import org.hibernate.HibernateException;
import org.soa.security.dao.GenericDAO;
import org.soa.security.dao.ServiceConfigurationDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.entities.DataSource;
import org.soa.security.model.entities.ServiceConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Defines methods for persisting {@link ServiceConfiguration} entity.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Repository
public class HibernateServiceConfigurationDAO extends HibernateGenericDAO<ServiceConfiguration>
        implements ServiceConfigurationDAO {

    /**
     * <p>Creates new instance of {@link HibernateServiceConfigurationDAO} class.</p>
     */
    public HibernateServiceConfigurationDAO() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ServiceConfiguration> getByUserId(long userId) throws SoaDAOException {

        try {
            return getSession().createQuery("from ServiceConfiguration s where s.user.id = :userId")
                    .setParameter("userId", userId).list();
        } catch (HibernateException e) {
            throw new SoaDAOException("Error occurred when retrieving list of user service configurations.", e);
        }
    }
}
