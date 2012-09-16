/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao.impl;

import org.hibernate.HibernateException;
import org.soa.security.dao.LookupDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.entities.DataSourceType;
import org.soa.security.model.entities.LookupEntity;
import org.soa.security.model.entities.RestSecurityType;
import org.soa.security.model.entities.SecurityType;
import org.soa.security.model.entities.ServiceType;
import org.soa.security.model.entities.UserRole;
import org.soa.security.model.entities.WebServiceSecurityType;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

/**
 * <p>Defines methods for retrieving lookup entities.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Repository
public class HibernateLookupDAO extends HibernateBaseDAO implements LookupDAO {

    /**
     * <p>Represents a query for retrieving a list of all entities.</p>
     */
    private static final String QUERY_LOOKUP_ENTITY = "from {0} e order by e.ordinal";

    /**
     * <p>Creates new instance of {@link HibernateLookupDAO} class.</p>
     */
    public HibernateLookupDAO() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends LookupEntity> T get(long id) throws SoaDAOException {

        try {
            return (T) getSession().get(LookupEntity.class, id);
        } catch (HibernateException e) {
            throw new SoaDAOException("Error occurred when retrieving entity.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserRole> getUserRoles() throws SoaDAOException {

        return getAllLookupEntities(UserRole.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ServiceType> getServiceTypes() throws SoaDAOException {

        return getAllLookupEntities(ServiceType.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DataSourceType> getDataSourceTypes() throws SoaDAOException {

        return getAllLookupEntities(DataSourceType.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SecurityType> getSecurityTypes() throws SoaDAOException {

        return getAllLookupEntities(SecurityType.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WebServiceSecurityType> getWebServiceSecurityTypes() throws SoaDAOException {

        return getAllLookupEntities(WebServiceSecurityType.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestSecurityType> getRestSecurityTypes() throws SoaDAOException {

        return getAllLookupEntities(RestSecurityType.class);
    }

    /**
     * <p>Retrieves lookup entities of the given type ordered by order column.</p>
     *
     * @param entityClass the class of the entity
     * @param <T>         the type of entity
     *
     * @return list of the entities
     *
     * @throws org.soa.security.dao.SoaDAOException
     *          if any error occurs
     */
    private <T extends LookupEntity> List<T> getAllLookupEntities(Class<T> entityClass) throws SoaDAOException {

        try {

            return getSession().createQuery(MessageFormat.format(QUERY_LOOKUP_ENTITY,
                    entityClass.getSimpleName())).list();
        } catch (HibernateException e) {

            throw new SoaDAOException(MessageFormat.format("Error occurred when retrieving entities of type {0}",
                    entityClass.getSimpleName()), e);
        }
    }
}
