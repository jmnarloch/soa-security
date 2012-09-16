/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * <p>Represents a ldap authentication source.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Entity
public class LdapDataSource extends DataSource {

    /**
     * <p>Represents the ldap url.</p>
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
     * <p>Represents the pattern used as the root of the user credentials.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private String root;

    /**
     * <p>Represents the dn pattern used for retrieving the user credentials..</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    @NotNull
    private String userDNPattern;

    /**
     * <p>Creates new instance of {@link LdapDataSource} class.</p>
     */
    public LdapDataSource() {
        // empty constructor
    }

    /**
     * <p>Retrieves the ldap url.</p>
     *
     * @return the ldap url
     */
    public String getUrl() {
        return url;
    }

    /**
     * <p>Sets the ldap url.</p>
     *
     * @param url the ldap url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * <p>Retrieves the root element.</p>
     *
     * @return the root element
     */
    public String getRoot() {
        return root;
    }

    /**
     * <p>Sets the root element.</p>
     *
     * @param root the root element
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * <p>Retrieves the dn pattern.</p>
     *
     * @return the dn pattern
     */
    public String getUserDNPattern() {
        return userDNPattern;
    }

    /**
     * <p>Sets the user dn pattern.</p>
     *
     * @param userDNPattern the user dn pattern
     */
    public void setUserDNPattern(String userDNPattern) {
        this.userDNPattern = userDNPattern;
    }
}
