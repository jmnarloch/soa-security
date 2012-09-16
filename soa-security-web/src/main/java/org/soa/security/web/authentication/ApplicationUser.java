/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p>Represents the application user.</p>
 *
 * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class ApplicationUser extends User {

    /**
     * <p>Represents the user id.</p>
     *
     * <p>Zero by default.</p>
     *
     * <p>Has getter.</p>
     */
    private long userId;

    /**
     * <p>Creates new instance of {@link ApplicationUser} class.</p>
     *
     * @param userId      the user id
     * @param username    the user name
     * @param password    the user password
     * @param authorities the user roles
     */
    public ApplicationUser(long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.userId = userId;
    }

    /**
     * <p>Creates new instance of {@link ApplicationUser} class.</p>
     *
     * @param username              the user name
     * @param password              the user password
     * @param enabled               whether the user account is enabled
     * @param accountNonExpired     whether the user account is not expired
     * @param credentialsNonExpired whether the user credentials didn't expired
     * @param accountNonLocked      whether the account hasn't been locked
     * @param authorities           the user roles
     */
    public ApplicationUser(long userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.userId = userId;
    }

    /**
     * <p>Retrieves the user id.</p>
     *
     * @return the user id
     */
    public long getUserId() {

        return userId;
    }
}
