/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.interceptor.impl;

import org.soa.security.core.Message;
import org.soa.security.core.interceptor.MessageInterceptionContext;
import org.soa.security.core.interceptor.MessageInterceptionException;
import org.soa.security.core.interceptor.MessageInterceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * <p>A message logging interceptor. It logs the inbound outbound messages.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class MessageLoggingInterceptor implements MessageInterceptor<Message> {

    /**
     * <p>Represents the instance of the logger used for logging the messages.</p>
     */
    private static Logger logger = Logger.getLogger(MessageLoggingInterceptor.class.getName());

    /**
     * <p>Creates new instance of {@link MessageLoggingInterceptor} class.</p>
     */
    public MessageLoggingInterceptor() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interceptInboundMessage(MessageInterceptionContext<Message> interceptionContext)
            throws MessageInterceptionException {

        logMessage(interceptionContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interceptOutboundMessage(MessageInterceptionContext<Message> interceptionContext)
            throws MessageInterceptionException {

        logMessage(interceptionContext);
    }

    /**
     * <p>Retrieves the message content.</p>
     *
     * @param message the message
     *
     * @return the content of the message
     */
    private String getMessageContent(Message message) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // writes the content of the message
        message.writeTo(outputStream);

        // return the content of the message
        return new String(outputStream.toByteArray());
    }

    /**
     * <p>Logs the message.</p>
     *
     * @param interceptionContext the message interception context
     *
     * @throws MessageInterceptionException if any error occurs
     */
    private void logMessage(MessageInterceptionContext<Message> interceptionContext)
            throws MessageInterceptionException {

        Message message;
        String messageContent;

        try {
            // retrieves the message
            message = interceptionContext.getMessage();

            // gets the string representation of the message
            messageContent = getMessageContent(message);

            // logs the message
            logger.fine(messageContent);
        } catch (IOException e) {

            throw new MessageInterceptionException("Could not log the message.", e);
        }
    }
}
