/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.web.controller;

import org.soa.security.model.dto.KeyStoreDTO;
import org.soa.security.service.KeyStoreService;
import org.soa.security.service.SoaServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>Key store controller.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Controller
public class KeyStoreController extends BaseController {

    /**
     * <p>Instance of {@link KeyStoreService} used for operating on key stores.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Autowired by IOC container.</p>
     */
    @Autowired
    private KeyStoreService keyStoreService;

    /**
     * <p>Creates new instance of {@link KeyStoreController} class.</p>
     */
    public KeyStoreController() {
        // empty constructor
    }

    /**
     * <p>Creates the key store.</p>
     *
     * @param keyStore the key store
     *
     * @return the id of the created key store
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/KeyStore", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public long createKeyStore(@RequestBody KeyStoreDTO keyStore) throws SoaServiceException {

        return keyStoreService.save(getAuthenticatedUser().getUserId(), keyStore);
    }

    /**
     * <p>Handles the key store file upload.</p>
     *
     * @param keyStoreId the key store id
     * @param keyStore   the key store
     *
     * @throws SoaServiceException if any error occurs
     * @throws IOException         if any error occurs during file upload
     */
    @RequestMapping(value = "/KeyStore/File", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void uploadKeyStoreFile(@RequestParam long keyStoreId, @RequestParam("keyStoreFile") MultipartFile keyStore)
            throws IOException, SoaServiceException {

        try {

            keyStoreService.saveKeyStoreFile(getAuthenticatedUser().getUserId(), keyStoreId, keyStore.getBytes());
        } catch (SoaServiceException e) {

            // if the operation didn't succeeded then removes the key store
            keyStoreService.delete(getAuthenticatedUser().getUserId(), keyStoreId);

            throw e;
        }
    }

    /**
     * <p>Deletes the key store by it's id.</p>
     *
     * @param keyStoreId the key store id
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/KeyStore", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteKeyStore(@RequestParam long keyStoreId) throws SoaServiceException {

        keyStoreService.delete(getAuthenticatedUser().getUserId(), keyStoreId);
    }

    /**
     * <p>Retrieves the key store by it's id.</p>
     *
     * @param keyStoreId the key store id
     *
     * @return the key store
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/KeyStore", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public KeyStoreDTO getKeyStoreById(@RequestParam long keyStoreId)
            throws SoaServiceException {

        return keyStoreService.get(getAuthenticatedUser().getUserId(), keyStoreId);
    }

    /**
     * <p>Handles request for all user key stores.</p>
     *
     * @return list of user key stores
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/KeyStore/User", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<KeyStoreDTO> getKeyStoresByUserId() throws SoaServiceException {

        return keyStoreService.getByUserId(getAuthenticatedUser().getUserId());
    }

    /**
     * <p>Handles the request for all key store aliases.</p>
     *
     * @return the list of key store aliases
     *
     * @throws SoaServiceException if any error occurs
     */
    @RequestMapping(value = "/KeyStore/Aliases", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<String> getKeyStoreAliases(@RequestParam long keyStoreId) throws SoaServiceException {

        return keyStoreService.getKeyStoreAliases(getAuthenticatedUser().getUserId(), keyStoreId);
    }
}
