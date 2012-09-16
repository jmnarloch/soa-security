/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.error.impl;

import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.service.error.ErrorHandler;
import org.springframework.stereotype.Component;

/**
 * <p>A default error handler.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class DefaultErrorHandler implements ErrorHandler {

    /**
     * <p>Creates new instance of {@link DefaultErrorHandler} class.</p>
     */
    public DefaultErrorHandler() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleError(Exception exc, HttpResponse httpResponse) throws Exception {

        // simply throws the exception, leaving the handling to the application server
        throw exc;
    }
}
