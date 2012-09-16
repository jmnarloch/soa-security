/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao;

import org.soa.security.model.entities.DataSourceType;
import org.soa.security.model.entities.LookupEntity;
import org.soa.security.model.entities.RestSecurityType;
import org.soa.security.model.entities.SecurityType;
import org.soa.security.model.entities.ServiceType;
import org.soa.security.model.entities.UserRole;
import org.soa.security.model.entities.WebServiceSecurityType;

import java.util.List;

/**
 * <p>DAO for retrieving lookup entities.</p>
 *
 * <p><strong>Thread safety:</strong> The implementation of this interface should be thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface LookupDAO {

    /**
     * <p>Retrieves the lookup entity from the persistence.</p>
     *
     * @param id  the entity id
     * @param <T> the type of the entity
     *
     * @return the lookuped entity or null if nothing was found
     *
     * @throws SoaDAOException if any error occurred
     */
    <T extends LookupEntity> T get(long id) throws SoaDAOException;

    /**
     * <p>Retrieves list of all user roles.</p>
     *
     * @return the list of user roles
     *
     * @throws SoaDAOException if any error occurs
     */
    List<UserRole> getUserRoles() throws SoaDAOException;

    /**
     * <p>Retrieves list of service types.</p>
     *
     * @return the list of service types
     *
     * @throws SoaDAOException if any error occurs
     */
    List<ServiceType> getServiceTypes() throws SoaDAOException;

    /**
     * <p>Retrieves list of authentication source types.</p>
     *
     * @return the list of authentication source types
     *
     * @throws SoaDAOException if any error occurs
     */
    List<DataSourceType> getDataSourceTypes() throws SoaDAOException;

    /**
     * <p>Retrieves list of security types.</p>
     *
     * @return the list of security types
     *
     * @throws SoaDAOException if any error occurs
     */
    List<SecurityType> getSecurityTypes() throws SoaDAOException;

    /**
     * <p>Retrieves list of web services security types.</p>
     *
     * @return the list of security types
     *
     * @throws SoaDAOException if any error occurs
     */
    List<WebServiceSecurityType> getWebServiceSecurityTypes() throws SoaDAOException;

    /**
     * <p>Retrieves list of rest security types.</p>
     *
     * @return the list of security types
     *
     * @throws SoaDAOException if any error occurs
     */
    List<RestSecurityType> getRestSecurityTypes() throws SoaDAOException;
}
