/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao;

import org.soa.security.model.entities.IdentifiableEntity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>This is base interface of all DAO classes in this application. It defines set of common methods required for
 * handling persistence operations.</p>
 *
 * <p><strong>Thread safety:</strong> The implementation of this interface should be thread safe.</p>
 *
 * @param <T> the type of entity
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0s
 */
public interface GenericDAO<T extends IdentifiableEntity> {

    /**
     * <p>Saves entity in persistence.</p>
     *
     * @param entity entity to save
     *
     * @return the id of the newly created entity
     *
     * @throws SoaDAOException if any error occurs
     */
    long save(T entity) throws SoaDAOException;

    /**
     * <p>Updates entity in persistence.</p>
     *
     * @param entity entity to updates
     *
     * @throws SoaDAOException if any error occurs
     */
    void update(T entity) throws SoaDAOException;

    /**
     * <p>Deletes entity from persistence.</p>
     *
     * @param entity entity to delete
     *
     * @throws SoaDAOException if any error occurs
     */
    void delete(T entity) throws SoaDAOException;

    /**
     * <p>Deletes entity from persistence by it's id.</p>
     *
     * @param id the id of entity to delete
     *
     * @throws SoaDAOException if any error occurs
     */
    void delete(Serializable id) throws SoaDAOException;

    /**
     * <p>Retrieves the entity by it's id.</p>
     *
     * @param id the entity id
     *
     * @return the retrieved entity
     *
     * @throws SoaDAOException if any error occurs
     */
    T get(Serializable id) throws SoaDAOException;

    /**
     * <p>Retrieves list of all entities.</p>
     *
     * @return the retrieved entity
     *
     * @throws SoaDAOException if any error occurs
     */
    List<T> getAll() throws SoaDAOException;
}
