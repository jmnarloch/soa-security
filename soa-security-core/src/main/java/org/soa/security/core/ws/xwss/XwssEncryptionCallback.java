/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */
package org.soa.security.core.ws.xwss;

import com.sun.xml.wss.impl.callback.CertificateValidationCallback;
import com.sun.xml.wss.impl.callback.DecryptionKeyCallback;
import com.sun.xml.wss.impl.callback.EncryptionKeyCallback;

import javax.crypto.SecretKey;
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
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * <p>An {@link XwssEncryptionCallback} implementation for encrypting the messages.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class XwssEncryptionCallback implements XwssSecurityCallback {

    /**
     * <p>Represents the keystore.</p>
     */
    private KeyStore keyStore;

    /**
     * <p>Represents the keystore password.</p>
     */
    private char[] keyStorePassword;

    /**
     * <p>Represents the key password.</p>
     */
    private char[] keyPassword;

    /**
     * <p>Creates new instance of {@link XwssEncryptionCallback} class.</p>
     *
     * @param keyStoreContent  the key store content
     * @param keyStorePassword the key store password
     * @param keyPassword      the key password
     */
    public XwssEncryptionCallback(byte[] keyStoreContent, char[] keyStorePassword, char[] keyPassword) {

        this.keyStore = loadKeyStore(keyStoreContent);
        this.keyStorePassword = keyStorePassword;
        this.keyPassword = keyPassword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Callback callback) {

        return callback instanceof CertificateValidationCallback
                || callback instanceof DecryptionKeyCallback
                || callback instanceof EncryptionKeyCallback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Callback callback) throws IOException, UnsupportedCallbackException {

        if (callback instanceof CertificateValidationCallback) {

            handleCertificateValidationCallback((CertificateValidationCallback) callback);
        } else if (callback instanceof DecryptionKeyCallback) {

            handleDecryptionKeyCallback((DecryptionKeyCallback) callback);
        } else if (callback instanceof EncryptionKeyCallback) {

            handleEncryptionKeyCallback((EncryptionKeyCallback) callback);
        }
    }

    /**
     * <p>Handles the X509 Certificate request.</p>
     *
     * @param encryptionKeyCallback  the encryption callback
     * @param x509CertificateRequest the X509 Certificate request
     *
     * @throws IOException if any error occurs
     */
    private void handleX509CertificateRequest(EncryptionKeyCallback encryptionKeyCallback,
                                              EncryptionKeyCallback.X509CertificateRequest x509CertificateRequest) throws IOException {

        if (x509CertificateRequest instanceof EncryptionKeyCallback.AliasX509CertificateRequest) {

            EncryptionKeyCallback.AliasX509CertificateRequest aliasRequest =
                    (EncryptionKeyCallback.AliasX509CertificateRequest) x509CertificateRequest;

            aliasRequest.setX509Certificate(getCertificate(aliasRequest.getAlias()));
        }
    }

    /**
     * <p>Handles the private key request.</p>
     *
     * @param decryptionKeyCallback the decryption callback
     * @param privateKeyRequest     the private key request
     *
     * @throws IOException if any error occurs
     */
    private void handlePrivateKeyRequest(DecryptionKeyCallback decryptionKeyCallback,
                                         DecryptionKeyCallback.PrivateKeyRequest privateKeyRequest) throws IOException {

        if (privateKeyRequest instanceof DecryptionKeyCallback.X509CertificateBasedRequest) {

            DecryptionKeyCallback.X509CertificateBasedRequest x509Request =
                    (DecryptionKeyCallback.X509CertificateBasedRequest) privateKeyRequest;

            x509Request.setPrivateKey(getPrivateKey(x509Request.getX509Certificate()));
        }
    }

    /**
     * <p>Handles the encryption key callback.</p>
     *
     * @param encryptionKeyCallback the encryption callback
     *
     * @throws IOException if any error occurs
     */
    private void handleEncryptionKeyCallback(EncryptionKeyCallback encryptionKeyCallback) throws IOException {

        if (encryptionKeyCallback.getRequest() instanceof EncryptionKeyCallback.SymmetricKeyRequest) {

            handleSymmetricKeyRequest(encryptionKeyCallback, (EncryptionKeyCallback.SymmetricKeyRequest) encryptionKeyCallback.getRequest());
        } else if (encryptionKeyCallback.getRequest() instanceof EncryptionKeyCallback.X509CertificateRequest) {

            handleX509CertificateRequest(encryptionKeyCallback,
                    (EncryptionKeyCallback.X509CertificateRequest) encryptionKeyCallback.getRequest());
        }
    }

    /**
     * <p>Handles the decryption key callback.</p>
     *
     * @param decryptionKeyCallback the decryption callback
     *
     * @throws IOException if any error occurs
     */
    private void handleDecryptionKeyCallback(DecryptionKeyCallback decryptionKeyCallback) throws IOException {

        if (decryptionKeyCallback.getRequest() instanceof DecryptionKeyCallback.PrivateKeyRequest) {

            handlePrivateKeyRequest(decryptionKeyCallback, (DecryptionKeyCallback.PrivateKeyRequest) decryptionKeyCallback.getRequest());
        } else if (decryptionKeyCallback.getRequest() instanceof DecryptionKeyCallback.SymmetricKeyRequest) {

            handleSymmetricKeyRequest(decryptionKeyCallback, (DecryptionKeyCallback.SymmetricKeyRequest) decryptionKeyCallback.getRequest());
        }
    }

    /**
     * <p>Handles the symmetric key callback.</p>
     *
     * @param encryptionKeyCallback the encryption callback
     * @param symmetricKeyRequest   the symmetric key request
     *
     * @throws IOException if any error occurs
     */
    private void handleSymmetricKeyRequest(EncryptionKeyCallback encryptionKeyCallback,
                                           EncryptionKeyCallback.SymmetricKeyRequest symmetricKeyRequest) throws IOException {

        EncryptionKeyCallback.AliasSymmetricKeyRequest aliasSymmetricKeyRequest =
                (EncryptionKeyCallback.AliasSymmetricKeyRequest) symmetricKeyRequest;

        aliasSymmetricKeyRequest.setSymmetricKey(getSymmetricKey(aliasSymmetricKeyRequest.getAlias()));
    }

    /**
     * <p>Handles the decryption key callback.</p>
     *
     * @param decryptionKeyCallback the decryption key callback
     * @param symmetricKeyRequest   the symmetric key request
     *
     * @throws IOException if any error occurs
     */
    private void handleSymmetricKeyRequest(DecryptionKeyCallback decryptionKeyCallback,
                                           DecryptionKeyCallback.SymmetricKeyRequest symmetricKeyRequest) throws IOException {
        DecryptionKeyCallback.AliasSymmetricKeyRequest aliasSymmetricKeyRequest =
                (DecryptionKeyCallback.AliasSymmetricKeyRequest) symmetricKeyRequest;

        aliasSymmetricKeyRequest.setSymmetricKey(getSymmetricKey(aliasSymmetricKeyRequest.getAlias()));
    }

    /**
     * <p>Handles the certificate validation callback.</p>
     *
     * @param certificateValidationCallback the certificate validation callback
     */
    private void handleCertificateValidationCallback(CertificateValidationCallback certificateValidationCallback) {

        // TODO this method probably won't be used
        // empty method
    }

    /**
     * <p>Retrieves the symmetric key from key store.</p>
     *
     * @param alias the key store alias
     *
     * @return the retrieved secret key
     *
     * @throws IOException if any error occurs
     */
    private SecretKey getSymmetricKey(String alias) throws IOException {

        try {

            return (SecretKey) keyStore.getKey(alias, keyPassword);
        } catch (KeyStoreException e) {

            throw new IOException("Error occurred when loading secret key.", e);
        } catch (NoSuchAlgorithmException e) {

            throw new IOException("Error occurred when loading secret key.", e);
        } catch (UnrecoverableKeyException e) {

            throw new IOException("Error occurred when loading secret key.", e);
        }
    }

    /**
     * <p>Retrieves the X509 Certificate from key store.</p>
     *
     * @param alias the key store alias
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
     * @param keyStoreContent the path to key store
     */
    private KeyStore loadKeyStore(byte[] keyStoreContent) {

        InputStream inputStream = null;
        KeyStore keyStore = null;

        try {
            inputStream = new ByteArrayInputStream(keyStoreContent);

            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(inputStream, keyStorePassword);

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
}
