/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.controller;

import org.soa.security.model.entities.DataSourceType;
import org.soa.security.model.entities.LookupEntity;
import org.soa.security.model.entities.RestSecurityType;
import org.soa.security.model.entities.SecurityType;
import org.soa.security.model.entities.ServiceType;
import org.soa.security.model.entities.UserRole;
import org.soa.security.model.entities.WebServiceSecurityType;
import org.soa.security.service.LookupService;
import org.soa.security.service.SoaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Lookup entities controller.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Controller
public class LookupController extends BaseController {

    /**
     * <p>Instance of {@link LookupService} used for retrieving the lookup entities.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private LookupService lookupService;

    /**
     * <p>Creates new instance of {@link LookupController} class.</p>
     */
    public LookupController() {
        // empty constructor
    }

    /**
     * <p>Handles request for all service types.</p>
     *
     * @return list of service types
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/Lookup/ServiceType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ServiceType> getServiceTypes() throws SoaServiceException {

        return getLookupEntities(lookupService.getServiceTypes(), ServiceType.class);
    }

    /**
     * <p>Handles request for all web service security types types.</p>
     *
     * @return list of service types
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/Lookup/WebServiceSecurityType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<WebServiceSecurityType> getWebServiceSecurityTypes() throws SoaServiceException {

        return getLookupEntities(lookupService.getWebServiceSecurityTypes(), WebServiceSecurityType.class);
    }

    /**
     * <p>Handles request for all rest security types types.</p>
     *
     * @return list of service types
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/Lookup/RestSecurityType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<RestSecurityType> getRestSecurityTypes() throws SoaServiceException {

        return getLookupEntities(lookupService.getRestSecurityTypes(), RestSecurityType.class);
    }

    /**
     * <p>Handles the request for all data source types.</p>
     *
     * @return the list of data source types
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/Lookup/DataSourceType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DataSourceType> getDataSourceTypes() throws SoaServiceException {

        return getLookupEntities(lookupService.getDataSourceTypes(), DataSourceType.class);
    }

    /**
     * <p>Handles the request for user roles.</p>
     *
     * @return the user roles
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/Lookup/UserRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserRole> getUserRoles() throws SoaServiceException {

        return getLookupEntities(lookupService.getUserRoles(), UserRole.class);
    }


    /**
     * <p>Retrieves the lookup entities.</p>
     *
     * @param lookupEntities the list of lookup entities
     * @param entityClass    the entity class
     * @param <T>            the type of the entity
     *
     * @return the list of the entities
     *
     * @throws SoaServiceException if any error occurs
     */
    private <T extends LookupEntity> List<T> getLookupEntities(List<T> lookupEntities, Class<T> entityClass) throws SoaServiceException {

        ArrayList<T> result = new ArrayList<T>();

        T defaultElement = createNewInstance(entityClass);
        defaultElement.setId(0);
        defaultElement.setName("-- Select --");
        result.add(defaultElement);
        result.addAll(lookupEntities);

        return result;
    }

    /**
     * <p>Creates new instance of the given entity type.</p>
     *
     * @param entityClass the entity class
     * @param <T>         the type of the entity class
     *
     * @return the new instance of the entity class
     */
    private <T extends LookupEntity> T createNewInstance(Class<T> entityClass) throws SoaServiceException {

        try {
            return entityClass.newInstance();
        } catch (InstantiationException e) {
            throw new SoaServiceException("Error occurred when creating object instance.", e);
        } catch (IllegalAccessException e) {
            throw new SoaServiceException("Error occurred when creating object instance.", e);
        }
    }
}
