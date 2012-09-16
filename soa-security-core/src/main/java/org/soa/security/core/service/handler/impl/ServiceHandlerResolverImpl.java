/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.handler.impl;

import org.soa.security.core.Helper;
import org.soa.security.core.service.NoSupportedHandlerException;
import org.soa.security.core.service.ServiceType;
import org.soa.security.core.service.handler.ServiceHandler;
import org.soa.security.core.service.handler.ServiceHandlerResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

/**
 * <p>The default implementation of {@link ServiceHandlerResolver}</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class ServiceHandlerResolverImpl implements ServiceHandlerResolver {

    /**
     * <p>Represents the list of supported error message handlers.</p>
     */
    @Resource
    private List<ServiceHandler> serviceHandlers;

    /**
     * <p>Creates new instance of {@link ServiceHandlerResolverImpl}.</p>
     */
    public ServiceHandlerResolverImpl() {
        // empty constructor
    }

    /**
     * <p>Validates that the class has been correctly initialized.</p>
     *
     * @throws IllegalStateException if the serviceHandlers hasn't been set
     */
    @PostConstruct
    public void init() {

        Helper.checkStateNotNull(serviceHandlers, "serviceHandlers");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceHandler resolveServiceHandler(ServiceType serviceType) throws NoSupportedHandlerException {

        for (ServiceHandler serviceHandler : serviceHandlers) {

            if (serviceHandler.getSupportedServiceType() == serviceType) {

                return serviceHandler;
            }
        }

        throw new NoSupportedHandlerException(MessageFormat.format("No handler has been found for service type {0}.",
                serviceType));
    }
}
