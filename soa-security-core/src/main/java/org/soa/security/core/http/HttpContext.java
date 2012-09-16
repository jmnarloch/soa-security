/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.http;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Represents the http context of the executing application.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public final class HttpContext {

    /**
     * <p>Represents the instance of {@link HttpContext}.</p>
     */
    private static HttpContext instance;

    /**
     * <p>Represents the servlet request.</p>
     */
    private final ThreadLocal<HttpServletRequest> servletRequest = new ThreadLocal<HttpServletRequest>();

    /**
     * <p>Creates new instance of {@link HttpContext} class.</p>
     */
    private HttpContext() {
        // empty constructor
    }

    /**
     * <p>Retrieves the application context path.</p>
     *
     * @return the application context path
     */
    public String getApplicationContextPath() {

        HttpServletRequest request = servletRequest.get();

        return new StringBuilder().append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append(request.getServletPath())
                .toString();
    }

    /**
     * <p>Sets the servlet context.</p>
     *
     * @param servletRequest the servlet request
     */
    public void setHttpServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest.set(servletRequest);
    }

    /**
     * <p>Retrieves the instance of {@link HttpContext}.</p>
     *
     * @return the instance of {@link HttpContext}
     */
    public static HttpContext getInstance() {

        synchronized (HttpContext.class) {
            if (instance == null) {
                instance = new HttpContext();
            }

            return instance;
        }
    }
}
