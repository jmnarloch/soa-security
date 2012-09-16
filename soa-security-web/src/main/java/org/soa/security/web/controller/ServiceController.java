/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.controller;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.service.registry.ServiceRegistry;
import org.soa.security.model.dto.ServiceConfigurationDTO;
import org.soa.security.service.ServiceConfigurationService;
import org.soa.security.service.SoaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>Service controller.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Controller
public class ServiceController extends BaseController {

    /**
     * <p>Instance of {@link org.soa.security.service.ServiceConfigurationService} used for retrieving service
     * configuration.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private ServiceConfigurationService serviceConfigurationService;

    /**
     * <p>Represents a instance of {@link ServiceRegistry}.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private ServiceRegistry serviceRegistry;

    /**
     * <p>Creates new instance of {@link ServiceController} class.</p>
     */
    public ServiceController() {
        // empty constructor
    }

    /**
     * <p>Handles request for creating new service configuration</p>
     *
     * @param service the service
     *
     * @return the id of newly created service
     *
     * @throws SoaServiceException       if any error occurs
     * @throws ServiceExecutionException if any error occurs
     */
    @RequestMapping(value = "/Service", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public long createService(@RequestBody ServiceConfigurationDTO service)
            throws SoaServiceException, ServiceExecutionException {

        service.setEnabled(true);

        long serviceId = serviceConfigurationService.save(getAuthenticatedUser().getUserId(), service);

        serviceRegistry.addServiceConfiguration(serviceId);

        return serviceId;
    }

    /**
     * <p>Updates the service configuration.</p>
     *
     * @param service the service
     *
     * @throws SoaServiceException       if any error occurs
     * @throws ServiceExecutionException if any error occurs
     */
    @RequestMapping(value = "/Service", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateService(@RequestBody ServiceConfigurationDTO service)
            throws SoaServiceException, ServiceExecutionException {

        serviceConfigurationService.update(getAuthenticatedUser().getUserId(), service);

        serviceRegistry.updateServiceConfiguration(service.getServiceConfigurationId());
    }

    /**
     * <p>Retrieves the service configuration.</p>
     *
     * @param serviceId the service id
     *
     * @return the service configuration
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/Service", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServiceConfigurationDTO getServiceById(@RequestParam long serviceId)
            throws SoaServiceException {

        return serviceConfigurationService.get(getAuthenticatedUser().getUserId(), serviceId);
    }

    /**
     * <p>Deletes the service configuration based on it's id.</p>
     *
     * @param serviceId the service id
     *
     * @return whether the operation succeeded
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/Service", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteService(@RequestParam long serviceId)
            throws SoaServiceException, ServiceExecutionException {

        serviceConfigurationService.delete(getAuthenticatedUser().getUserId(), serviceId);

        serviceRegistry.deleteServiceConfiguration(serviceId);
    }

    /**
     * <p>Handles request for all user services.</p>
     *
     * @return list of user services
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/Service/User", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ServiceConfigurationDTO> getServicesByUserId()
            throws SoaServiceException {

        return serviceConfigurationService.getByUserId(getAuthenticatedUser().getUserId());
    }
}
