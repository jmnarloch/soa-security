/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao;

import org.soa.security.model.entities.KeyStore;

import java.util.List;

/**
 * <p>Defines method need for manipulating {@link KeyStore} in the persistence. It extends the {@link
 * GenericDAO}.</p>
 *
 * <p><strong>Thread safety:</strong> The implementation of this interface should be thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface KeyStoreDAO extends GenericDAO<KeyStore> {

    /**
     * <p>Retrieves the list of all key stores create by user.</p>
     *
     * @param userId the user id
     *
     * @return list of all key stores created by user
     *
     * @throws SoaDAOException if any error occurs
     */
    List<KeyStore> getByUserId(long userId) throws SoaDAOException;

    /**
     * <p>Retrieves the content of key store.</p>
     *
     * @param keyStoreId the key store id
     *
     * @return the key store content
     *
     * @throws SoaDAOException if any error occurs when retrieving the key store
     */
    byte[] getContent(long keyStoreId) throws SoaDAOException;

    /**
     * <p>Retrieves the list of key store aliases.</p>
     *
     * @param keyStoreId the key store id
     *
     * @return the list of key store aliases
     *
     * @throws SoaDAOException if any error occurs
     */
    List<String> getKeyStoreAliases(long keyStoreId) throws SoaDAOException;
}
