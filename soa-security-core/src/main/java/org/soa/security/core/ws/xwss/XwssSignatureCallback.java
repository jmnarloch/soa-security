/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.xwss;

import com.sun.xml.wss.impl.callback.CertificateValidationCallback;
import com.sun.xml.wss.impl.callback.SignatureKeyCallback;
import com.sun.xml.wss.impl.callback.SignatureVerificationKeyCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * <p>An implementation of {@link XwssSecurityCallback} responsible for validating the message signature.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class XwssSignatureCallback implements XwssSecurityCallback {

    /**
     * <p>Represents the key store.</p>
     */
    private KeyStore keyStore;

    /**
     * <p>Represents the key store password.</p>
     */
    private char[] keystorePassword;

    /**
     * <p>Represents the key password.</p>
     */
    private char[] keyPassword;

    /**
     * <p>Creates new instance of {@link XwssSecurityCallback} class.</p>
     *
     * @param keyStoreContent  the key store content
     * @param keystorePassword the key store password
     * @param keyPassword      the key password
     */
    public XwssSignatureCallback(byte[] keyStoreContent, char[] keystorePassword, char[] keyPassword) {

        this.keyStore = loadKeyStore(keyStoreContent);
        this.keystorePassword = keystorePassword;
        this.keyPassword = keyPassword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Callback callback) {

        return callback instanceof CertificateValidationCallback
                || callback instanceof SignatureKeyCallback
                || callback instanceof SignatureVerificationKeyCallback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Callback callback) throws IOException, UnsupportedCallbackException {

        if (callback instanceof CertificateValidationCallback) {
            handleCertificateValidationCallback((CertificateValidationCallback) callback);
        } else if (callback instanceof SignatureKeyCallback) {
            handleSignatureKeyCallback((SignatureKeyCallback) callback);
        } else if (callback instanceof SignatureVerificationKeyCallback) {
            handleSignatureVerificationKeyCallback((SignatureVerificationKeyCallback) callback);
        }
    }


    /**
     * <p>Handles the signature key callback.</p>
     *
     * @param signatureKeyCallback the signature key callback
     *
     * @throws IOException if any error occurs
     */
    private void handleSignatureKeyCallback(SignatureKeyCallback signatureKeyCallback) throws IOException {

        if (signatureKeyCallback.getRequest() instanceof SignatureKeyCallback.PrivKeyCertRequest) {
            handlePrivKeyCertRequest(signatureKeyCallback, (SignatureKeyCallback.PrivKeyCertRequest)
                    signatureKeyCallback.getRequest());
        }
    }

    /**
     * <p>Handles the private key certificate request.</p>
     *
     * @param signatureKeyCallback the signature callback
     * @param privKeyCertRequest   the private key cert request
     *
     * @throws IOException if any error occurs
     */
    private void handlePrivKeyCertRequest(SignatureKeyCallback signatureKeyCallback,
                                          SignatureKeyCallback.PrivKeyCertRequest privKeyCertRequest) throws IOException {

        if (signatureKeyCallback.getRequest() instanceof SignatureKeyCallback.AliasPrivKeyCertRequest) {
            handleAliasPrivKeyCertRequest(signatureKeyCallback, (SignatureKeyCallback.AliasPrivKeyCertRequest) privKeyCertRequest);
        }
    }

    /**
     * <p>Handles the alias private key request.</p>
     *
     * @param signatureKeyCallback    the signature callback
     * @param aliasPrivKeyCertRequest the alias private key callback
     *
     * @throws IOException if any error occurs
     */
    private void handleAliasPrivKeyCertRequest(SignatureKeyCallback signatureKeyCallback,
                                               SignatureKeyCallback.AliasPrivKeyCertRequest aliasPrivKeyCertRequest) throws IOException {

        PrivateKey privateKey = getPrivateKey(aliasPrivKeyCertRequest.getAlias());
        X509Certificate certificate = getCertificate(aliasPrivKeyCertRequest.getAlias());
        aliasPrivKeyCertRequest.setPrivateKey(privateKey);
        aliasPrivKeyCertRequest.setX509Certificate(certificate);
    }

    /**
     * <p>Handles the signature verification key callback.</p>
     *
     * @param signatureVerificationKeyCallback
     *         the signature verification key callback
     *
     * @throws IOException if any error occurs
     */
    private void handleSignatureVerificationKeyCallback(SignatureVerificationKeyCallback signatureVerificationKeyCallback)
            throws IOException {

        if (signatureVerificationKeyCallback.getRequest()
                instanceof SignatureVerificationKeyCallback.X509CertificateRequest) {

            handleX509CertificateRequest(signatureVerificationKeyCallback,
                    (SignatureVerificationKeyCallback.X509CertificateRequest) signatureVerificationKeyCallback.getRequest());
        }
    }

    /**
     * <p>Handles the X509 certificate request.</p>
     *
     * @param signatureVerificationKeyCallback
     *                               the signature verification callback
     * @param x509CertificateRequest the X509 request
     *
     * @throws IOException if any error occurs
     */
    private void handleX509CertificateRequest(SignatureVerificationKeyCallback signatureVerificationKeyCallback,
                                              SignatureVerificationKeyCallback.X509CertificateRequest x509CertificateRequest) throws IOException {
        if (x509CertificateRequest instanceof SignatureVerificationKeyCallback.PublicKeyBasedRequest) {
            handlePublicKeyBasedRequest(signatureVerificationKeyCallback,
                    (SignatureVerificationKeyCallback.PublicKeyBasedRequest) x509CertificateRequest);
        }
    }

    /**
     * <p>Handles {@link SignatureVerificationKeyCallback}.</p>
     *
     * @param signatureVerificationKeyCallback
     *                              the callback to process
     * @param publicKeyBasedRequest the request
     */
    private void handlePublicKeyBasedRequest(SignatureVerificationKeyCallback signatureVerificationKeyCallback, SignatureVerificationKeyCallback.PublicKeyBasedRequest publicKeyBasedRequest) throws IOException {

        publicKeyBasedRequest.setX509Certificate(getCertificate(publicKeyBasedRequest.getPublicKey()));
    }

    /**
     * <p>Handles {@link CertificateValidationCallback}.</p>
     *
     * @param certificateValidationCallback the callback to process
     */
    private void handleCertificateValidationCallback(CertificateValidationCallback certificateValidationCallback) {

        certificateValidationCallback.setValidator(new CertificateValidator());
    }

    /**
     * <p>Retrieves the X509 Certificate from key store.</p>
     *
     * @param alias the keystore alias
     *
     * @return the X509Certificate retrieved from key store
     */
    private X509Certificate getCertificate(String alias) throws IOException {

        try {

            return (X509Certificate) keyStore.getCertificate(alias);
        } catch (KeyStoreException e) {

            throw new IOException("Error occurred when loading certificate.", e);
        }
    }

    /**
     * <p>Retrieves the X509 Certificate from key store.</p>
     *
     * @param publicKey the public key
     *
     * @return the X509Certificate retrieved from key store
     */
    private X509Certificate getCertificate(PublicKey publicKey) throws IOException {

        try {

            Enumeration aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = (String) aliases.nextElement();
                Certificate cert = keyStore.getCertificate(alias);
                if (cert == null || !"X.509".equals(cert.getType())) {
                    continue;
                }
                X509Certificate x509Cert = (X509Certificate) cert;
                if (x509Cert.getPublicKey().equals(publicKey)) {
                    return x509Cert;
                }
            }
        } catch (GeneralSecurityException e) {

            throw new IOException("Error occurred when loading certificate", e);
        }

        // no certificate matching the given public key
        return null;
    }

    /**
     * <p>Retrieves the private key from key store using alias.</p>
     *
     * @param alias the private key alias
     *
     * @return the private key retrieved from alias.
     *
     * @throws IOException if any error occurs
     */
    private PrivateKey getPrivateKey(String alias) throws IOException {

        try {

            return (PrivateKey) keyStore.getKey(alias, keyPassword);
        } catch (GeneralSecurityException e) {

            throw new IOException("Error occurred when loading private key", e);
        }
    }

    /**
     * <p>Retrieves the private key from key store.</p>
     *
     * @param x509Certificate the certificate to use
     *
     * @return the private key
     *
     * @throws IOException if any error occurs
     */
    private PrivateKey getPrivateKey(X509Certificate x509Certificate) throws IOException {

        try {
            Enumeration aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = (String) aliases.nextElement();
                if (!keyStore.isKeyEntry(alias)) {
                    continue;
                }
                Certificate cert = keyStore.getCertificate(alias);
                if (cert != null && cert.equals(x509Certificate)) {
                    return (PrivateKey) keyStore.getKey(alias, keyPassword);
                }
            }
        } catch (GeneralSecurityException e) {
            throw new IOException("Error occurred when loading private key", e);
        }
        return null;
    }

    /**
     * <p>Loads key store form specified file.</p>
     *
     * @param keyStoreContent the key store content
     */
    private KeyStore loadKeyStore(byte[] keyStoreContent) {

        InputStream inputStream = null;
        KeyStore keyStore = null;

        try {
            inputStream = new ByteArrayInputStream(keyStoreContent);

            if (inputStream != null) {

                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(inputStream, keystorePassword);
            }

            return keyStore;
        } catch (KeyStoreException e) {

            // ignores exception
        } catch (CertificateException e) {

            // ignores exception
        } catch (NoSuchAlgorithmException e) {

            // ignores exception
        } catch (IOException e) {

            // ignores exception
        } finally {

            // finally closes the input stream
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // ignores exception
                }
            }
        }

        return null;
    }

    /**
     * <p>The certificate validator.</p>
     *
     * <p><strong>Thread safety:</strong> This class is immutable and thread safe.</p>
     *
     * @author Jakub Narloch (jmnarloch@gmail.com)
     * @version 1.0
     */
    private class CertificateValidator implements CertificateValidationCallback.CertificateValidator {

        @Override
        public boolean validate(X509Certificate x509Certificate) throws CertificateValidationCallback.CertificateValidationException {
            return true;
        }
    }
}
