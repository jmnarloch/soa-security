/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.core;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Represents a message.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface Message {

    /**
     * <p>Writes the content of the message into the given output stream.</p>
     *
     * @param outputStream the output stream
     *
     * @throws IOException if any error occurs when writing the content of the message
     */
    void writeTo(OutputStream outputStream) throws IOException;
}
