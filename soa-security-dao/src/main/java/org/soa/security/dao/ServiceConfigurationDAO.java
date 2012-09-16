/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao;

import org.soa.security.model.entities.ServiceConfiguration;

import java.util.List;

/**
 * <p>Defines method need for manipulating {@link ServiceConfiguration} in the persistence. It extends the {@link
 * GenericDAO}.</p>
 *
 * <p><strong>Thread safety:</strong> The implementation of this interface should be thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceConfigurationDAO extends GenericDAO<ServiceConfiguration> {

    /**
     * <p>Retrieves the list of all service configurations create by user.</p>
     *
     * @param userId the user id
     * @return list of all service configurations created by user
     * @throws SoaDAOException if any error occurs
     */
    List<ServiceConfiguration> getByUserId(long userId) throws SoaDAOException;
}
