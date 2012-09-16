/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.dispatcher.impl;

import org.soa.security.core.Helper;
import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.http.HttpRequest;
import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.service.NoSupportedHandlerException;
import org.soa.security.core.service.Service;
import org.soa.security.core.service.ServiceType;
import org.soa.security.core.service.dispatcher.ServiceDispatcher;
import org.soa.security.core.service.error.ErrorHandler;
import org.soa.security.core.service.error.ServiceErrorHandler;
import org.soa.security.core.service.error.ServiceErrorHandlerResolver;
import org.soa.security.core.service.handler.ServiceHandler;
import org.soa.security.core.service.handler.ServiceHandlerResolver;
import org.soa.security.core.service.manager.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>The default implementation of the {@link ServiceDispatcher}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class ServiceDispatcherImpl implements ServiceDispatcher {

    /**
     * <p>Represents the service manager.</p>
     */
    @Autowired
    private ServiceManager serviceManager;

    /**
     * <p>Service handler resolver.</p>
     */
    @Autowired
    private ServiceHandlerResolver serviceHandlerResolver;

    /**
     * <p>Resolves the error handler for specific service.</p>
     */
    @Autowired
    private ServiceErrorHandlerResolver serviceErrorHandlerResolver;

    /**
     * <p>Creates new instance of {@link ServiceDispatcher}.</p>
     */
    public ServiceDispatcherImpl() {
        // empty constructor
    }

    /**
     * <p>Validates that the class has been correctly initialized.</p>
     *
     * @throws IllegalStateException if the serviceHandlerResolver or serviceErrorHandlerResolver haven't been set
     */
    @PostConstruct
    public void init() {

        Helper.checkStateNotNull(serviceManager, "serviceManager");
        Helper.checkStateNotNull(serviceHandlerResolver, "serviceHandlerResolver");
        Helper.checkStateNotNull(serviceErrorHandlerResolver, "serviceErrorHandlerResolver");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processRequest(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {

        String serviceUrl;
        ServiceHandler serviceHandler;
        Service service = null;

        try {
            // retrieves the service name from the request
            serviceUrl = getServiceUrl(httpRequest);

            // retrieves the service information
            service = getService(serviceUrl);

            // retrieves the handler for specific service type
            serviceHandler = getServiceHandler(service.getServiceType());

            // performs delegates the execution to handler
            serviceHandler.handleRequest(service, httpRequest, httpResponse);
        } catch (ServiceExecutionException exc) {

            handleException(exc, service, httpResponse);
        }
    }

    /**
     * <p>Retrieves the service by it's url.</p>
     *
     * @param serviceUrl the service url
     *
     * @return the service
     *
     * @throws ServiceExecutionException if any error occurs
     */
    private Service getService(String serviceUrl) throws ServiceExecutionException {

        return serviceManager.getServiceByUrl(serviceUrl);
    }

    /**
     * <p>Retrieves the service handler by the service type.</p>
     *
     * @param serviceType the service type
     *
     * @return the service handler capable of handling specific service type
     *
     * @throws NoSupportedHandlerException if no handler has been found
     */
    private ServiceHandler getServiceHandler(ServiceType serviceType) throws NoSupportedHandlerException {

        return serviceHandlerResolver.resolveServiceHandler(serviceType);
    }

    /**
     * <p>Retrieves the name of the called service from the request.</p>
     *
     * @param httpRequest the http request
     *
     * @return the name of the called service
     */
    private String getServiceUrl(HttpRequest httpRequest) {

        String requestUri;
        int index = -1;
        int endIndex = -1;

        requestUri = httpRequest.getRequestUri();

        for(int i = 0; i < 3; i++) {

            index = requestUri.indexOf("/", index + 1);
        }

        endIndex = requestUri.indexOf("/", index + 1);

        // otherwise the whole request contains only the service name
        if (index == -1) {
            index = requestUri.length();
        }

        if(endIndex != -1) {
            return requestUri.substring(index, endIndex);
        } else  {
            return requestUri.substring(index);
        }
    }

    /**
     * <p>Handles the service execution error.</p>
     *
     * @param exc          the service execution error
     * @param service      the service
     * @param httpResponse the http response
     */
    private void handleException(ServiceExecutionException exc, Service service, HttpResponse httpResponse)
            throws Exception {

        ErrorHandler errorHandler;

        try {

            if (service != null && service.getServiceType() != null) {
                // retrieves the error handler
                errorHandler = getServiceErrorHandler(service.getServiceType());
            } else {

                errorHandler = getDefaultErrorHandler();
            }

            // handles the error
            errorHandler.handleError(exc, httpResponse);
        } catch (NoSupportedHandlerException e) {

            // ignores the exception
        }
    }

    /**
     * <p>Retrieves the error handler for specific service type.</p>
     *
     * @param serviceType the service type
     *
     * @return the service error handler
     *
     * @throws NoSupportedHandlerException if no handler has been found
     */
    private ServiceErrorHandler getServiceErrorHandler(ServiceType serviceType) throws NoSupportedHandlerException {

        return serviceErrorHandlerResolver.resolveErrorHandler(serviceType);
    }

    /**
     * <p>Retrieves the default error handler.</p>
     *
     * @return the default error handler
     */
    public ErrorHandler getDefaultErrorHandler() {

        return serviceErrorHandlerResolver.getDefaultErrorHandler();
    }
}
