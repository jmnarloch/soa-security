/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao;

import org.soa.security.model.entities.DataSource;
import org.soa.security.model.entities.ServiceConfiguration;

import java.util.List;

/**
 * <p>Defines method need for manipulating {@link DataSource} in the persistence. It extends the {@link
 * GenericDAO}.</p>
 *
 * <p><strong>Thread safety:</strong> The implementation of this interface should be thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface DataSourceDAO extends GenericDAO<DataSource> {

    /**
     * <p>Retrieves the list of all authentication sources create by user.</p>
     *
     * @param userId the user id
     * @return list of all authentication sources created by user
     * @throws SoaDAOException if any error occurs
     */
    List<DataSource> getByUserId(long userId) throws SoaDAOException;
}
