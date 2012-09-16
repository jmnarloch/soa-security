/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.handler;

import org.soa.security.core.service.NoSupportedHandlerException;
import org.soa.security.core.service.ServiceType;

/**
 * <p>A resolver that retrieves the service handler capable of handling specific service type.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceHandlerResolver {

    /**
     * <p>Resolves the error handler for specific service type.</p>
     *
     * @param serviceType the type of the service
     *
     * @return the message error handler that supports service type
     *
     * @throws NoSupportedHandlerException if no handler has been found
     */
    ServiceHandler resolveServiceHandler(ServiceType serviceType) throws NoSupportedHandlerException;
}
