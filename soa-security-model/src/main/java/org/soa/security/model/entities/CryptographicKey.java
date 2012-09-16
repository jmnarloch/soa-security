/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * <p>Represents a cryptographic key.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class CryptographicKey extends IdentifiableEntity {

    /**
     * <p>Represents the key authentication.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    byte[] data;

    /**
     * <p>Creates new instance of {@link CryptographicKey} class.</p>
     */
    public CryptographicKey() {
        // empty constructor
    }

    /**
     * <p>Retrieves the key authentication.</p>
     *
     * @return the key authentication
     */
    public byte[] getData() {
        return data;
    }

    /**
     * <p>Sets the key authentication.</p>
     *
     * @param data the key authentication
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}
