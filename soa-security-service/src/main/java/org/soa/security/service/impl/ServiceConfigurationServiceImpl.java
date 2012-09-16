/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.impl;

import org.soa.security.dao.ServiceConfigurationDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.dto.ServiceConfigurationDTO;
import org.soa.security.model.entities.ServiceConfiguration;
import org.soa.security.service.ServiceConfigurationService;
import org.soa.security.service.SoaServiceException;
import org.soa.security.service.mapper.Mappers;
import org.soa.security.service.mapper.impl.ServiceConfigurationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>A default implementation of {@link ServiceConfigurationService}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Service
@Transactional
public class ServiceConfigurationServiceImpl implements ServiceConfigurationService {

    /**
     * <p>Instance of {@link ServiceConfigurationService} used for persisting service configurations.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private ServiceConfigurationDAO serviceConfigurationDAO;

    /**
     * <p>Instance of {@link ServiceConfigurationMapper} used for converting the entities.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private ServiceConfigurationMapper serviceConfigurationMapper;

    /**
     * <p>Creates new instance of {@link ServiceConfigurationServiceImpl}</p>
     */
    public ServiceConfigurationServiceImpl() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(long userId, ServiceConfigurationDTO serviceConfiguration) throws SoaServiceException {

        try {
            serviceConfiguration.setUserId(userId);

            return serviceConfigurationDAO.save(serviceConfigurationMapper.mapToEntity(serviceConfiguration));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when saving the service configuration.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(long userId, ServiceConfigurationDTO serviceConfiguration) throws SoaServiceException {

        try {
            validateUserPermission(userId, serviceConfiguration.getServiceConfigurationId());

            serviceConfiguration.setUserId(userId);

            serviceConfigurationDAO.update(serviceConfigurationMapper.mapToEntity(serviceConfiguration));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when updating the service configuration.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<ServiceConfigurationDTO> getByUserId(long userId) throws SoaServiceException {

        try {

            return Mappers.mapEntityCollection(serviceConfigurationMapper, serviceConfigurationDAO.getByUserId(userId));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the service configurations.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ServiceConfigurationDTO get(long userId, long id) throws SoaServiceException {

        try {
            validateUserPermission(userId, id);

            return serviceConfigurationMapper.mapToDto(serviceConfigurationDAO.get(id));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the service configuration.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ServiceConfigurationDTO get(long id) throws SoaServiceException {

        try {

            return serviceConfigurationMapper.mapToDto(serviceConfigurationDAO.get(id));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the service configuration.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<ServiceConfigurationDTO> getAll() throws SoaServiceException {

        try {

            return Mappers.mapEntityCollection(serviceConfigurationMapper, serviceConfigurationDAO.getAll());
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurs when retrieving the service configuration.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long userId, long serviceConfigurationId) throws SoaServiceException {

        try {
            validateUserPermission(userId, serviceConfigurationId);

            serviceConfigurationDAO.delete(serviceConfigurationId);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when deleting the service.", e);
        }
    }

    /**
     * <p>Validates that the user may access the given service configuration from persistence.</p>
     *
     * @param userId                 the user id
     * @param serviceConfigurationId the service configuration id
     *
     * @throws SoaDAOException     if any error occurs
     * @throws SoaServiceException if the user may not access the given key store
     */
    private void validateUserPermission(long userId, long serviceConfigurationId) throws SoaDAOException, SoaServiceException {

        ServiceConfiguration serviceConfiguration = serviceConfigurationDAO.get(serviceConfigurationId);

        if (serviceConfiguration.getUser().getId() != userId) {
            throw new SoaServiceException("Unauthorized to access the specified resource.");
        }
    }
}
