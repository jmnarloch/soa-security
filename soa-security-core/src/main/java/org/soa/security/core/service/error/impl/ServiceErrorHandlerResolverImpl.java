/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.error.impl;

import org.soa.security.core.Helper;
import org.soa.security.core.service.NoSupportedHandlerException;
import org.soa.security.core.service.ServiceType;
import org.soa.security.core.service.error.ErrorHandler;
import org.soa.security.core.service.error.ServiceErrorHandler;
import org.soa.security.core.service.error.ServiceErrorHandlerResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.List;

/**
 * <p>The default implementation of {@link org.soa.security.core.service.error.ServiceErrorHandlerResolver}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class ServiceErrorHandlerResolverImpl implements ServiceErrorHandlerResolver {

    /**
     * <p>Represents the list of supported error message handlers.</p>
     */
    @Autowired
    private List<ServiceErrorHandler> serviceErrorHandlers;

    /**
     * <p>Represents the default error handler.</p>
     */
    @Autowired
    @Qualifier("defaultErrorHandler")
    private ErrorHandler defaultErrorHandler;

    /**
     * <p>Creates new instance of {@link ServiceErrorHandlerResolverImpl}.</p>
     */
    public ServiceErrorHandlerResolverImpl() {
        // empty constructor
    }

    /**
     * <p>Validates that the class has been correctly initialized.</p>
     *
     * @throws IllegalStateException if the serviceErrorHandlers hasn't been set
     */
    @PostConstruct
    public void init() {

        Helper.checkStateNotNull(serviceErrorHandlers, "serviceErrorHandlers");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceErrorHandler resolveErrorHandler(ServiceType serviceType) throws NoSupportedHandlerException {

        for (ServiceErrorHandler serviceErrorHandler : serviceErrorHandlers) {

            if (serviceErrorHandler.getSupportedServiceType() == serviceType) {

                return serviceErrorHandler;
            }
        }

        throw new NoSupportedHandlerException(MessageFormat.format("No handler has been found for service type {0}.",
                serviceType));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ErrorHandler getDefaultErrorHandler() {

        return defaultErrorHandler;
    }
}
