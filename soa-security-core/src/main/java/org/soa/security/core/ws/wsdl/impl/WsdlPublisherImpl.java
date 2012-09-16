/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.wsdl.impl;

import org.soa.security.core.Helper;
import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.http.HttpContext;
import org.soa.security.core.http.client.HttpClient;
import org.soa.security.core.service.Service;
import org.soa.security.core.ws.wsdl.WsdlPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.MessageFormat;

/**
 * <p>A default implementation of {@link WsdlPublisher}.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class WsdlPublisherImpl implements WsdlPublisher {

    /**
     * <p>Represents the http client used for retrieving the remote service wsdl.</p>
     */
    @Autowired
    private HttpClient httpClient;

    /**
     * <p>Creates new instance of {@link WsdlPublisherImpl} class.</p>
     */
    public WsdlPublisherImpl() {
        // empty constructor
    }

    /**
     * <p>Validates that the class has been correctly initialized.</p>
     *
     * @throws IllegalStateException if one of the required fields hasn't been set
     */
    @PostConstruct
    public void init() {

        Helper.checkStateNotNull(httpClient, "httpClient");
    }

    /**
     * <p>Sets the http client.</p>
     *
     * @param httpClient the http client
     */
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String publishWsdl(Service service) throws ServiceExecutionException {

        String wsdl = retrieveServiceWsdl(service);

        return modifyService(service, wsdl);
    }

    /**
     * <p>Retrieves the web service wsdl directly from the given address.</p>
     *
     * @param service the service
     *
     * @return the service wsdl
     */
    private String retrieveServiceWsdl(Service service) throws ServiceExecutionException {

        return httpClient.makeGetRequest(service.getContractUrl());
    }

    /**
     * <p>Modifies the service wsdl and sets the service location to the address of the running application.</p>
     *
     * @param service the service
     * @param wsdl    the service wsdl
     *
     * @return the modified service wsdl
     *
     * @throws ServiceExecutionException if any error occurs when modifying the service wsdl
     */
    private String modifyService(Service service, String wsdl) throws ServiceExecutionException {

        String serviceUrl;
        StreamResult streamResult;
        StringReader reader;
        InputSource inputSource;

        try {
            // gets the service url
            serviceUrl = getServiceUrl(service);

            reader = new StringReader(wsdl);
            inputSource = new InputSource(reader);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputSource);

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xPath = xpathFactory.newXPath();

            NodeList nodeList = (NodeList) xPath.evaluate("/definitions/service/port/address/@location",
                    doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                nodeList.item(i).setNodeValue(serviceUrl);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");

            streamResult = new StreamResult(new StringWriter());
            transformer.transform(new DOMSource(doc), streamResult);

            return streamResult.getWriter().toString();
        } catch (TransformerConfigurationException e) {

            throw new ServiceExecutionException("Could not modify the service wsdl.", e);
        } catch (TransformerException e) {

            throw new ServiceExecutionException("Could not modify the service wsdl.", e);
        } catch (SAXException e) {

            throw new ServiceExecutionException("Could not modify the service wsdl.", e);
        } catch (XPathExpressionException e) {

            throw new ServiceExecutionException("Could not modify the service wsdl.", e);
        } catch (ParserConfigurationException e) {

            throw new ServiceExecutionException("Could not modify the service wsdl.", e);
        } catch (IOException e) {

            throw new ServiceExecutionException("Could not modify the service wsdl.", e);
        }
    }

    /**
     * <p>Retrieves the service url.</p>
     *
     * @param service the service
     *
     * @return the service url
     */
    private String getServiceUrl(Service service) {

        String contextPath = HttpContext.getInstance().getApplicationContextPath();
        String servicePath = service.getUrl();

        if(contextPath.endsWith("/")) {
            contextPath = contextPath.substring(0, contextPath.length() - 1);
        }

        if(servicePath.startsWith("/")) {
            servicePath = servicePath.substring(1, servicePath.length());
        }

        return MessageFormat.format("{0}/{1}", contextPath, servicePath);
    }
}
