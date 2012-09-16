/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service;

import org.soa.security.model.entities.DataSourceType;
import org.soa.security.model.entities.RestSecurityType;
import org.soa.security.model.entities.SecurityType;
import org.soa.security.model.entities.ServiceType;
import org.soa.security.model.entities.UserRole;
import org.soa.security.model.entities.WebServiceSecurityType;

import java.util.List;

/**
 * <p>A service for retrieving lookup values from persistence.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface LookupService {

    /**
     * <p>Retrieves list of all user roles.</p>
     *
     * @return the list of user roles
     *
     * @throws SoaServiceException if any error occurs
     */
    List<UserRole> getUserRoles() throws SoaServiceException;

    /**
     * <p>Retrieves list of service types.</p>
     *
     * @return the list of service types
     *
     * @throws SoaServiceException if any error occurs
     */
    List<ServiceType> getServiceTypes() throws SoaServiceException;

    /**
     * <p>Retrieves list of authentication source types.</p>
     *
     * @return the list of authentication source types
     *
     * @throws SoaServiceException if any error occurs
     */
    List<DataSourceType> getDataSourceTypes() throws SoaServiceException;

    /**
     * <p>Retrieves list of security types.</p>
     *
     * @return the list of security types
     *
     * @throws SoaServiceException if any error occurs
     */
    List<SecurityType> getSecurityTypes() throws SoaServiceException;

    /**
     * <p>Retrieves list of web service security types.</p>
     *
     * @return the list of security types
     *
     * @throws SoaServiceException if any error occurs
     */
    List<WebServiceSecurityType> getWebServiceSecurityTypes() throws SoaServiceException;

    /**
     * <p>Retrieves list of rest security types.</p>
     *
     * @return the list of security types
     *
     * @throws SoaServiceException if any error occurs
     */
    List<RestSecurityType> getRestSecurityTypes() throws SoaServiceException;
}
