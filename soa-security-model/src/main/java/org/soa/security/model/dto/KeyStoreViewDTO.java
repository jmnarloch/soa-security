/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Represents a key store DTO.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class KeyStoreViewDTO implements Serializable {

    /**
     * <p>Represents the key store id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long keyStoreId;

    /**
     * <p>Represents the key store name.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String name;

    /**
     * <p>Represents the key aliases.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private List<String> aliases;

    /**
     * <p>Creates new instance of {@link org.soa.security.model.dto.KeyStoreViewDTO} class.</p>
     */
    public KeyStoreViewDTO() {
        // empty constructor
    }

    /**
     * <p>Retrieves the key store id.</p>
     *
     * @return the key store id
     */
    public long getKeyStoreId() {
        return keyStoreId;
    }

    /**
     * <p>Sets the key store id.</p>
     *
     * @param keyStoreId the key store id
     */
    public void setKeyStoreId(long keyStoreId) {
        this.keyStoreId = keyStoreId;
    }

    /**
     * <p>Retrieves the key store name.</p>
     *
     * @return the key store name
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Sets the key store name.</p>
     *
     * @param name the key store name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Retrieves the key aliases.</p>
     *
     * @return the key aliases
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * <p>Sets the key aliases.</p>
     *
     * @param aliases the key aliases
     */
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}
