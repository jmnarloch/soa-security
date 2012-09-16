/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.handler;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.http.HttpRequest;
import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.service.Service;
import org.soa.security.core.service.ServiceType;

/**
 * <p>A handler that process the request to the specific service.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceHandler {

    /**
     * <p>Retrieves the supported service type.</p>
     *
     * @return the supported service type.
     */
    ServiceType getSupportedServiceType();

    /**
     * <p>Handles the request to specific service.</p>
     *
     * @param service      the called service
     * @param httpRequest  the http request
     * @param httpResponse the http response
     *
     * @throws ServiceExecutionException if any error occurs during service execution
     */
    void handleRequest(Service service, HttpRequest httpRequest, HttpResponse httpResponse)
            throws ServiceExecutionException;
}
