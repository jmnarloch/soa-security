/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * <p>This is base class for all entities.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@MappedSuperclass
public abstract class IdentifiableEntity implements Serializable {

    /**
     * <p>Represents the entity id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * <p>Creates new instance of {@link IdentifiableEntity} class.</p>
     */
    protected IdentifiableEntity() {
        // empty constructor
    }

    /**
     * <p>Retrieves the entity id.</p>
     *
     * @return the entity id
     */
    public long getId() {
        return id;
    }

    /**
     * <p>Sets the entity id.</p>
     *
     * @param id the entity id
     */
    public void setId(long id) {
        this.id = id;
    }
}
