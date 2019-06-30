package com.kail.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

public class AESPKSC7Java {
    public static final String key = "0123456789ABCDED";
    public static final String plaintext = "12345678901";
    private static final byte[] IV = new byte[16]; //empty padding
    public static void doTest() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        byte[] clearData = plaintext.getBytes();
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        AlgorithmParameterSpec IVspec = new IvParameterSpec(IV, 0 ,16);
        // encrypt with PKCS7 padding
        Cipher encrypterWithPad = Cipher.getInstance("AES/CBC/PKCS7PADDING", "BC");
        encrypterWithPad.init(Cipher.ENCRYPT_MODE, secretKey, IVspec);
        byte[] encryptedData = encrypterWithPad.doFinal(clearData);
        System.out.println("Encryped data (" + encryptedData.length + " bytes): \t" + toHexString(encryptedData));

        // encrypt without PKCS7 padding
        Cipher encrypterWithoutPad = Cipher.getInstance("AES/CBC/NOPADDING", "BC");
        encrypterWithPad.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedDataNoPd = encrypterWithPad.doFinal(clearData);
        System.out.println("Encryped data without padding (" + encryptedData.length + " bytes): \t" + toHexString(encryptedDataNoPd));

        // decrypt with PKCS7 pad
        Cipher decrypterWithPad = Cipher.getInstance("AES/CBC/PKCS7PADDING", "BC");
        decrypterWithPad.init(Cipher.DECRYPT_MODE, secretKey, IVspec);
        byte[] buffer1 = new byte[encryptedData.length];
        int decryptLen1 = decrypterWithPad.doFinal(encryptedData, 0, encryptedData.length, buffer1);
        System.out.println("Decrypted with Pad (" + decryptLen1 + " bytes):  \t" + toHexString(buffer1) + "\tplaintext:" + toByteString(buffer1));

        // decrypt without PKCS7 pad
        Cipher decrypterWithoutPad = Cipher.getInstance("AES/CBC/NOPADDING", "BC");
        decrypterWithoutPad.init(Cipher.DECRYPT_MODE, secretKey, IVspec);
        byte[] buffer2 = new byte[encryptedData.length];
        int decryptLen2 = decrypterWithoutPad.doFinal(encryptedData, 0, encryptedData.length, buffer2);
        System.out.println("Decrypted without Pad (" + decryptLen2 + " bytes):\t" + toHexString(buffer2) + "\tplaintext:" + toByteString(buffer2));
    }

    public static void doTest2(){

        byte[] data = plaintext.getBytes(StandardCharsets.UTF_8);
        System.out.println("Data ("+ data.length  + " bytes):"+ toHexString(data));
        byte[] encryped = AES256Encryption.encrypt(plaintext, key);
        System.out.println("Encryped (" + encryped.length + " bytes):\t"+ toHexString(encryped));

        // decrypt
        byte[] decrypted = AES256Encryption.decrypt(encryped, key);
        System.out.println("Decrypted (" + decrypted.length + " bytes):\t"+ toHexString(decrypted));
        System.out.println("Decrypted String:" + new String(decrypted , StandardCharsets.UTF_8));
    }

    private static String toByteString(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }
    private static String toHexString(byte[] bytes) {
        return javax.xml.bind.DatatypeConverter.printHexBinary(bytes);
//        return Base64.getEncoder().encodeToString(bytes);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("key length:" + key.getBytes(StandardCharsets.UTF_8).length);
        AESPKSC7Java.doTest();
        System.out.println();
        AESPKSC7Java.doTest2();
    }
}
