/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service;

import org.soa.security.model.dto.ServiceConfigurationDTO;

import java.util.List;

/**
 * <p>A service for performing operations on {@link ServiceConfigurationDTO}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceConfigurationService {

    /**
     * <p>Saves {@link ServiceConfigurationDTO} in persistence.</p>
     *
     * @param userId  the user id
     * @param serviceConfiguration the {@link ServiceConfigurationDTO} to persist
     *
     * @throws SoaServiceException if any error occurs
     */
    long save(long userId, ServiceConfigurationDTO serviceConfiguration) throws SoaServiceException;

    /**
     * <p>Updates the service configuration.</p>
     *
     * @param userId               the user id
     * @param serviceConfiguration the service configuration
     *
     * @throws SoaServiceException if any error occurs
     */
    void update(long userId, ServiceConfigurationDTO serviceConfiguration) throws SoaServiceException;

    /**
     * <p>Retrieves all service configurations for given user.</p>
     *
     * @param userId the user id
     *
     * @return list of all user service configurations
     *
     * @throws SoaServiceException if any error occurs
     */
    List<ServiceConfigurationDTO> getByUserId(long userId) throws SoaServiceException;

    /**
     * <p>Retrieves the service configuration.</p>
     *
     * @param userId the user id
     * @param id     the service configuration id
     *
     * @return the data source
     *
     * @throws SoaServiceException if any error occurs
     */
    ServiceConfigurationDTO get(long userId, long id) throws SoaServiceException;

    /**
     * <p>Retrieves the service configuration.</p>
     *
     * @param id     the service configuration id
     *
     * @return the data source
     *
     * @throws SoaServiceException if any error occurs
     */
    ServiceConfigurationDTO get(long id) throws SoaServiceException;

    /**
     * <p>Retrieves the list of all all the service configuration.</p>
     *
     * @return the list of all service configurations
     *
     * @throws SoaServiceException if any error occurs
     */
    List<ServiceConfigurationDTO> getAll() throws SoaServiceException;

    /**
     * <p>Deletes the service configuration.</p>
     *
     * @param userId                 the user id
     * @param serviceConfigurationId the service configuration id
     *
     * @throws SoaServiceException if any error occurs
     */
    void delete(long userId, long serviceConfigurationId) throws SoaServiceException;
}
