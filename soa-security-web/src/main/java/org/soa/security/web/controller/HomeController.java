/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.controller;

import org.soa.security.model.ModelConsts;
import org.soa.security.web.UriAddress;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Represents home controller.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Controller
public class HomeController extends BaseController {

    /**
     * <p>Creates new instance of {@link HomeController}</p>
     */
    public HomeController() {
        // empty constructor
    }

    /**
     * <p>Handles the request.</p>
     *
     * @return the view name
     */
    @RequestMapping(UriAddress.HOME)
    public String home(Authentication authentication) {

        if (hasAdminPrivileges(authentication)) {

            return "redirect:Admin";
        }

        return "redirect:Dashboard";
    }

    /**
     * <p>Returns whether the user has admin privileges.</p>
     *
     * @param authentication the authentication
     *
     * @return true if user has admin privileges
     */
    private boolean hasAdminPrivileges(Authentication authentication) {

        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {

            if (ModelConsts.UserRoleNames.ADMIN.equals(grantedAuthority.getAuthority())) {

                return true;
            }
        }

        return false;
    }
}
