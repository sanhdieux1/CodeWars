package com.kail.encryption;

import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author sun
 */
public class PasswordHashing {

    private static final BasicPasswordEncryptor crypto = new BasicPasswordEncryptor();

    public static String hash(String password) {
        return crypto.encryptPassword(password);
    }

    public static boolean check(String plain, String encrypted) {
        return crypto.checkPassword(plain, encrypted);
    }
}
