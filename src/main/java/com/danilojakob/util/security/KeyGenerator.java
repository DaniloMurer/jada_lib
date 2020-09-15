package com.danilojakob.util.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

/**
 * Help from:
 * https://www.mkyong.com/java/java-asymmetric-cryptography-example/
 * @copyright Danilo Jakob 2019
 */

/**
 * Class for generating RSA Keys
 */
public class KeyGenerator {

    private KeyPairGenerator keyPairGenerator_;
    private KeyPair keyPair_;
    private PrivateKey privateKey_;
    private PublicKey publicKey_;

    /**
     * Constructor of the class
     * @param length {@link Integer} length of the key/s
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public KeyGenerator(int length) throws NoSuchAlgorithmException, NoSuchProviderException {
        keyPairGenerator_ = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator_.initialize(length);
    }

    /**
     * Method for generating the keys (Private & Public)
     */
    public void generateKeys() {
        keyPair_ = keyPairGenerator_.generateKeyPair();
        privateKey_ = keyPair_.getPrivate();
        publicKey_ = keyPair_.getPublic();
    }

    /**
     * Method for saving the keys as files on the server
     * @param filePath {@link String} where to save the key
     * @param key {@link Byte[]} the key as byte array
     * @throws IOException
     */
    public void saveKeys(String filePath, byte[] key) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(key);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public PublicKey getPublicKey() {return publicKey_;}
    public PrivateKey getPrivateKey() {return privateKey_;}
}
