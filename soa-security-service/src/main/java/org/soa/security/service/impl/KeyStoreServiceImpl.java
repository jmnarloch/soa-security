/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.service.impl;

import org.soa.security.dao.KeyStoreDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.dto.KeyStoreDTO;
import org.soa.security.model.entities.KeyStore;
import org.soa.security.model.entities.KeyStoreAlias;
import org.soa.security.model.entities.KeyStoreFile;
import org.soa.security.service.KeyStoreService;
import org.soa.security.service.SoaServiceException;
import org.soa.security.service.mapper.Mappers;
import org.soa.security.service.mapper.impl.KeyStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>A default implementation of {@link KeyStoreService}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Service
@Transactional
public class KeyStoreServiceImpl implements KeyStoreService {

    /**
     * <p>Instance of {@link KeyStoreDAO} used for persisting key stores.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private KeyStoreDAO keyStoreDAO;

    /**
     * <p>Instance of {@link KeyStoreMapper} used for converting the entities.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private KeyStoreMapper keyStoreMapper;

    /**
     * <p>Creates new instance of {@link KeyStoreServiceImpl} class.</p>
     */
    public KeyStoreServiceImpl() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long save(long userId, KeyStoreDTO keyStore) throws SoaServiceException {

        try {
            keyStore.setUserId(userId);

            return keyStoreDAO.save(keyStoreMapper.mapToEntity(keyStore));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when saving the key store.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveKeyStoreFile(long userId, long keyStoreId, byte[] keyStoreContent) throws SoaServiceException {

        try {
            validateUserPermission(userId, keyStoreId);

            // retrieves the key store
            KeyStore keyStore = keyStoreDAO.get(keyStoreId);

            // sets the key store content
            keyStore.setKeyStoreFile(new KeyStoreFile());
            keyStore.getKeyStoreFile().setContent(keyStoreContent);

            // TODO retrieve the key store aliases
            keyStore.setKeyStoreAliases(getKeyStoreAliases(keyStore, keyStoreContent));

            // updates the key store
            keyStoreDAO.update(keyStore);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when updating the key store.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(long userId, KeyStoreDTO keyStore) throws SoaServiceException {

        try {
            validateUserPermission(userId, keyStore.getKeyStoreId());

            keyStore.setUserId(userId);

            keyStoreDAO.update(keyStoreMapper.mapToEntity(keyStore));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when updating the key store.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<KeyStoreDTO> getByUserId(long userId) throws SoaServiceException {

        try {

            return Mappers.mapEntityCollection(keyStoreMapper, keyStoreDAO.getByUserId(userId));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the key stores.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public KeyStoreDTO get(long id) throws SoaServiceException {

        try {

            return keyStoreMapper.mapToDto(keyStoreDAO.get(id));
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the key store.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public KeyStoreDTO get(long userId, long id) throws SoaServiceException {

        try {
            validateUserPermission(userId, id);

            return get(id);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the key store.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public byte[] getContent(long userId, long id) throws SoaServiceException {

        try {
            validateUserPermission(userId, id);

            return getContent(id);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the key store.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public byte[] getContent(long id) throws SoaServiceException {

        try {

            return keyStoreDAO.getContent(id);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the key store.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long userId, long keyStoreId) throws SoaServiceException {

        try {
            validateUserPermission(userId, keyStoreId);

            keyStoreDAO.delete(keyStoreId);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when deleting the key store.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getKeyStoreAliases(long userId, long keyStoreId) throws SoaServiceException {

        try {
            validateUserPermission(userId, keyStoreId);

            return keyStoreDAO.getKeyStoreAliases(keyStoreId);
        } catch (SoaDAOException e) {

            throw new SoaServiceException("An error occurred when retrieving the key store aliases.", e);
        }
    }

    /**
     * <p>Validates that the user may access the given key store from persistence.</p>
     *
     * @param userId     the user id
     * @param keyStoreId the key store id
     *
     * @throws SoaDAOException     if any error occurs
     * @throws SoaServiceException if the user may not access the given key store
     */
    private void validateUserPermission(long userId, long keyStoreId) throws SoaDAOException, SoaServiceException {

        KeyStore keyStore = keyStoreDAO.get(keyStoreId);

        if (keyStore.getUser().getId() != userId) {
            throw new SoaServiceException("Unauthorized to access the specified resource.");
        }
    }

    /**
     * <p>Retrieves the list of key store aliases.</p>
     *
     * @param keyStore        the key store
     * @param keyStoreContent the key store content
     *
     * @return the list of key store aliases
     *
     * @throws SoaServiceException if any error occurs
     */
    private List<KeyStoreAlias> getKeyStoreAliases(KeyStore keyStore, byte[] keyStoreContent) throws SoaServiceException {

        ByteArrayInputStream inputStream = null;
        char[] password = null;
        try {
            inputStream = new ByteArrayInputStream(keyStoreContent);

            if (keyStore.getKeyStorePassword() != null && !keyStore.getKeyStorePassword().isEmpty()) {

                password = keyStore.getKeyStorePassword().toCharArray();
            }

            java.security.KeyStore tmp = java.security.KeyStore.getInstance(java.security.KeyStore.getDefaultType());
            tmp.load(inputStream, password);

            List<KeyStoreAlias> result = new ArrayList<KeyStoreAlias>();
            Enumeration<String> enumeration = tmp.aliases();

            KeyStoreAlias alias;
            while (enumeration.hasMoreElements()) {
                alias = new KeyStoreAlias();
                alias.setName(enumeration.nextElement());

                result.add(alias);
            }

            return result;
        } catch (KeyStoreException e) {

            throw new SoaServiceException("An error occurred when reading the key store content.", e);
        } catch (CertificateException e) {

            throw new SoaServiceException("An error occurred when reading the key store content.", e);
        } catch (NoSuchAlgorithmException e) {

            throw new SoaServiceException("An error occurred when reading the key store content.", e);
        } catch (IOException e) {

            throw new SoaServiceException("An error occurred when reading the key store content.", e);
        } finally {

            if (inputStream != null) {

                try {
                    inputStream.close();
                } catch (IOException e) {
                    // ignores exception
                }
            }
        }
    }
}
