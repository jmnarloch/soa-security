/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.service.security.policy.impl;

import org.soa.security.core.ServiceExecutionException;
import org.soa.security.core.service.security.policy.PolicyGenerator;
import org.soa.security.model.ModelConsts;
import org.soa.security.model.dto.ServiceSecurityDTO;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

/**
 * <p>A policy generator that creates XWSS policies from security configuration.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
@Component
public class XwssPolicyGenerator implements PolicyGenerator {

    /**
     * <p>Represents the template for XWSS xml policy file.</p>
     */
    private static final String XWSS_POLICY =
            "<xwss:SecurityConfiguration xmlns:xwss=\"http://java.sun.com/xml/ns/xwss/config\"> " +
                    " {0} </xwss:SecurityConfiguration>";

    /**
     * <p>A xwss tag for configuring the user authentication.</p>
     */
    private static final String XWSS_AUTHENTICATION =
            "<xwss:RequireUsernameToken passwordDigestRequired=\"false\" nonceRequired=\"false\"/>";

    /**
     * <p>A xwss tag for configuring the user authentication.</p>
     */
    private static final String XWSS_AUTHENTICATION_DIGEST =
            "<xwss:RequireUsernameToken passwordDigestRequired=\"true\" nonceRequired=\"true\"/>";

    // TODO the algorithm is being hard coded

    /**
     * <p>A xwss tag for configuring message signature verification.</p>
     */
    private static final String XWSS_REQUIRE_SIGNATURE = "<xwss:RequireSignature requireTimestamp=\"false\">" +
            "<xwss:X509Token certificateAlias=\"{0}\"/>" +
            "<xwss:CanonicalizationMethod algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>" +
            "<xwss:SignatureMethod algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>" +
            "</xwss:RequireSignature>";

    /**
     * <p>A xwss tag for configuring message signing.</p>
     */
    private static final String XWSS_SIGNING = "<xwss:Sign includeTimestamp=\"false\">" +
            "<xwss:X509Token certificateAlias=\"{0}\"/>" +
            "<xwss:CanonicalizationMethod algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>" +
            "<xwss:SignatureMethod algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>" +
            "</xwss:Sign>";

    /**
     * <p>A xwss tag for configuring message decryption.</p>
     */
    public static final String XWSS_REQUIRE_ENCRYPTION = "<xwss:RequireEncryption>" +
            "<xwss:X509Token certificateAlias=\"{0}\"/>" +
            "</xwss:RequireEncryption>";

    /**
     * <p>A xwss tag for configuring message encryption.</p>
     */
    public static final String XWSS_ENCRYPTION = "<xwss:Encrypt>" +
            "<xwss:X509Token certificateAlias=\"{0}\"/>" +
            "</xwss:Encrypt>";

    /**
     * <p>Creates new instance of {@link XwssPolicyGenerator} class.</p>
     */
    public XwssPolicyGenerator() {
        // empty constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePolicy(List<ServiceSecurityDTO> securities) throws ServiceExecutionException {

        StringBuilder stringBuilder = new StringBuilder();

        // appends the required information
        for (ServiceSecurityDTO serviceSecurity : securities) {

            if (serviceSecurity.getSecurityTypeId() == ModelConsts.SecurityTypes.WS_PASSWORD) {

                stringBuilder.append(XWSS_AUTHENTICATION);
            } else if (serviceSecurity.getSecurityTypeId() == ModelConsts.SecurityTypes.WS_DIGEST) {

                stringBuilder.append(XWSS_AUTHENTICATION_DIGEST);
            } else if (serviceSecurity.getSecurityTypeId() == ModelConsts.SecurityTypes.WS_SIGNING) {

                stringBuilder.append(MessageFormat.format(XWSS_REQUIRE_SIGNATURE, serviceSecurity.getClientAlias()));

                stringBuilder.append(MessageFormat.format(XWSS_SIGNING, serviceSecurity.getServerAlias()));

            } else if (serviceSecurity.getSecurityTypeId() == ModelConsts.SecurityTypes.WS_ENCRYPTION) {

                stringBuilder.append(MessageFormat.format(XWSS_REQUIRE_ENCRYPTION, serviceSecurity.getServerAlias()));

                stringBuilder.append(MessageFormat.format(XWSS_ENCRYPTION, serviceSecurity.getClientAlias()));
            }
        }

        // formats the policy file
        return MessageFormat.format(XWSS_POLICY, stringBuilder.toString());
    }
}
