/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core;

import java.text.MessageFormat;

/**
 * <p>A helper class used in this component.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public final class Helper {

    /**
     * <p>Checks if passed parameter is not null, if it's is then {@link IllegalArgumentException} is being thrown.</p>
     *
     * @param param     the parameter to check
     * @param paramName the parameter name
     *
     * @throws IllegalArgumentException if param is null
     */
    public static void checkNotNull(Object param, String paramName) {

        if (param == null) {
            throw new IllegalArgumentException(MessageFormat.format("The parameter {0} can not be null.", paramName));
        }
    }

    /**
     * <p>Checks if the passed field has been properly initialized, if it wasn't then {@link IllegalStateException} is
     * being thrown.</p>
     *
     * @param field     the field to check
     * @param fieldName the field name
     *
     * @throws IllegalStateException if field is null
     */
    public static void checkStateNotNull(Object field, String fieldName) {

        if (field == null) {
            throw new IllegalStateException(MessageFormat.format("The field {0} can not be null.", fieldName));
        }
    }
}
