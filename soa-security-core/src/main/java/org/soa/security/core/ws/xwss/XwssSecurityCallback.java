/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.xwss;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * <p>A XWSS security callback. The concrete implementations will responsible for handling specific security types.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface XwssSecurityCallback {

    /**
     * <p>Returns whether the handler support specific callback.</p>
     *
     * @param callback the callback to check
     *
     * @return true if the handler can process specific callback, false otherwise
     */
    boolean supports(Callback callback);

    /**
     * <p>Handles given callback.</p>
     *
     * @param callback the callback to process
     *
     * @throws UnsupportedCallbackException if any error occurs
     */
    void handle(Callback callback) throws IOException, UnsupportedCallbackException;
}
