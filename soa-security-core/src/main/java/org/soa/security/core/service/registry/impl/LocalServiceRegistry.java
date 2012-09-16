/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.registry.impl;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.service.manager.ServiceManager;
import org.soa.security.core.service.registry.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Represents a local service registry that can be accessed through the same JVM.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class LocalServiceRegistry implements ServiceRegistry {

    /**
     * <p>An instance of {@link ServiceManager}.</p>
     */
    @Autowired
    private ServiceManager serviceManager;

    /**
     * <p>Creates new instance of {@link LocalServiceRegistry} class.</p>
     */
    public LocalServiceRegistry() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addServiceConfiguration(long serviceId) throws ServiceExecutionException {

        serviceManager.configureService(serviceId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateServiceConfiguration(long serviceId) throws ServiceExecutionException {

        serviceManager.configureService(serviceId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteServiceConfiguration(long serviceId) throws ServiceExecutionException {

        serviceManager.deleteService(serviceId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureAll() throws ServiceExecutionException {

        // delegates the invocation to the underlying service manager
        serviceManager.configureServices();
    }
}
