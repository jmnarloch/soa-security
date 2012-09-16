/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.dto;

import java.io.Serializable;

/**
 * <p>Represents a key store DTO.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class KeyStoreDTO implements Serializable {

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
     * <p>Represents the key store keyStoreName.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String keyStoreName;

    /**
     * <p>Represents the key store keyStoreFile.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private byte[] keyStoreFile;

    /**
     * <p>Represents the key store password.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String keyStorePassword;

    /**
     * <p>Represents the key password.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String keyPassword;

    /**
     * <p>Represents the user id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private long userId;

    /**
     * <p>Creates new instance of {@link KeyStoreDTO} class.</p>
     */
    public KeyStoreDTO() {
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
     * <p>Retrieves the key store keyStoreName.</p>
     *
     * @return the key store keyStoreName
     */
    public String getKeyStoreName() {
        return keyStoreName;
    }

    /**
     * <p>Sets the key store keyStoreName.</p>
     *
     * @param name the key store keyStoreName
     */
    public void setKeyStoreName(String name) {
        this.keyStoreName = name;
    }

    /**
     * <p>Retrieves the key store password.</p>
     *
     * @return the key store password
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * <p>Sets the key store password.</p>
     *
     * @param keyStorePassword the key store password
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * <p>Retrieves the key password.</p>
     *
     * @return the key store password
     */
    public String getKeyPassword() {
        return keyPassword;
    }

    /**
     * <p>Sets the key password.</p>
     *
     * @param keyPassword the key password
     */
    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    /**
     * <p>Retrieves the user id.</p>
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * <p>Sets the user id.</p>
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
