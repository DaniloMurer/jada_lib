package com.danilojakob.util.security;

/**
 * @copyright Danilo Jakob 2019
 */

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.math.BigInteger;
import java.security.*;

import org.apache.commons.codec.binary.Base64;

/**
 * Class for the complete Encryption Process
 */
public class EncryptionHelper {

    private final int CAESAR_SHIFT = 4;
    private Cipher cipher_;

    /**
     * Method to encrypt a text with caesar
     * @param text {@link String} text that needs to be encrypted
     * @param encode {@link Boolean} if the text needs to encoded or decoded
     * @return {@link String} encrypted text
     */
    public String caesarEncryption(String text, boolean encode) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            //Get a char from the text and add the shift
            char pickedChar = text.charAt(i);
            int asciiCode = pickedChar;
            if (encode) {
                asciiCode += CAESAR_SHIFT;
            } else {
                asciiCode -= CAESAR_SHIFT;
            }
            pickedChar = (char) asciiCode;
            stringBuilder.append(pickedChar);
        }
        return stringBuilder.toString();
    }

    /**
     * Method to encrypt a text by replacing all occurrences of
     * an defined character
     * @param text {@link String} text that needs to be encrypted
     * @param characterToReplace {@link String} character that needs to be replaced
     * @param characterReplacement {@link String} character that comes replaces the character
     * @return {@link String} encrypted text
     */
    public String replacementEncryption(String text, String characterToReplace, String characterReplacement) {
        text = text.replace(characterToReplace.charAt(0), characterReplacement.charAt(0));
        return text;
    }

    /**
     * Method for hashing in string
     * @param toHash {@link String} string to hash
     * @return {@link String} hashed String
     */
    public String hash(String toHash) {
        //Variable to store the hashed String
        String hashed;
        //Hashing password with SHA-256
        try {
            //Hash algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] md = messageDigest.digest(toHash.getBytes());
            BigInteger no = new BigInteger(1, md);
            hashed = no.toString(16);

            while(hashed.length() < 32) {
                hashed = "0" + hashed;
            }
        } catch(Exception e) {
            //If there is an error with the algorithm, the hashes password is set to error
            return "error";
        }
        return hashed;
    }

    /**
     * Method for encrypting text with a PublicKey
     * @param text {@link String} Text to encrypt
     * @param key {@link PublicKey} public key
     * @return {@link String} encrypted text
     * @throws Exception
     */
    public String encryptText(String text, PublicKey key) throws Exception {
        cipher_ = Cipher.getInstance("RSA");
        cipher_.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(cipher_.doFinal(text.getBytes("UTF-8")));
    }

    /**
     * Method for decrypting Text with a PrivateKey
     * @param text {@link String} Text to encrypt
     * @param key {@link PrivateKey} private key
     * @return {@link String} decrypted Text
     * @throws Exception
     */
    public String decryptText(String text, PrivateKey key) throws Exception {
        cipher_ = Cipher.getInstance("RSA");
        cipher_.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher_.doFinal(Base64.decodeBase64(text)), "UTF-8");
    }

    /**
     * Method for encrypting a file
     * @param file {@link File} file that needs to be encrypted
     * @param key {@link PrivateKey} public key
     */
    public void encryptFile(File file, PublicKey key) {
        try {
            cipher_ = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
        } catch (NoSuchPaddingException ex) {
        }
        try {
            cipher_.init(Cipher.ENCRYPT_MODE, key);
            writeToFile(file, cipher_.doFinal(getFileInBytes(file)));
        } catch (InvalidKeyException ex) {
        } catch (IOException ex) {
        } catch (IllegalBlockSizeException ex) {
        } catch (BadPaddingException ex) {

        }
    }

    public void decryptFile(File file, PrivateKey key) {
        try {
            cipher_ = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
        } catch (NoSuchPaddingException ex) {
        }

        try {
            cipher_.init(Cipher.DECRYPT_MODE, key);
            writeToFile(file, cipher_.doFinal(getFileInBytes(file)));
        } catch (InvalidKeyException ex) {
        } catch (IOException ex) {
        } catch (IllegalBlockSizeException ex) {
        } catch (BadPaddingException ex) {
        }
    }

    /**
     * Method for writing content into a file
     * @param output {@link File} file which needs to be written to
     * @param toWrite {@link Byte[]} bytes to write
     * @return {@link File}
     * @throws Exception
     */
    private File writeToFile(File output, byte[] toWrite) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(output);
            fileOutputStream.write(toWrite);
            fileOutputStream.close();
        } catch (Exception ex) {
        }
        return output;
    }

    /**
     * Method to get content as bytes from the file
     * @param file {@link File}
     * @return {@link Byte[]}
     * @throws IOException
     */
    private byte[] getFileInBytes(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        return bytes;
    }
}
