/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.controller;

import org.soa.security.web.authentication.ApplicationUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>Represents a base controller for controllers used in this component.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public abstract class BaseController {

    /**
     * <p>Creates new instance of {@link BaseController} class.</p>
     */
    protected BaseController() {
        // empty constructor
    }

    /**
     * <p>Retrieves the id of the logged user.</p>
     *
     * @return the id of the logged user
     */
    protected ApplicationUser getAuthenticatedUser() {

        // retrieves the authentication context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // returns the user details
        return (ApplicationUser) auth.getPrincipal();
    }
}
