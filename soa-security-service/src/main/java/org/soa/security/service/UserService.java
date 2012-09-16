/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service;

import org.soa.security.model.dto.UserDTO;

import java.util.List;

/**
 * <p>A service for users.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface UserService {

    /**
     * <p>Saves the user.</p>
     *
     * @param userId the user id
     * @param user   the user
     *
     * @return the id of newly created key store
     *
     * @throws SoaServiceException if
     */
    long save(long userId, UserDTO user) throws SoaServiceException;

    /**
     * <p>Updates the user.</p>
     *
     * @param userId the user id
     * @param user   the user
     *
     * @throws SoaServiceException if any error occurs
     */
    void update(long userId, UserDTO user) throws SoaServiceException;

    /**
     * <p>Retrieves all users.</p>
     *
     * @param userId the user id
     *
     * @return list of all user
     *
     * @throws SoaServiceException if any error occurs
     */
    List<UserDTO> getAll(long userId) throws SoaServiceException;

    /**
     * <p>Retrieves the user.</p>
     *
     * @param userId the user id
     * @param id     the user id
     *
     * @return the user
     *
     * @throws SoaServiceException if any error occurs
     */
    UserDTO get(long userId, long id) throws SoaServiceException;

    /**
     * <p>Retrieves </p>
     *
     * @param userName the user name
     *
     * @return the user
     *
     * @throws SoaServiceException if any error occurs
     */
    UserDTO getByUserName(String userName) throws SoaServiceException;

    /**
     * <p>Deletes the user.</p>
     *
     * @param userId the user id
     * @param id     the user id
     *
     * @throws SoaServiceException if any error occurs
     */
    void delete(long userId, long id) throws SoaServiceException;

    /**
     * <p>Returns whether the user with given name exists.</p>
     *
     * @param userName the user name
     *
     * @return true if user exists, false otherwise
     *
     * @throws SoaServiceException if any error occurs
     */
    boolean isUserExists(String userName) throws SoaServiceException;

    /**
     * <p>Enables the user.</p>
     *
     * @param userId the user id
     * @param id     the user id
     * @param enable whether to enable the user
     *
     * @throws SoaServiceException if any error occurs
     */
    void enableUser(long userId, long id, boolean enable) throws SoaServiceException;
}
