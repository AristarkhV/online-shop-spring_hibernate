package com.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {

    private static final Logger logger = Logger.getLogger(HashUtil.class);

    public static byte[] getRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String getSaltedPassword(String password, byte[] salt) {
        if (password == null || password.isEmpty() || salt == null || isEmpty(salt)) {
            logger.error("Empty arguments when trying to get salted password!");
            throw new IllegalArgumentException("Arguments can not be empty");
        }
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            for (byte b : hashBytes) {
                hashPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("Can't find algorithm for hashing", e);
        }
        return hashPassword.toString();
    }

    private static boolean isEmpty(byte[] salt) {
        boolean empty = true;
        for (byte element : salt) {
            if (element != 0) {
                empty = false;
                break;
            }
        }
        return empty;
    }
}
