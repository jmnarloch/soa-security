/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao.impl;

import org.hibernate.HibernateException;
import org.soa.security.dao.KeyStoreDAO;
import org.soa.security.dao.SoaDAOException;
import org.soa.security.model.entities.KeyStore;
import org.soa.security.model.entities.KeyStoreAlias;
import org.soa.security.model.entities.KeyStoreFile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Defines methods for persisting {@link KeyStore} entity.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Repository
public class HibernateKeyStoreDAO extends HibernateGenericDAO<KeyStore> implements KeyStoreDAO {

    /**
     * <p>Creates new instance of {@link HibernateKeyStoreDAO} class.</p>
     */
    public HibernateKeyStoreDAO() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KeyStore> getByUserId(long userId) throws SoaDAOException {

        try {
            return getSession().createQuery("from KeyStore k where k.user.id = :userId")
                    .setParameter("userId", userId).list();
        } catch (HibernateException e) {
            throw new SoaDAOException("Error occurred when retrieving list of user key stores.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getContent(long keyStoreId) throws SoaDAOException {

        try {

            KeyStoreFile keyStoreFile = (KeyStoreFile) getSession().createQuery(
                    "select f from KeyStore k join k.keyStoreFile f where k.id = :id")
                    .setParameter("id", keyStoreId)
                    .uniqueResult();

            return keyStoreFile.getContent();
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when retrieving list of user key stores.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getKeyStoreAliases(long keyStoreId) throws SoaDAOException {

        List<String> result = new ArrayList<String>();

        try {

            List<KeyStoreAlias> aliases = getSession().createQuery("select k.keyStoreAliases from KeyStore k where k.id = :id")
                    .setParameter("id", keyStoreId).list();

            if (aliases != null) {

                for (KeyStoreAlias alias : aliases) {

                    result.add(alias.getName());
                }
            }

            return result;
        } catch (HibernateException e) {

            throw new SoaDAOException("Error occurred when retrieving list of key stores alases.", e);
        }
    }


}
