/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.servlet;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.http.HttpContext;
import org.soa.security.core.http.impl.ServletHttpRequest;
import org.soa.security.core.http.impl.ServletHttpResponse;
import org.soa.security.core.service.dispatcher.ServiceDispatcher;
import org.soa.security.core.service.registry.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>A service dispatcher servlet.</p>
 *
 * <p>It's responsible for dispatching the incoming requests towards correct services</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServiceDispatcherServlet extends HttpServlet {

    /**
     * <p>Represents the instance of {@link ServiceDispatcher}.</p>
     */
    @Autowired
    private ServiceDispatcher serviceDispatcher;

    /**
     * <p>Represents a instance of {@link ServiceRegistry}.</p>
     */
    @Autowired
    private ServiceRegistry serviceRegistry;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        try {
            // creates the http context
            HttpContext httpContext = HttpContext.getInstance();

            // autowires the dependencies
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    servletConfig.getServletContext());

            // initializes the services from their configuration in the database
            serviceRegistry.configureAll();
        } catch (ServiceExecutionException e) {

            throw new ServletException("An error occurred when configuring the services.", e);
        }
    }

    /**
     * <p>Handles incoming service request dispatching to specified service.</p>
     *
     * @param req  http servlet request
     * @param resp http servlet response
     *
     * @throws ServletException
     * @throws IOException      if any I/O operation error occurs
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // intercepts incoming message and determines the target service

        // For web service request
        // #1 checks the validity of the request - mostly the request headers
        // #2 creates the SOAP message from the input
        // #3 processes the message using the interceptors
        // #4 forwards the service call to remote service
        // #5 processes the result (use interceptors)
        // #6 writes the result to servlet response

        // For REST service request
        // #1 validates the incoming message
        // #2 creates the REST message
        // #3 processes the message using the interceptors
        // #4 forwards the service call
        // #5 processes the result
        // #6 writes the result to servlet response

        try {
            // inits the http context
            HttpContext.getInstance().setHttpServletRequest(req);

            // delegates towards the dispatcher service
            serviceDispatcher.processRequest(new ServletHttpRequest(req), new ServletHttpResponse(resp));
        } catch (Exception e) {
            // propagates the exception
            throw new ServletException(e);
        }
    }
}
