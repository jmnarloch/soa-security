/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.mapper.impl;

import org.soa.security.dao.LookupDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.dao.UserDAO;
import org.soa.security.model.dto.DataSourceDTO;
import org.soa.security.model.dto.DatabaseDataSourceDTO;
import org.soa.security.model.entities.DataSource;
import org.soa.security.model.entities.DataSourceType;
import org.soa.security.model.entities.DatabaseDataSource;
import org.soa.security.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>A {@link DataSource} mapper.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class DataSourceMapper implements Mapper<DataSource, DataSourceDTO> {

    /**
     * <p>Represents the instance of {@link UserDAO} class.</p>
     */
    @Autowired
    private LookupDAO lookupDAO;

    /**
     * <p>Represents the instance of {@link UserDAO} class.</p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <p>Creates new instance of {@link DataSourceMapper} class.</p>
     */
    public DataSourceMapper() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataSourceDTO mapToDto(DataSource dataSource) {

        DataSourceDTO dataSourceDTO = null;

        if (dataSource instanceof DatabaseDataSource) {

            DatabaseDataSource databaseDataSource = (DatabaseDataSource) dataSource;

            dataSourceDTO = new DatabaseDataSourceDTO();
            ((DatabaseDataSourceDTO) dataSourceDTO).setConnectionUrl(databaseDataSource.getConnectionUrl());
            ((DatabaseDataSourceDTO) dataSourceDTO).setDriverClass(databaseDataSource.getDriverClass());
            ((DatabaseDataSourceDTO) dataSourceDTO).setUserName(databaseDataSource.getUserName());
            // TODO the user password is being omitted
            ((DatabaseDataSourceDTO) dataSourceDTO).setUserPassword(databaseDataSource.getUserPassword());
            ((DatabaseDataSourceDTO) dataSourceDTO).setUserQuery(databaseDataSource.getUserQuery());
        }

        dataSourceDTO.setDataSourceId(dataSource.getId());
        dataSourceDTO.setDataSourceTypeId(dataSource.getDataSourceType().getId());
        dataSourceDTO.setDataSourceTypeName(dataSource.getDataSourceType().getName());
        dataSourceDTO.setDataSourceName(dataSource.getName());
        dataSourceDTO.setUserId(dataSource.getUser().getId());

        return dataSourceDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataSource mapToEntity(DataSourceDTO dataSourceDTO) throws SoaDAOException {

        DataSource dataSource = null;

        if (dataSourceDTO instanceof DatabaseDataSourceDTO) {

            DatabaseDataSourceDTO databaseDataSourceDTO = (DatabaseDataSourceDTO) dataSourceDTO;

            dataSource = new DatabaseDataSource();
            ((DatabaseDataSource) dataSource).setConnectionUrl(databaseDataSourceDTO.getConnectionUrl());
            ((DatabaseDataSource) dataSource).setDriverClass(databaseDataSourceDTO.getDriverClass());
            ((DatabaseDataSource) dataSource).setUserName(databaseDataSourceDTO.getUserName());
            ((DatabaseDataSource) dataSource).setUserPassword(databaseDataSourceDTO.getUserPassword());
            ((DatabaseDataSource) dataSource).setUserQuery(databaseDataSourceDTO.getUserQuery());
        }

        dataSource.setId(dataSourceDTO.getDataSourceId());
        dataSource.setDataSourceType((DataSourceType) lookupDAO.get(dataSourceDTO.getDataSourceTypeId()));
        dataSource.setName(dataSourceDTO.getDataSourceName());
        dataSource.setUser(userDAO.get(dataSourceDTO.getUserId()));

        return dataSource;
    }
}
