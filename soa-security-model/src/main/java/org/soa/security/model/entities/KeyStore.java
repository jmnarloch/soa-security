/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>Represents a key store.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class KeyStore extends IdentifiableEntity {

    /**
     * <p>Represents the key store name.</p>
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
     * <p>Represents whether this key store has been removed.</p>
     *
     * <p>False by default.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private boolean removed;

    /**
     * <p>Represents the key store password.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String keyStorePassword;

    /**
     * <p>Represents the key password.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String keyPassword;

    /**
     * <p>Represents the key store file.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private KeyStoreFile keyStoreFile;

    /**
     * <p>Represents the key store aliases.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<KeyStoreAlias> keyStoreAliases;

    /**
     * <p>Represents the user.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    @ManyToOne
    private UserInfo user;

    /**
     * <p>Creates new instance of {@link KeyStore} class.</p>
     */
    public KeyStore() {
        // empty constructor
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
     * <p>Retrieves whether the key store has been removed.</p>
     *
     * @return whether the key store has been removed
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * <p>Sets whether the key store has been removed.</p>
     *
     * @param removed whether the key store has been removed
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
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
     * <p>Sets the key store password</p>
     *
     * @param keyStorePassword the key store password
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * <p>Retrieves the key password.</p>
     *
     * @return the key password
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
     * <p>Retrieves the key store file.</p>
     *
     * @return key store file
     */
    public KeyStoreFile getKeyStoreFile() {
        return keyStoreFile;
    }

    /**
     * <p>Sets the key store file.</p>
     *
     * @param keyStoreFile the key store file
     */
    public void setKeyStoreFile(KeyStoreFile keyStoreFile) {
        this.keyStoreFile = keyStoreFile;
    }

    /**
     * <p>Retrieves the key store aliases.</p>
     *
     * @return the key store aliases
     */
    public List<KeyStoreAlias> getKeyStoreAliases() {
        return keyStoreAliases;
    }

    /**
     * <p>Sets the key store aliases.</p>
     *
     * @param keyStoreAliases the key store aliases
     */
    public void setKeyStoreAliases(List<KeyStoreAlias> keyStoreAliases) {
        this.keyStoreAliases = keyStoreAliases;
    }

    /**
     * <p>Retrieves the user.</p>
     *
     * @return the user
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * <p>Sets the user.</p>
     *
     * @param user the user
     */
    public void setUser(UserInfo user) {
        this.user = user;
    }
}
