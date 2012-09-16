/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.mapper.impl;

import org.soa.security.dao.LookupDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.dto.UserDTO;
import org.soa.security.model.entities.UserInfo;
import org.soa.security.model.entities.UserRole;
import org.soa.security.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>A {@link UserInfo} mapper.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class UserMapper implements Mapper<UserInfo, UserDTO> {

    /**
     * <p>Represents the instance of {@link LookupDAO} class.</p>
     */
    @Autowired
    private LookupDAO lookupDAO;

    /**
     * <p>Creates new instance of {@link UserMapper} class.</p>
     */
    public UserMapper() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO mapToDto(UserInfo userInfo) {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userInfo.getId());
        userDTO.setUserName(userInfo.getUserName());
        // the password is not being passed by to the view - can not be read
        // userDTO.setUserPassword(userInfo.getPassword());
        userDTO.setEmailAddress(userInfo.getEmailAddress());
        userDTO.setEnabled(userInfo.isEnabled());
        userDTO.setUserRoleId(userInfo.getUserRole().getId());
        userDTO.setUserRoleName(userInfo.getUserRole().getName());

        return userDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo mapToEntity(UserDTO userDTO) throws SoaDAOException {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(userDTO.getUserId());
        userInfo.setUserName(userDTO.getUserName());
        userInfo.setPassword(userDTO.getUserPassword());
        userInfo.setEmailAddress(userDTO.getEmailAddress());
        userInfo.setEnabled(userDTO.isEnabled());
        userInfo.setUserRole((UserRole) lookupDAO.get(userDTO.getUserRoleId()));

        return userInfo;
    }
}
