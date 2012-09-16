/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.manager;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.service.Service;

/**
 * <p>A service manager.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceManager {

    /**
     * <p>Configures the service by it's id.</p>
     *
     * @param serviceId the service id
     *
     * @throws ServiceExecutionException if any error occurs
     */
    void configureService(long serviceId) throws ServiceExecutionException;

    /**
     * <p>Deletes the service by it's id.</p>
     *
     * @param serviceId the service id
     *
     * @throws ServiceExecutionException if any error occurs
     */
    void deleteService(long serviceId) throws ServiceExecutionException;

    /**
     * <p>Configures all services that were created in underlying authentication source.</p>
     *
     * @throws ServiceExecutionException if any error occurs when configuring the services
     */
    void configureServices() throws ServiceExecutionException;

    /**
     * <p>Retrieves the service by it's url.</p>
     *
     * @param url the service url
     *
     * @return the service
     *
     * @throws ServiceExecutionException if any error occurs when configuring the services
     */
    Service getServiceByUrl(String url) throws ServiceExecutionException;
}
