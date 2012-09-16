/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.error;

import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.service.ServiceType;

/**
 * <p>An service error handler.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceErrorHandler extends ErrorHandler {

    /**
     * <p>Retrieves the supported service type.</p>
     *
     * @return the supported service type.
     */
    ServiceType getSupportedServiceType();
}
