/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao.impl;

import org.hibernate.HibernateException;
import org.soa.security.dao.DataSourceDAO;
import org.soa.security.dao.GenericDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.entities.DataSource;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Defines methods for persisting {@link DataSource} entity.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Repository
public class HibernateDataSourceDAO extends HibernateGenericDAO<DataSource> implements DataSourceDAO {

    /**
     * <p>Creates new instance of {@link HibernateDataSourceDAO} class.</p>
     */
    public HibernateDataSourceDAO() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Serializable id) throws SoaDAOException {

        DataSource dataSource = get(id);

        if(dataSource != null) {

            getSession().delete(dataSource);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DataSource> getByUserId(long userId) throws SoaDAOException {

        try {
            return getSession().createQuery("from DataSource d where d.user.id = :userId")
                .setParameter("userId", userId).list();
        } catch (HibernateException e) {
            throw new SoaDAOException("Error occurred when retrieving list of user authentication sources.", e);
        }
    }
}
