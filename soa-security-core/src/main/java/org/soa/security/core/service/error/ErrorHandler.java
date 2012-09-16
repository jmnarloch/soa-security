/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.error;

import org.soa.security.core.http.HttpResponse;

/**
 * <p>An error handler that creates error message directly in the response.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ErrorHandler {

    /**
     * <p>Handles error of specific type.</p>
     *
     * @param exc          the exception that occurred during execution
     * @param httpResponse the http response
     *
     * @throws Exception if any error occurs
     */
    void handleError(Exception exc, HttpResponse httpResponse) throws Exception;
}
