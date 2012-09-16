/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao.impl;

import org.soa.security.dao.ServiceSecurityDAO;
import org.soa.security.model.entities.ServiceSecurity;
import org.springframework.stereotype.Repository;

/**
 * <p>Defines methods for persisting {@link ServiceSecurity} entity.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Repository
public class HibernateServiceSecurityDAO extends HibernateGenericDAO<ServiceSecurity> implements ServiceSecurityDAO {

    /**
     * <p>Creates new instance of {@link HibernateServiceSecurityDAO} class.</p>
     */
    public HibernateServiceSecurityDAO() {
        // empty constructor
    }
}
