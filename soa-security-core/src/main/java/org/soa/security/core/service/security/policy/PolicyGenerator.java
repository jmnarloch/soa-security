/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.security.policy;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.model.dto.ServiceSecurityDTO;

import java.util.List;

/**
 * <p>A policy generator that is responsible for generating the policy from the given configuration.</p>
 *
 * @param <T> the configuration type
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface PolicyGenerator {

    /**
     * <p>Generates the security policy based on the provided configuration object.</p>
     *
     * @param securities the configuration
     *
     * @return the generated policy
     */
    String generatePolicy(List<ServiceSecurityDTO> securities) throws ServiceExecutionException;
}
