/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service;

import org.soa.security.model.dto.KeyStoreDTO;

import java.util.List;

/**
 * <p>A service for key store.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface KeyStoreService {

    /**
     * <p>Saves the key store.</p>
     *
     * @param userId   the user id
     * @param keyStore the key store
     *
     * @return the id of newly created key store
     *
     * @throws SoaServiceException if any error occurs
     */
    long save(long userId, KeyStoreDTO keyStore) throws SoaServiceException;

    /**
     * <p>Saves the key store content.</p>
     *
     * @param userId          the user id
     * @param keyStoreId      the key store id
     * @param keyStoreContent the key store content
     *
     * @throws SoaServiceException if any error occurs
     */
    void saveKeyStoreFile(long userId, long keyStoreId, byte[] keyStoreContent) throws SoaServiceException;

    /**
     * <p>Updates the key store.</p>
     *
     * @param userId   the user id
     * @param keyStore the key store
     *
     * @throws SoaServiceException if any error occurs
     */
    void update(long userId, KeyStoreDTO keyStore) throws SoaServiceException;

    /**
     * <p>Retrieves all key stores for given user.</p>
     *
     * @param userId the user id
     *
     * @return list of all user key stores
     *
     * @throws SoaServiceException if any error occurs
     */
    List<KeyStoreDTO> getByUserId(long userId) throws SoaServiceException;

    /**
     * <p>Retrieves the key store by it's id.</p>
     *
     * @param id the key store id
     *
     * @return the key store
     *
     * @throws SoaServiceException if any error occurs
     */
    KeyStoreDTO get(long id) throws SoaServiceException;

    /**
     * <p>Retrieves the key store by it's id.</p>
     *
     * @param userId the user id
     * @param id     the key store id
     *
     * @return the key store
     *
     * @throws SoaServiceException if any error occurs
     */
    KeyStoreDTO get(long userId, long id) throws SoaServiceException;

    /**
     * <p>Retrieves the key store content.</p>
     *
     * @param id     the key store id
     * @param userId the user id
     *
     * @return the key store content
     *
     * @throws SoaServiceException if any error occurs
     */
    byte[] getContent(long userId, long id) throws SoaServiceException;

    /**
     * <p>Retrieves the key store content.</p>
     *
     * @param id the key store id
     *
     * @return the key store content
     *
     * @throws SoaServiceException if any error occurs
     */
    byte[] getContent(long id) throws SoaServiceException;

    /**
     * <p>Deletes the key store.</p>
     *
     * @param userId     the user id
     * @param keyStoreId the key store id
     *
     * @throws SoaServiceException if any error occurs
     */
    void delete(long userId, long keyStoreId) throws SoaServiceException;

    /**
     * <p>Retrieves the list of key store aliases.</p>
     *
     * @param keyStoreId the key store id
     *
     * @return the list of key store aliases
     *
     * @throws SoaServiceException if any error occurs
     */
    List<String> getKeyStoreAliases(long userId, long keyStoreId) throws SoaServiceException;
}
