/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.impl;

import org.soa.security.dao.LookupDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.entities.DataSourceType;
import org.soa.security.model.entities.RestSecurityType;
import org.soa.security.model.entities.SecurityType;
import org.soa.security.model.entities.ServiceType;
import org.soa.security.model.entities.UserRole;
import org.soa.security.model.entities.WebServiceSecurityType;
import org.soa.security.service.LookupService;
import org.soa.security.service.SoaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>A default implementation of {@link LookupDAO}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class LookupServiceImpl implements LookupService {

    /**
     * <p>Instance of {@link LookupDAO} used for retrieving lookup entities.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private LookupDAO lookupDAO;

    /**
     * <p>Creates new instance of {@link LookupServiceImpl} class.</p>
     */
    public LookupServiceImpl() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserRole> getUserRoles() throws SoaServiceException {

        try {

            return lookupDAO.getUserRoles();
        } catch (SoaDAOException e) {

            throw new SoaServiceException("Exception occurred when retrieving user roles.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ServiceType> getServiceTypes() throws SoaServiceException {

        try {

            return lookupDAO.getServiceTypes();
        } catch (SoaDAOException e) {

            throw new SoaServiceException("Exception occurred when retrieving service types.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DataSourceType> getDataSourceTypes() throws SoaServiceException {

        try {

            return lookupDAO.getDataSourceTypes();
        } catch (SoaDAOException e) {

            throw new SoaServiceException("Exception occurred when retrieving authentication source types.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SecurityType> getSecurityTypes() throws SoaServiceException {

        try {

            return lookupDAO.getSecurityTypes();
        } catch (SoaDAOException e) {

            throw new SoaServiceException("Exception occurred when retrieving security types.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WebServiceSecurityType> getWebServiceSecurityTypes() throws SoaServiceException {

        try {

            return lookupDAO.getWebServiceSecurityTypes();
        } catch (SoaDAOException e) {

            throw new SoaServiceException("Exception occurred when retrieving security types.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestSecurityType> getRestSecurityTypes() throws SoaServiceException {

        try {

            return lookupDAO.getRestSecurityTypes();
        } catch (SoaDAOException e) {

            throw new SoaServiceException("Exception occurred when retrieving security types.", e);
        }
    }

}
