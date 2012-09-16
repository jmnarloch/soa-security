/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.registry;

import org.soa.security.core.ServiceExecutionException;

/**
 * <p>A service registry, responsible for configuring the services in the proxy module.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceRegistry {

    /**
     * <p>Adds a service configuration to the registry.</p>
     *
     * @param serviceId the service id
     *
     * @throws ServiceExecutionException if any error occurs
     */
    void addServiceConfiguration(long serviceId) throws ServiceExecutionException;

    /**
     * <p>Updates a service configuration to the registry.</p>
     *
     * @param serviceId the service id
     *
     * @throws ServiceExecutionException if any error occurs
     */
    void updateServiceConfiguration(long serviceId) throws ServiceExecutionException;

    /**
     * <p>Deletes a service configuration to the registry.</p>
     *
     * @param serviceId the service id
     *
     * @throws ServiceExecutionException if any error occurs
     */
    void deleteServiceConfiguration(long serviceId) throws ServiceExecutionException;

    /**
     * <p>Configures all services.</p>
     *
     * @throws ServiceExecutionException if any error occurs
     */
    void configureAll() throws ServiceExecutionException;
}
