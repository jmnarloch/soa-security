/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.controller;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.service.registry.ServiceRegistry;
import org.soa.security.model.dto.DataSourceDTO;
import org.soa.security.model.dto.DatabaseDataSourceDTO;
import org.soa.security.service.DataSourceService;
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
 * <p>Data source controller.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Controller
public class DataSourceController extends BaseController {

    /**
     * <p>Instance of {@link DataSourceService} used for retrieving data sources.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private DataSourceService dataSourceService;

    /**
     * <p>Represents a instance of {@link org.soa.security.core.service.registry.ServiceRegistry}.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private ServiceRegistry serviceRegistry;

    /**
     * <p>Creates new instance of {@link DashboardController} class.</p>
     */
    public DataSourceController() {
        // empty constructor
    }

    /**
     * <p>Creates new instance of data source</p>
     *
     * @param dataSource the data source
     *
     * @return the id of the created data source
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/DataSource", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public long createDataSource(@RequestBody DatabaseDataSourceDTO dataSource) throws SoaServiceException {

        return dataSourceService.save(getAuthenticatedUser().getUserId(), dataSource);
    }

    /**
     * <p>Updates the data source.</p>
     *
     * @param dataSource the data source
     *
     * @throws SoaServiceException if any error occurs
     * @throws ServiceExecutionException if any error occurs
     */
    @RequestMapping(value = "/DataSource", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateDataSource(@RequestBody DatabaseDataSourceDTO dataSource) throws SoaServiceException,
            ServiceExecutionException {

        dataSourceService.update(getAuthenticatedUser().getUserId(), dataSource);

        // reconfigures the services
        serviceRegistry.configureAll();
    }

    /**
     * <p>Deletes the data source.</p>
     *
     * @param dataSourceId the data source id
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/DataSource", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteDataSource(@RequestParam long dataSourceId) throws SoaServiceException {

        dataSourceService.delete(getAuthenticatedUser().getUserId(), dataSourceId);
    }

    /**
     * <p>Retrieves the data source by it's id.</p>
     *
     * @param dataSourceId the data source id
     *
     * @return the data source
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/DataSource", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataSourceDTO getDataSourceById(@RequestParam long dataSourceId) throws SoaServiceException {

        return dataSourceService.get(getAuthenticatedUser().getUserId(), dataSourceId);
    }

    /**
     * <p>Handles request for all user authentication sources.</p>
     *
     * @return list of user authentication sources
     */
    @RequestMapping(value = "/DataSource/User", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DataSourceDTO> getDataSourcesByUserId() throws SoaServiceException {

        return dataSourceService.getByUserId(getAuthenticatedUser().getUserId());
    }
}
