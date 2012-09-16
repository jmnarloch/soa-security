/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.wsdl;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.service.Service;

/**
 * <p>A wsdl publisher, it is responsible for generating and modifying service contract.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface WsdlPublisher {

    /**
     * <p>Publishes the service wsdl.</p>
     *
     * @param service the service
     *
     * @return the published wsdl.
     *
     * @throws ServiceExecutionException if any error occurs
     */
    String publishWsdl(Service service) throws ServiceExecutionException;
}
