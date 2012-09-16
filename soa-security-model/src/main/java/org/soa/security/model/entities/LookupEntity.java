/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

/**
 * <p>Represents a lookup entity.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class LookupEntity extends IdentifiableEntity {

    /**
     * <p>Represents the entity name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String name;

    /**
     * <p>An optional value associated with the name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String value;

    /**
     * <p>Represents the entity ordinal.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private int ordinal;

    /**
     * <p>Creates new instance of {@link LookupEntity} class.</p>
     */
    public LookupEntity() {
        // empty constructor
    }

    /**
     * <p>Retrieves the entity name.</p>
     *
     * @return the entity name
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Sets the entity name.</p>
     *
     * @param name the entity name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Retrieves the value.</p>
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * <p>Sets the value.</p>
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * <p>Retrieves the entity ordinal.</p>
     *
     * @return the entity ordinal
     */
    public int getOrdinal() {
        return ordinal;
    }

    /**
     * <p>Sets the entity ordinal</p>
     *
     * @param ordinal the entity ordinal
     */
    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }
}
