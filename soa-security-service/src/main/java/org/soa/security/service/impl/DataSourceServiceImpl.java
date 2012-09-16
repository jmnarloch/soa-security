/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.impl;

import org.soa.security.dao.DataSourceDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.dto.DataSourceDTO;
import org.soa.security.model.entities.DataSource;
import org.soa.security.service.DataSourceService;
import org.soa.security.service.SoaServiceException;
import org.soa.security.service.mapper.Mappers;
import org.soa.security.service.mapper.impl.DataSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>A default implementation of {@link DataSourceService}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Service
@Transactional
public class DataSourceServiceImpl implements DataSourceService {

    /**
     * <p>Instance of {@link DataSourceDAO} used for persisting data sources.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private DataSourceDAO dataSourceDAO;

    /**
     * <p>Instance of {@link DataSourceMapper} used for converting the entities.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private DataSourceMapper dataSourceMapper;

    /**
     * <p>Creates new instance of {@link DataSourceServiceImpl} class.</p>
     */
    public DataSourceServiceImpl() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(long userId, DataSourceDTO dataSource) throws SoaServiceException {

        try {
            dataSource.setUserId(userId);

            return dataSourceDAO.save(dataSourceMapper.mapToEntity(dataSource));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when saving the data source.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(long userId, DataSourceDTO dataSource) throws SoaServiceException {

        try {
            validateUserPermission(userId, dataSource.getDataSourceId());

            dataSource.setUserId(userId);
            dataSource.setDataSourceId(dataSource.getDataSourceId());

            dataSourceDAO.update(dataSourceMapper.mapToEntity(dataSource));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when updating the data source.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DataSourceDTO> getByUserId(long userId) throws SoaServiceException {

        try {

            return Mappers.mapEntityCollection(dataSourceMapper, dataSourceDAO.getByUserId(userId));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the data sources.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public DataSourceDTO get(long userId, long id) throws SoaServiceException {

        try {
            validateUserPermission(userId, id);

            return get(id);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the data source.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public DataSourceDTO get(long id) throws SoaServiceException {

        try {

            return dataSourceMapper.mapToDto(dataSourceDAO.get(id));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the data source.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long userId, long dataSourceId) throws SoaServiceException {

        try {
            validateUserPermission(userId, dataSourceId);

            dataSourceDAO.delete(dataSourceId);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when deleting the key store.", e);
        }
    }

    /**
     * <p>Validates that the user may access the given data source from persistence.</p>
     *
     * @param userId       the user id
     * @param dataSourceId the data source id
     *
     * @throws SoaDAOException     if any error occurs
     * @throws SoaServiceException if the user may not access the given key store
     */
    private void validateUserPermission(long userId, long dataSourceId) throws SoaDAOException, SoaServiceException {

        DataSource dataSource = dataSourceDAO.get(dataSourceId);

        if (dataSource.getUser().getId() != userId) {
            throw new SoaServiceException("Unauthorized to access the specified resource.");
        }
    }
}
