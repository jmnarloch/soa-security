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
 * <p>Administrator controller.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Controller
public class AdminController extends BaseController {

    /**
     * <p>Creates new instance of {@link AdminController} class.</p>
     */
    public AdminController() {
        // empty constructor
    }

    /**
     * <p>Handles admin page request.</p>
     *
     * @return model and view
     */
    @RequestMapping(UriAddress.ADMIN)
    public ModelAndView admin() {

        return new ModelAndView(Views.ADMIN);
    }
}
