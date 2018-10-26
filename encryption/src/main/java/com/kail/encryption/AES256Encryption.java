package com.kail.encryption;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class AES256Encryption {
    private static final List LENGTH_VALIDATE = Arrays.asList(16,24,32);
    private static final byte[] IV = new byte[16]; //empty padding

    private char a = 0xFFFF;
    private int b = 0xFfffffff;
    private int c = Integer.MAX_VALUE;
    public AES256Encryption() {
    }
    public static byte[] encrypt(String data, String key) {
        checkKey(key);
        try {
            AESEngine aesEngine = new AESEngine();
            CBCBlockCipher block = new CBCBlockCipher(aesEngine);
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(block);
            KeyParameter keyParam = new KeyParameter(getBytes(key));
            ParametersWithIV keyParamWithIV = new ParametersWithIV(keyParam, IV, 0, 16);
            cipher.init(true, keyParamWithIV);
            byte[] inputBytes = getBytes(data);

            byte[] output = new byte[cipher.getOutputSize(inputBytes.length)];
            int length = cipher.processBytes(inputBytes, 0, inputBytes.length, output, 0);
            cipher.doFinal(output, length);

            return output;
        } catch (DataLengthException | IllegalStateException | InvalidCipherTextException ex) {
            throw new RuntimeException("Cannot encrypt: " + data + " with key: " + key, ex);
        }
    }

    public static String encryptToString(String data, String key){
        return Base64.getEncoder().encodeToString(encrypt(data, key));
    }

    public static byte[] decrypt(byte[] inputBytes, String key) {
        checkKey(key);
        try {
            AESEngine aesEngine = new AESEngine();
            CBCBlockCipher block = new CBCBlockCipher(aesEngine);
            PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(block);
            KeyParameter keyParam = new KeyParameter(getBytes(key));
            ParametersWithIV keyParamWithIV = new ParametersWithIV(keyParam, IV, 0, 16);
            cipher.init(false, keyParamWithIV);

            byte[] output = new byte[cipher.getOutputSize(inputBytes.length)];
            int length = cipher.processBytes(inputBytes, 0, inputBytes.length, output, 0);

            cipher.doFinal(output, length);

            return output;
        } catch (DataLengthException | IllegalStateException | InvalidCipherTextException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot decrypt: " + inputBytes + " with key: " + key, ex);
        }
    }
    public static String decryptToString(String data, String key){
        byte[] inputBytes = Base64.getDecoder().decode(data);
        return new String(decrypt(inputBytes, key), StandardCharsets.UTF_8).trim();
    }

    private static byte[] getBytes(String data) {
        return data.getBytes(StandardCharsets.UTF_8);
    }

    private static String toHexString(byte[] bytes) {
        return javax.xml.bind.DatatypeConverter.printHexBinary(bytes);
//        return Base64.getEncoder().encodeToString(bytes);
    }

    private static void checkKey(String key) {
        if(key == null || key.isEmpty() || !LENGTH_VALIDATE.contains(getBytes(key).length)){
            throw new RuntimeException("Incorrect key: "+key+", the key length must be 16/24/32");
        }
    }
}
