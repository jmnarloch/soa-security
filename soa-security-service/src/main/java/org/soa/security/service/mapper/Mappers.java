/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A utility class used with mappers.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class Mappers {

    /**
     * <p>An utility method that maps the content of entire list into the desires DTO type.</p>
     *
     * @param mapper    the mapper instance
     * @param entities  the collection of entities
     * @param <TEntity> the type of entity
     * @param <TDto>    the type of DTO
     *
     * @return the result list that contains the mapped entities
     */
    public static <TEntity, TDto> List<TDto> mapEntityCollection(Mapper<TEntity, TDto> mapper, List<TEntity> entities) {

        List<TDto> result = new ArrayList<TDto>();

        for(TEntity entity : entities) {

            result.add(mapper.mapToDto(entity));
        }

        return result;
    }
}
