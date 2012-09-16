/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Entity;

/**
 * <p>Lookup entity defining the user role.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class UserRole extends LookupEntity {

    /**
     * <p>Creates new instance of {@link UserRole} class.</p>
     */
    public UserRole() {
        // empty constructor
    }
}
