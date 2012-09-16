/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.authentication;

import org.soa.security.dao.SoaDAOException;
import org.soa.security.dao.UserDAO;
import org.soa.security.model.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * <p>Custom user details service.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
@Transactional
public class ApplicationUserDetailService implements UserDetailsService {

    /**
     * <p>Represents the instance of {@link UserDAO}.</p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <p>Creates new instance of {@link ApplicationUserDetailService} class.</p>
     */
    public ApplicationUserDetailService() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        try {
            UserInfo userInfo = userDAO.getByUserName(userName);

            if (userInfo == null) {
                throw new UsernameNotFoundException("User could not be found.");
            }

            return new ApplicationUser(userInfo.getId(), userInfo.getUserName(), userInfo.getPassword(),
                    userInfo.isEnabled(), true, true, true,
                    Arrays.asList(new SimpleGrantedAuthority(userInfo.getUserRole().getValue())));

        } catch (SoaDAOException e) {

            throw new UsernameNotFoundException("Error occurred when accessing the persistence.", e);
        }
    }
}
