/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.entities.IdentifiableEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.List;

/**
 * <p>This is base class for all DAO classes in this component.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public abstract class HibernateBaseDAO {

    /**
     * <p>Represents a query for retrieving all instance of entity of the given type.</p>
     */
    private static final String QUERY_SELECT_ALL = "from {0}";

    /**
     * <p>Represents the session factory.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <p>Creates new instance of {@link HibernateBaseDAO} class.</p>
     */
    protected HibernateBaseDAO() {
        // empty constructor
    }

    /**
     * <p>Retrieves the session factory.</p>
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * <p>Sets the session factory.</p>
     *
     * @param sessionFactory the session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * <p>Retrieves current hibernate session.</p>
     *
     * @return the hibernate session
     *
     * @throws org.soa.security.dao.SoaDAOException if any error occurred
     */
    protected Session getSession() throws SoaDAOException {

        try {

            return getSessionFactory().getCurrentSession();
        } catch (HibernateException e) {

            throw new SoaDAOException("Could not get hibernate session.", e);
        }
    }

    /**
     * <p>Retrieves list of entities of concrete type.</p>
     *
     * @param entityClass the class of entities to get
     * @param <T>         the type of entities to get
     *
     * @return list of retrieved entities
     *
     * @throws org.soa.security.dao.SoaDAOException if any error occurs
     */
    protected <T extends IdentifiableEntity> List<T> getAllEntities(Class<T> entityClass) throws SoaDAOException {

        try {

            return getSession().createQuery(MessageFormat.format(QUERY_SELECT_ALL, entityClass.getSimpleName())).list();
        } catch (HibernateException e) {

            throw new SoaDAOException(MessageFormat.format("Error occurred when retrieving entities of type {0}",
                    entityClass.getSimpleName()), e);
        }
    }
}
