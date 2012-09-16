/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.core.interceptor;

import org.soa.security.core.Message;

/**
 * <p>A message interceptor, used for processing inbound and outbound messages.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public interface MessageInterceptor<T extends Message> {

    /**
     * <p>Intercepts the inbound message.</p>
     *
     * @param interceptionContext the interception context
     *
     * @throws MessageInterceptionException if any error occurs when processing the message
     */
    void interceptInboundMessage(MessageInterceptionContext<T> interceptionContext) throws MessageInterceptionException;

    /**
     * <p>Intercepts the inbound message.</p>
     *
     * @param interceptionContext the interception context
     *
     * @throws MessageInterceptionException if any error occurs when processing the message
     */
    void interceptOutboundMessage(MessageInterceptionContext<T> interceptionContext) throws MessageInterceptionException;
}
