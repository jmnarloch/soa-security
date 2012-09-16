/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web;

/**
 * <p>Defines consts for view names.</p>
 *
 * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public final class Views {

    /**
     * <p>Represents the name of the admin view.</p>
     */
    public static final String ADMIN = "admin";

    /**
     * <p>Represents the name of the dashboard view.</p>
     */
    public static final String DASHBOARD = "dashboard";

    /**
     * <p>Creates new instance of {@link Views} class.</p>
     *
     * <p>Private constructor prevents instantiation outside this class.</p>
     */
    private Views() {
        // empty constructor
    }
}
