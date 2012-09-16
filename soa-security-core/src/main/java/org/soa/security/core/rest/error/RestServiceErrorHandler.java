/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.rest.error;

import org.soa.security.core.http.HttpResponse;
import org.soa.security.core.service.ServiceType;
import org.soa.security.core.service.error.ServiceErrorHandler;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>A service handler capable of handling rest service errors.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class RestServiceErrorHandler implements ServiceErrorHandler {

    /**
     * <p>Creates new instance of {@link RestServiceErrorHandler}.</p>
     */
    public RestServiceErrorHandler() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceType getSupportedServiceType() {
        return ServiceType.REST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleError(Exception exc, HttpResponse httpResponse) {

        try {
            // sets the error status code
            // TODO introduce const
            httpResponse.setStatusCode(500);
            // writes the exception to the response
            writeException(exc, httpResponse.getOutputStream());
        } catch (IOException e) {

            // ignores exception
        }
    }

    /**
     * <p>Writes the exception stack trace in the output stream.</p>
     *
     * @param exc    the exception
     * @param output the output stream
     */
    private void writeException(Exception exc, OutputStream output) throws IOException {

        BufferedWriter bufferedWriter = null;
        String stackTrace;

        try {
            // retrieves the exception stack trace
            stackTrace = getStackTrace(exc);

            // writes the stack trace to the output stream
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
            bufferedWriter.write(stackTrace);
            bufferedWriter.flush();
        } finally {

            if (bufferedWriter != null) {
                // TODO should the stream be closed by this method or by caller?
                bufferedWriter.close();
            }
        }
    }

    /**
     * <p>Prints out the exception stack trace.</p>
     *
     * @param exc the exception
     *
     * @return the exception stack trace
     */
    private String getStackTrace(Exception exc) {

        StringWriter stringWriter;
        PrintWriter printWriter;

        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        exc.printStackTrace(printWriter);

        return stringWriter.toString();
    }
}
