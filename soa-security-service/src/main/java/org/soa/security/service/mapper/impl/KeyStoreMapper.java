/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.mapper.impl;

import org.soa.security.dao.SoaDAOException;
import org.soa.security.dao.UserDAO;
import org.soa.security.model.dto.KeyStoreDTO;
import org.soa.security.model.entities.KeyStore;
import org.soa.security.service.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>A {@link KeyStore} mapper.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class KeyStoreMapper implements Mapper<KeyStore, KeyStoreDTO> {

    /**
     * <p>Represents the instance of {@link UserDAO} class.</p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <p>Creates new instance of {@link KeyStoreMapper} class.</p>
     */
    public KeyStoreMapper() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyStoreDTO mapToDto(KeyStore keyStore) {

        KeyStoreDTO keyStoreDTO = new KeyStoreDTO();
        keyStoreDTO.setKeyStoreId(keyStore.getId());
        keyStoreDTO.setKeyStoreName(keyStore.getName());
        keyStoreDTO.setKeyStorePassword(keyStore.getKeyStorePassword());
        keyStoreDTO.setKeyPassword(keyStore.getKeyPassword());
        keyStoreDTO.setUserId(keyStore.getUser().getId());

        // passing the key store content back to view through DTO has been disabled
        // the dto itself is being used for displaying the key store information
        // keyStoreDTO.setKeyStoreFile(keyStore.getKeyStoreFile());

        return keyStoreDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyStore mapToEntity(KeyStoreDTO keyStoreDTO) throws SoaDAOException {

        KeyStore keyStore = new KeyStore();
        keyStore.setId(keyStoreDTO.getKeyStoreId());
        keyStore.setName(keyStoreDTO.getKeyStoreName());
        keyStore.setKeyStorePassword(keyStoreDTO.getKeyStorePassword());
        keyStore.setKeyPassword(keyStoreDTO.getKeyPassword());
        keyStore.setUser(userDAO.get(keyStoreDTO.getUserId()));

        return keyStore;
    }
}
