/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao.impl;

import org.hibernate.HibernateException;
import org.soa.security.dao.GenericDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.entities.IdentifiableEntity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.List;

/**
 * <p>This is implementation of the generic DAO.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @param <T> the type of entity
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public abstract class HibernateGenericDAO<T extends IdentifiableEntity>
        extends HibernateBaseDAO implements GenericDAO<T> {

    /**
     * <p>Represents the entity bean class.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>After initialization in constructor won't be null.</p>
     *
     * <p>Has getter.</p>
     */
    private final Class<T> entityClass;

    /**
     * <p>Creates new instance of {@link HibernateGenericDAO} class.</p>
     */
    @SuppressWarnings("unchecked")
    protected HibernateGenericDAO() {

        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * <p>Retrieves the managed entity class.</p>
     *
     * @return the managed entity class
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(T entity) throws SoaDAOException {

        try {

            return (Long)getSession().save(entity);
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when saving entity in persistence.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T entity) throws SoaDAOException {

        try {

            getSession().merge(entity);
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when updating entity in persistence.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) throws SoaDAOException {

        try {

            getSession().delete(entity);
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when deleting entity in persistence.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Serializable id) throws SoaDAOException {

        try {

            T entity = (T) getSession().get(getEntityClass(), id);

            getSession().delete(entity);
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when deleting entity in persistence.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(Serializable id) throws SoaDAOException {

        try {

            return (T) getSession().get(getEntityClass(), id);
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when retrieving entity from persistence.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() throws SoaDAOException {

        return getAllEntities(getEntityClass());
    }
}

