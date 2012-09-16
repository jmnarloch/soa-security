/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.xwss;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.List;

/**
 * <p>A XWSS Security callback handler.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class XwssSecurityCallbackHandler implements CallbackHandler {

    /**
     * <p>Represents a list of security callbacks.</p>
     */
    private List<XwssSecurityCallback> securityCallbacks;

    /**
     * <p>Creates new instance of {@link XwssSecurityCallbackHandler}.</p>
     */
    public XwssSecurityCallbackHandler(List<XwssSecurityCallback> securityCallbacks) {

        this.securityCallbacks = securityCallbacks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        for (Callback callback : callbacks) {

            XwssSecurityCallback securityCallback = getSecurityCallback(callback);

            securityCallback.handle(callback);
        }
    }

    /**
     * <p>Retrieves a security callback that is capable of handling the given callback.</p>
     *
     * @param callback the xwss callback
     *
     * @return the security callback
     *
     * @throws UnsupportedCallbackException if the given callback is not supported
     */
    private XwssSecurityCallback getSecurityCallback(Callback callback) throws UnsupportedCallbackException {

        for (XwssSecurityCallback securityCallback : securityCallbacks) {

            if (securityCallback.supports(callback)) {

                return securityCallback;
            }
        }

        throw new UnsupportedCallbackException(callback, "The given callback is not supported.");
    }
}
