/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.mapper;

import org.soa.security.dao.SoaDAOException;

/**
 * <p>Maps the entity into the DTO object.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface Mapper<TEntity, TDto> {

    /**
     * <p>Maps the passed entity into a dto.</p>
     *
     * @param entity the entity  instance
     *
     * @return the dto instance created based on the passed object
     */
    TDto mapToDto(TEntity entity);

    /**
     * <p>Maps the passed dto into an entity.</p>
     *
     * @param dto the dto instance
     *
     * @return the entity instance created based on the passed object
     *
     * @throws SoaDAOException if any error occurs
     */
    TEntity mapToEntity(TDto dto) throws SoaDAOException;
}
