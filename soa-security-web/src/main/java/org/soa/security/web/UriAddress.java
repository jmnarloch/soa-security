/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web;

/**
 * <p>Defines consts for uri addresses.</p>
 *
 * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public final class UriAddress {

    /**
     * <p>Represents the url that maps to home page.</p>
     */
    public static final String HOME = "/Home";

    /**
     * <p>Represents the url that maps to Admin page.</p>
     */
    public static final String ADMIN = "/Admin";

    /**
     * <p>Represents the url that maps to Dashboard page.</p>
     */
    public static final String DASHBOARD = "/Dashboard";
    
    /**
     * <p>Creates new instance of {@link UriAddress} class.</p>
     * 
     * <p>Private constructor prevents from instantiation outside this class.</p>
     */
    private UriAddress() {
        // empty constructor
    }
}
