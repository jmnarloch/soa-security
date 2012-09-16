/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.controller;

import org.soa.security.model.dto.UserDTO;
import org.soa.security.service.SoaServiceException;
import org.soa.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>User controller.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Controller
public class UserController extends BaseController {

    /**
     * <p>Instance of {@link UserService} used for retrieving users.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private UserService userService;

    /**
     * <p>Creates new instance of {@link UserController} class.</p>
     */
    public UserController() {
        // empty constructor
    }

    /**
     * <p>Handles request for creating new user</p>
     *
     * @param userDTO the user
     *
     * @return the id of newly created user
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/User", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public long createUser(@RequestBody UserDTO userDTO)
            throws SoaServiceException {

        return userService.save(getAuthenticatedUser().getUserId(), userDTO);
    }

    /**
     * <p>Updates the user.</p>
     *
     * @param userDTO the user
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/User", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void updateUser(@RequestBody UserDTO userDTO)
            throws SoaServiceException {

        userService.update(getAuthenticatedUser().getUserId(), userDTO);
    }

    /**
     * <p>Retrieves the user.</p>
     *
     * @param id the user id
     *
     * @return the user
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/User", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDTO getUserById(@RequestParam long id)
            throws SoaServiceException {

        return userService.get(getAuthenticatedUser().getUserId(), id);
    }

    /**
     * <p>Deletes the user based on it's id.</p>
     *
     * @param id the  id
     *
     * @return whether the operation succeeded
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/User", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteUser(@RequestParam long id)
            throws SoaServiceException {

        // deletes the user
        userService.delete(getAuthenticatedUser().getUserId(), id);
    }

    /**
     * <p>Handles request for retrieving all user.</p>
     *
     * @return list of user services
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/User/All", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDTO> getUsers()
            throws SoaServiceException {

        return userService.getAll(getAuthenticatedUser().getUserId());
    }

    /**
     * <p>Enables the user.</p>
     *
     * @param enable whether enable the user
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/User/Enable", method = RequestMethod.POST)
    @ResponseBody
    public void enable(@RequestParam long id, @RequestParam boolean enable) throws SoaServiceException {

        userService.enableUser(getAuthenticatedUser().getUserId(), id, enable);
    }
}
