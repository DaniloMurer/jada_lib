/*
 * Copyright (c) Danilo Jakob 2019
 * Code from https://www.geeksforgeeks.org/sha-256-hash-in-java/
 */

package com.danilojakob.util.security;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Hasher {

    /*Method that takes a String as parameter and hashes it*/
    public static String hash(String toHash) {
        //Variable to store the hashed String
        String hashed = "";

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
            return "error";
        }
        return hashed;
    }
}
