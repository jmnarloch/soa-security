/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service;

import org.soa.security.model.dto.DataSourceDTO;

import java.util.List;

/**
 * <p>A service for data sources.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface DataSourceService {

    /**
     * <p>Saves the data source.</p>
     *
     * @param userId     the user id
     * @param dataSource the data source
     *
     * @return the id of newly created data source
     *
     * @throws SoaServiceException if
     */
    long save(long userId, DataSourceDTO dataSource) throws SoaServiceException;

    /**
     * <p>Updates the data source.</p>
     *
     * @param userId     the user id
     * @param dataSource the data source
     *
     * @throws SoaServiceException if any error occurs
     */
    void update(long userId, DataSourceDTO dataSource) throws SoaServiceException;

    /**
     * <p>Retrieves all data sources for given user.</p>
     *
     * @param userId the user id
     *
     * @return list of all data sources
     *
     * @throws SoaServiceException if any error occurs
     */
    List<DataSourceDTO> getByUserId(long userId) throws SoaServiceException;

    /**
     * <p>Retrieves the data source.</p>
     *
     * @param userId the user id
     * @param id     the data source id
     *
     * @return the data source
     *
     * @throws SoaServiceException if any error occurs
     */
    DataSourceDTO get(long userId, long id) throws SoaServiceException;

    /**
     * <p>Retrieves the data source.</p>
     *
     * @param id     the data source id
     *
     * @return the data source
     *
     * @throws SoaServiceException if any error occurs
     */
    DataSourceDTO get(long id) throws SoaServiceException;

    /**
     * <p>Deletes the data source.</p>
     *
     * @param userId       the user id
     * @param dataSourceId the data source id
     *
     * @throws SoaServiceException if any error occurs
     */
    void delete(long userId, long dataSourceId) throws SoaServiceException;
}
