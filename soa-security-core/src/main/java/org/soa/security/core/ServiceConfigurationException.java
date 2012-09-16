/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core;

/**
 * <p>Exception used for reporting error in configuration.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ServiceConfigurationException extends RuntimeException {

    /**
     * <p>Creates new instance of {@link ServiceConfigurationException} class.</p>
     */
    public ServiceConfigurationException() {
        // empty constructor
    }

    /**
     * <p>Creates new instance of {@link ServiceConfigurationException} class with detailed error message.</p>
     *
     * @param message the detailed error message
     */
    public ServiceConfigurationException(String message) {
        super(message);
    }

    /**
     * <p>Creates new instance of {@link ServiceConfigurationException} class with detailed error message and inner
     * cause.</p>
     *
     * @param message the detailed error message
     * @param cause   the inner cause
     */
    public ServiceConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
