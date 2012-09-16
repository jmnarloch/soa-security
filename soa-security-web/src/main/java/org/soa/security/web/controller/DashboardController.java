/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.controller;

import org.soa.security.web.UriAddress;
import org.soa.security.web.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Dashboard controller.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Controller
public class DashboardController extends BaseController {

    /**
     * <p>Creates new instance of {@link DashboardController} class.</p>
     */
    public DashboardController() {
        // empty constructor
    }

    /**
     * <p>Handles dashboard page request.</p>
     *
     * @return model and view
     */
    @RequestMapping(UriAddress.DASHBOARD)
    public ModelAndView dashboard() {

        return new ModelAndView(Views.DASHBOARD);
    }
}
