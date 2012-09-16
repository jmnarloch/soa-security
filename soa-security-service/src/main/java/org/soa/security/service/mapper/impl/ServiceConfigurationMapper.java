/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.mapper.impl;

import org.soa.security.dao.DataSourceDAO;
import org.soa.security.dao.KeyStoreDAO;
import org.soa.security.dao.LookupDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.dao.UserDAO;
import org.soa.security.model.ModelConsts;
import org.soa.security.model.dto.ServiceConfigurationDTO;
import org.soa.security.model.dto.ServiceSecurityDTO;
import org.soa.security.model.entities.SecurityType;
import org.soa.security.model.entities.ServiceConfiguration;
import org.soa.security.model.entities.ServiceEndpoint;
import org.soa.security.model.entities.ServiceSecurity;
import org.soa.security.model.entities.ServiceSecurityConfiguration;
import org.soa.security.model.entities.ServiceType;
import org.soa.security.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A {@link ServiceConfiguration} mapper.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class ServiceConfigurationMapper implements Mapper<ServiceConfiguration, ServiceConfigurationDTO> {

    /**
     * <p>Represents the instance of {@link LookupDAO} class.</p>
     */
    @Autowired
    private LookupDAO lookupDAO;

    /**
     * <p>Represents the instance of {@link DataSourceDAO} class.</p>
     */
    @Autowired
    private DataSourceDAO dataSourceDAO;

    /**
     * <p>Represents the instance of {@link KeyStoreDAO} class.</p>
     */
    @Autowired
    private KeyStoreDAO keyStoreDAO;

    /**
     * <p>Represents the instance of {@link UserDAO} class.</p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <p>Creates new instance of {@link ServiceConfigurationMapper}.</p>
     */
    public ServiceConfigurationMapper() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceConfigurationDTO mapToDto(ServiceConfiguration serviceConfiguration) {

        ServiceConfigurationDTO serviceConfigurationDTO = new ServiceConfigurationDTO();

        serviceConfigurationDTO.setServiceConfigurationId(serviceConfiguration.getId());
        serviceConfigurationDTO.setServiceName(serviceConfiguration.getServiceName());
        serviceConfigurationDTO.setServiceTypeId(serviceConfiguration.getServiceType().getId());
        serviceConfigurationDTO.setServiceTypeName(serviceConfiguration.getServiceType().getName());
        serviceConfigurationDTO.setServiceUrl(serviceConfiguration.getServiceUrl());
        serviceConfigurationDTO.setServiceContractAddress(serviceConfiguration.getServiceContractAddress());
        serviceConfigurationDTO.setEndpoints(getServiceEndpointsUrls(serviceConfiguration.getEndpoints()));
        serviceConfigurationDTO.setEnabled(serviceConfiguration.isEnabled());
        serviceConfigurationDTO.setSecurityEnabled(serviceConfiguration.getServiceSecurityConfiguration().isEnabled());
        serviceConfigurationDTO.setServiceSecurities(getServiceSecurityConfiguration(
                serviceConfiguration.getServiceSecurityConfiguration()));
        serviceConfigurationDTO.setUserId(serviceConfiguration.getUser().getId());

        return serviceConfigurationDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceConfiguration mapToEntity(ServiceConfigurationDTO serviceConfigurationDTO) throws SoaDAOException {

        ServiceConfiguration serviceConfiguration = new ServiceConfiguration();

        serviceConfiguration.setId(serviceConfigurationDTO.getServiceConfigurationId());
        serviceConfiguration.setServiceName(serviceConfigurationDTO.getServiceName());
        serviceConfiguration.setServiceType((ServiceType) lookupDAO.get(serviceConfigurationDTO.getServiceTypeId()));
        serviceConfiguration.setServiceUrl(serviceConfigurationDTO.getServiceUrl());
        serviceConfiguration.setServiceContractAddress(serviceConfigurationDTO.getServiceContractAddress());
        serviceConfiguration.setEndpoints(getServiceEndpoints(serviceConfigurationDTO.getEndpoints()));
        serviceConfiguration.setEnabled(serviceConfigurationDTO.isEnabled());

        serviceConfiguration.setServiceSecurityConfiguration(new ServiceSecurityConfiguration());
        serviceConfiguration.getServiceSecurityConfiguration().setEnabled(serviceConfigurationDTO.isSecurityEnabled());
        serviceConfiguration.getServiceSecurityConfiguration().setServiceSecurities(getServiceSecurityConfiguration(
                serviceConfigurationDTO.getServiceSecurities()));

        serviceConfiguration.setUser(userDAO.get(serviceConfigurationDTO.getUserId()));

        return serviceConfiguration;
    }

    /**
     * <p>Retrieves the service security configuration.</p>
     *
     * @param serviceSecurityConfiguration the service security configuration
     *
     * @return the service configuration
     */

    private List<ServiceSecurityDTO> getServiceSecurityConfiguration(
            ServiceSecurityConfiguration serviceSecurityConfiguration) {


        List<ServiceSecurityDTO> result = new ArrayList<ServiceSecurityDTO>();

        ServiceSecurityDTO serviceSecurityDTO;
        if (serviceSecurityConfiguration != null && serviceSecurityConfiguration.getServiceSecurities() != null) {
            for (ServiceSecurity serviceSecurity : serviceSecurityConfiguration.getServiceSecurities()) {

                serviceSecurityDTO = new ServiceSecurityDTO();
                serviceSecurityDTO.setSecurityTypeId(serviceSecurity.getSecurityType().getId());
                serviceSecurityDTO.setSecurityTypeName(serviceSecurity.getSecurityType().getName());

                if (serviceSecurity.getSecurityType().getId() == ModelConsts.SecurityTypes.WS_PASSWORD
                        || serviceSecurity.getSecurityType().getId() == ModelConsts.SecurityTypes.WS_DIGEST) {

                    serviceSecurityDTO.setDataSourceId(serviceSecurity.getDataSource().getId());
                } else if (serviceSecurity.getSecurityType().getId() == ModelConsts.SecurityTypes.WS_SIGNING) {

                    serviceSecurityDTO.setKeyStoreId(serviceSecurity.getKeyStore().getId());
                    serviceSecurityDTO.setClientAlias(serviceSecurity.getClientAlias());
                    serviceSecurityDTO.setServerAlias(serviceSecurity.getServerAlias());
                } else if (serviceSecurity.getSecurityType().getId() == ModelConsts.SecurityTypes.WS_ENCRYPTION) {

                    serviceSecurityDTO.setKeyStoreId(serviceSecurity.getKeyStore().getId());
                    serviceSecurityDTO.setClientAlias(serviceSecurity.getClientAlias());
                    serviceSecurityDTO.setServerAlias(serviceSecurity.getServerAlias());
                }

                result.add(serviceSecurityDTO);
            }
        }

        return result;
    }

    /**
     * <p>Retrieves the service security configuration.</p>
     *
     * @param serviceSecuritiesDTO the service security configuration
     *
     * @return the service configuration
     */
    private List<ServiceSecurity> getServiceSecurityConfiguration(List<ServiceSecurityDTO>
                                                                          serviceSecuritiesDTO) throws SoaDAOException {

        List<ServiceSecurity> serviceSecurities = new ArrayList<ServiceSecurity>();

        ServiceSecurity serviceSecurity;
        if (serviceSecuritiesDTO != null) {
            for (ServiceSecurityDTO serviceSecurityDTO : serviceSecuritiesDTO) {

                serviceSecurity = new ServiceSecurity();
                serviceSecurity.setSecurityType((SecurityType) lookupDAO.get(serviceSecurityDTO.getSecurityTypeId()));

                if (serviceSecurityDTO.getSecurityTypeId() == ModelConsts.SecurityTypes.WS_PASSWORD
                        || serviceSecurityDTO.getSecurityTypeId() == ModelConsts.SecurityTypes.WS_DIGEST) {

                    serviceSecurity.setDataSource(dataSourceDAO.get(serviceSecurityDTO.getDataSourceId()));
                } else if (serviceSecurityDTO.getSecurityTypeId() == ModelConsts.SecurityTypes.WS_SIGNING) {

                    serviceSecurity.setKeyStore(keyStoreDAO.get(serviceSecurityDTO.getKeyStoreId()));
                    serviceSecurity.setClientAlias(serviceSecurityDTO.getClientAlias());
                    serviceSecurity.setServerAlias(serviceSecurityDTO.getServerAlias());
                } else if (serviceSecurityDTO.getSecurityTypeId() == ModelConsts.SecurityTypes.WS_ENCRYPTION) {

                    serviceSecurity.setKeyStore(keyStoreDAO.get(serviceSecurityDTO.getKeyStoreId()));
                    serviceSecurity.setClientAlias(serviceSecurityDTO.getClientAlias());
                    serviceSecurity.setServerAlias(serviceSecurityDTO.getServerAlias());
                }

                serviceSecurities.add(serviceSecurity);
            }
        }

        return serviceSecurities;
    }

    /**
     * <p>Retrieves the list of service endpoints.</p>
     *
     * @param endpoints the list of endpoints
     *
     * @return the list of endpoints addresses
     */
    private List<String> getServiceEndpointsUrls(List<ServiceEndpoint> endpoints) {

        List<String> result = new ArrayList<String>();

        if (endpoints != null) {
            for (ServiceEndpoint endpoint : endpoints) {

                result.add(endpoint.getUrl());
            }
        }

        return result;
    }

    /**
     * <p>Retrieves the list of service endpoints.</p>
     *
     * @param endpoints the list of endpoints
     *
     * @return the list of endpoints addresses
     */
    private List<ServiceEndpoint> getServiceEndpoints(List<String> endpoints) {

        List<ServiceEndpoint> result = new ArrayList<ServiceEndpoint>();

        ServiceEndpoint serviceEndpoint;
        if (endpoints != null) {
            for (String endpoint : endpoints) {

                serviceEndpoint = new ServiceEndpoint();
                serviceEndpoint.setUrl(endpoint);
                result.add(serviceEndpoint);
            }
        }

        return result;
    }
}
