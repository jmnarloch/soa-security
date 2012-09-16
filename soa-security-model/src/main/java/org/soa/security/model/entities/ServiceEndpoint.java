/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * <p>Represents a service endpoint.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class ServiceEndpoint extends IdentifiableEntity {

    /**
     * <p>Represents the endpoint url.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String url;

    /**
     * <p>Creates new instance of {@link ServiceEndpoint} class.</p>
     */
    public ServiceEndpoint() {
        // empty constructor
    }

    /**
     * <p>Retrieves the endpoint url.</p>
     *
     * @return the endpoint url
     */
    public String getUrl() {
        return url;
    }

    /**
     * <p>Sets the endpoint url.</p>
     *
     * @param url the endpoint url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
