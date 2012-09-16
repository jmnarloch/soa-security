/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.core.service.dispatcher;

import org.soa.security.core.http.HttpRequest;
import org.soa.security.core.http.HttpResponse;

/**
 * <p>The message dispatcher, processes the incoming requests and dispatches them to services.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface ServiceDispatcher {

    /**
     * <p>Processes the incoming request.</p>
     *
     * @param httpRequest  the http request
     * @param httpResponse the http response
     *
     * @throws Exception if any error occurs
     */
    void processRequest(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception;
}
