/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.manager.impl;

import org.soa.security.core.Helper;
import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.authentication.UserDataSource;
import org.soa.security.core.authentication.impl.DatabaseUserDataSource;
import org.soa.security.core.interceptor.MessageInterceptor;
import org.soa.security.core.service.Service;
import org.soa.security.core.service.ServiceType;
import org.soa.security.core.service.impl.ServiceImpl;
import org.soa.security.core.service.manager.ServiceManager;
import org.soa.security.core.service.security.policy.impl.XwssPolicyGenerator;
import org.soa.security.core.ws.xwss.SoapMessageSecurityInterceptor;
import org.soa.security.core.ws.xwss.XwssEncryptionCallback;
import org.soa.security.core.ws.xwss.XwssPasswordValidationCallback;
import org.soa.security.core.ws.xwss.XwssSecurityCallback;
import org.soa.security.core.ws.xwss.XwssSecurityCallbackHandler;
import org.soa.security.core.ws.xwss.XwssSignatureCallback;
import org.soa.security.model.ModelConsts;
import org.soa.security.model.dto.DataSourceDTO;
import org.soa.security.model.dto.DatabaseDataSourceDTO;
import org.soa.security.model.dto.KeyStoreDTO;
import org.soa.security.model.dto.ServiceConfigurationDTO;
import org.soa.security.model.dto.ServiceSecurityDTO;
import org.soa.security.service.DataSourceService;
import org.soa.security.service.KeyStoreService;
import org.soa.security.service.ServiceConfigurationService;
import org.soa.security.service.SoaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>A default implementation of {@link ServiceManager} class.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class ServiceManagerImpl implements ServiceManager {

    /**
     * <p>Represents the instance of {@link ServiceConfigurationService}, used for retrieving the service
     * information.</p>
     */
    @Autowired
    private ServiceConfigurationService serviceConfigurationService;

    /**
     * <p>Represents the instance of {@link DataSourceService}, used for retrieving the data source information.</p>
     */
    @Autowired
    private DataSourceService dataSourceService;

    /**
     * <p>Represents the instance of {@link KeyStoreService}, used for retrieving the data source information.</p>
     */
    @Autowired
    private KeyStoreService keyStoreService;

    /**
     * <p>A xwss policy generator, used for generating the policy files from the service configuration.</p>
     */
    @Autowired
    private XwssPolicyGenerator xwssPolicyGenerator;

    /**
     * <p>Represents a map of configured services.</p>
     */
    private final Map<String, Service> serviceMap = new HashMap<String, Service>();

    /**
     * <p>Creates new instance of {@link ServiceManagerImpl} class.</p>
     */
    public ServiceManagerImpl() {
        // empty constructor
    }

    /**
     * <p>Validates that the class has been correctly initialized.</p>
     *
     * @throws IllegalStateException if one of the required fields hasn't been set
     */
    @PostConstruct
    public void init() {

        Helper.checkStateNotNull(serviceConfigurationService, "serviceConfigurationService");
        Helper.checkStateNotNull(dataSourceService, "dataSourceService");
        Helper.checkStateNotNull(keyStoreService, "keyStoreService");
        Helper.checkStateNotNull(xwssPolicyGenerator, "xwssPolicyGenerator");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureService(long serviceId) throws ServiceExecutionException {

        try {
            // retrieves the service
            ServiceConfigurationDTO serviceConfigurationDTO = serviceConfigurationService.get(serviceId);

            if(serviceConfigurationDTO == null) {

                throw new ServiceExecutionException("Could not configure the service.");
            }

            // creates the service
            Service service = createService(serviceConfigurationDTO);

            // adds the service
            serviceMap.put(service.getUrl(), service);
        } catch (SoaServiceException e) {
            throw new ServiceExecutionException("Could not configure the service.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteService(long serviceId) throws ServiceExecutionException {

        // iterates over all services
        for (Map.Entry<String, Service> entry : serviceMap.entrySet()) {

            if (entry.getValue().getServiceConfigurationId() == serviceId) {

                // removes the service configuration
                serviceMap.remove(entry.getKey());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configureServices() throws ServiceExecutionException {

        try {

            Map<String, Service> map = new HashMap<String, Service>();

            List<Service> services = createServices(serviceConfigurationService.getAll());

            for (Service service : services) {

                map.put(service.getUrl(), service);
            }

            this.serviceMap.putAll(map);
        } catch (SoaServiceException e) {
            throw new ServiceExecutionException("An error occurred when configuring the services.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Service getServiceByUrl(String url) throws ServiceExecutionException {

        Service service = serviceMap.get(url);

        if (service == null) {
            throw new ServiceExecutionException(MessageFormat.format("A service for given url \"{0}\" does not exists",
                    url));
        }

        return service;
    }

    /**
     * <p>Creates services from their configuration.</p>
     *
     * @param serviceConfigurations the service configurations
     *
     * @return the list of services
     *
     * @throws SoaServiceException       if any error occurs when creating services
     * @throws ServiceExecutionException if any error occurs when creating the service
     */
    private List<Service> createServices(List<ServiceConfigurationDTO> serviceConfigurations)
            throws SoaServiceException, ServiceExecutionException {

        List<Service> result = new ArrayList<Service>();

        for (ServiceConfigurationDTO serviceConfiguration : serviceConfigurations) {

            result.add(createService(serviceConfiguration));
        }

        return result;
    }

    /**
     * <p>Retrieves the service from the database.</p>
     *
     * @param serviceConfiguration the service configuration
     *
     * @return the service
     *
     * @throws SoaServiceException       if any error occurs when retrieving the service information
     * @throws ServiceExecutionException if any error occurs when creating the service
     */
    private Service createService(ServiceConfigurationDTO serviceConfiguration) throws SoaServiceException,
            ServiceExecutionException {

        ServiceImpl service = new ServiceImpl();
        service.setName(serviceConfiguration.getServiceName());
        service.setUrl(serviceConfiguration.getServiceUrl());
        service.setEndpointUrl(serviceConfiguration.getEndpoints().get(0));
        service.setContractUrl(serviceConfiguration.getServiceContractAddress());
        service.setServiceType(serviceConfiguration.getServiceTypeId() == ModelConsts.ServiceTypes.WEB_SERVICE ?
                ServiceType.SOAP : ServiceType.REST);
        service.setServiceConfigurationId(serviceConfiguration.getServiceConfigurationId());

        service.setMessageInterceptors(new ArrayList<MessageInterceptor>());
        // iterates over all configured settings
        if (serviceConfiguration.getServiceTypeId() == ModelConsts.ServiceTypes.WEB_SERVICE) {

            setSoapSecurityInterceptors(service, serviceConfiguration);
        }

        return service;
    }

    /**
     * <p>Creates list of message interceptors based on the given service configuration.</p>
     *
     * @param service              the service
     * @param serviceConfiguration the service configuration
     *
     * @throws ServiceExecutionException if any error occurs
     * @throws SoaServiceException       if any error occurs
     */
    private void setSoapSecurityInterceptors(ServiceImpl service, ServiceConfigurationDTO serviceConfiguration)
            throws ServiceExecutionException, SoaServiceException {

        if (serviceConfiguration.isSecurityEnabled() && serviceConfiguration.getServiceSecurities() != null) {

            service.getMessageInterceptors().add(new SoapMessageSecurityInterceptor());

            List<XwssSecurityCallback> securityCallbacks = new ArrayList<XwssSecurityCallback>();
            for (ServiceSecurityDTO serviceSecurity : serviceConfiguration.getServiceSecurities()) {

                securityCallbacks.add(createSecurityCallback(serviceSecurity));
            }

            service.setSecurityPolicyContent(xwssPolicyGenerator.generatePolicy(
                    serviceConfiguration.getServiceSecurities()));
            service.setCallbackHandler(new XwssSecurityCallbackHandler(securityCallbacks));
        }
    }

    /**
     * <p>Creates new security callback.</p>
     *
     * @param serviceSecurity the service security
     *
     * @return the created security callback
     *
     * @throws ServiceExecutionException if any error occurs when creating security callback
     * @throws SoaServiceException       if any error occurs
     */
    private XwssSecurityCallback createSecurityCallback(ServiceSecurityDTO serviceSecurity) throws ServiceExecutionException, SoaServiceException {

        long securityTypeId = serviceSecurity.getSecurityTypeId();
        byte[] keyStoreData;
        KeyStoreDTO keyStoreDTO;

        if (securityTypeId == ModelConsts.SecurityTypes.WS_PASSWORD ||
                securityTypeId == ModelConsts.SecurityTypes.WS_DIGEST) {

            return new XwssPasswordValidationCallback(createUserDataSource(serviceSecurity.getDataSourceId()));
        } else if (securityTypeId == ModelConsts.SecurityTypes.WS_SIGNING) {

            keyStoreDTO = keyStoreService.get(serviceSecurity.getKeyStoreId());
            keyStoreData = keyStoreService.getContent(serviceSecurity.getKeyStoreId());

            return new XwssSignatureCallback(keyStoreData, keyStoreDTO.getKeyStorePassword().toCharArray(),
                    keyStoreDTO.getKeyPassword().toCharArray());
        } else if (securityTypeId == ModelConsts.SecurityTypes.WS_ENCRYPTION) {

            keyStoreDTO = keyStoreService.get(serviceSecurity.getKeyStoreId());
            keyStoreData = keyStoreService.getContent(serviceSecurity.getKeyStoreId());

            return new XwssEncryptionCallback(keyStoreData, keyStoreDTO.getKeyStorePassword().toCharArray(),
                    keyStoreDTO.getKeyPassword().toCharArray());
        }

        throw new ServiceExecutionException("The given security configuration is not supported.");
    }

    /**
     * <p>Creates the {@link org.soa.security.core.authentication.UserDataSource} based on the .</p>
     *
     * @param dataSourceId the data source id
     *
     * @return the created user data source
     */
    private UserDataSource createUserDataSource(long dataSourceId) throws ServiceExecutionException {

        try {
            DataSourceDTO dataSourceDTO = dataSourceService.get(dataSourceId);

            if (dataSourceDTO.getDataSourceTypeId() == ModelConsts.DataSourceTypes.DATABASE) {
                // casts the to database data source
                DatabaseDataSourceDTO databaseDataSourceDTO = (DatabaseDataSourceDTO) dataSourceDTO;

                return new DatabaseUserDataSource(databaseDataSourceDTO.getConnectionUrl(),
                        databaseDataSourceDTO.getUserName(), databaseDataSourceDTO.getUserPassword(),
                        databaseDataSourceDTO.getUserQuery());
            }

            // otherwise throw exception
            throw new ServiceExecutionException("The given data source type is not supported.");
        } catch (SoaServiceException e) {

            throw new ServiceExecutionException("An error occurred when retrieving the data source information.", e);
        }
    }
}