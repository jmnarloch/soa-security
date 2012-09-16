/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.dao;

import org.soa.security.model.entities.ServiceSecurity;

/**
 * <p>Defines method need for manipulating {@link ServiceSecurity} in the persistence. It extends the {@link
 * GenericDAO}.</p>
 *
 * <p><strong>Thread safety:</strong> The implementation of this interface should be thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceSecurityDAO extends GenericDAO<ServiceSecurity> {
}
