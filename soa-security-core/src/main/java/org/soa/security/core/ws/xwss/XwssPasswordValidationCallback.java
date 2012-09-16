/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.xwss;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;
import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.authentication.UserDataSource;
import org.soa.security.core.authentication.UserInfo;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * <p>A implementation of {@link XwssSecurityCallback} that validates the user credentials passed with the message.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class XwssPasswordValidationCallback implements XwssSecurityCallback {

    /**
     * <p>Represents the user data source used for authenticating.</p>
     */
    private UserDataSource userDataSource;

    /**
     * <p>Creates new instance of {@link XwssPasswordValidationCallback} class.</p>
     *
     * @param userDataSource the user data source
     */
    public XwssPasswordValidationCallback(UserDataSource userDataSource) {

        this.userDataSource = userDataSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Callback callback) {

        return (callback instanceof PasswordValidationCallback);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Callback callback) throws IOException, UnsupportedCallbackException {

        if (callback instanceof PasswordValidationCallback) {

            if (((PasswordValidationCallback) callback).getRequest() instanceof PasswordValidationCallback.PlainTextPasswordRequest) {
                ((PasswordValidationCallback) callback).setValidator(new PasswordValidator(userDataSource));
            } else if (((PasswordValidationCallback) callback).getRequest() instanceof PasswordValidationCallback.DigestPasswordRequest) {
                ((PasswordValidationCallback) callback).setValidator(new DigestPasswordValidator(userDataSource));
            }
        }
    }

    /**
     * <p>A validator that verifies the user credentials that were passed with the message.</p>
     *
     * @author Jakub Narloch (jmnarloch@gmail.com)
     * @version 1.0
     */
    private class PasswordValidator implements PasswordValidationCallback.PasswordValidator {

        /**
         * <p>Represents the user data source.</p>
         */
        private UserDataSource userDataSource;

        /**
         * <p>Creates new instance of {@link PasswordValidator} class.</p>
         *
         * @param userDataSource the user datasource
         */
        private PasswordValidator(UserDataSource userDataSource) {

            this.userDataSource = userDataSource;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean validate(PasswordValidationCallback.Request request)
                throws PasswordValidationCallback.PasswordValidationException {

            if (request instanceof PasswordValidationCallback.PlainTextPasswordRequest) {

                try {

                    PasswordValidationCallback.PlainTextPasswordRequest plainTextRequest =
                            (PasswordValidationCallback.PlainTextPasswordRequest) request;

                    return userDataSource.authenticate(plainTextRequest.getUsername(), plainTextRequest.getPassword());
                } catch (ServiceExecutionException e) {
                    // ignores exception
                }
            }

            // otherwise return false
            return false;
        }
    }

    /**
     * <p>A validator that verifies the user credentials that were passed with the message.</p>
     *
     * @author Jakub Narloch (jmnarloch@gmail.com)
     * @version 1.0
     */
    private class DigestPasswordValidator extends PasswordValidationCallback.DigestPasswordValidator {

        /**
         * <p>Represents the user data source.</p>
         */
        private UserDataSource userDataSource;

        /**
         * <p>Creates new instance of {@link PasswordValidator} class.</p>
         *
         * @param userDataSource the user datasource
         */
        private DigestPasswordValidator(UserDataSource userDataSource) {

            this.userDataSource = userDataSource;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean validate(PasswordValidationCallback.Request request)
                throws PasswordValidationCallback.PasswordValidationException {

            if (request instanceof PasswordValidationCallback.DigestPasswordRequest) {

                try {

                    PasswordValidationCallback.DigestPasswordRequest digestRequest =
                            (PasswordValidationCallback.DigestPasswordRequest) request;

                    UserInfo userInfo = userDataSource.getUser(digestRequest.getUsername());

                    if(userInfo == null)
                    {
                        return false;
                    }

                    digestRequest.setPassword(userInfo.getPassword());

                    return super.validate(digestRequest);
                } catch (ServiceExecutionException e) {
                    // ignores exception
                }
            }

            // otherwise return false
            return false;
        }
    }
}


