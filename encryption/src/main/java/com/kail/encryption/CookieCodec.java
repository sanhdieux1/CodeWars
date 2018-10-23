package com.kail.encryption;

import com.kail.encryption.model.BaseUser;
import com.kail.encryption.model.InvalidUser;
import com.kail.encryption.model.MyPermission;
import com.kail.encryption.model.MyUser;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author dapeng
 */
public class CookieCodec {

    private static final Logger LOGGER = Logger.getLogger(CookieCodec.class.getName());
    private static final int SIZE = 4 /*role.orginal*/ + 8 /*id*/ + 8 /*ts*/;

    private byte[] salt = loadSalt();
    private final int size;

    CookieCodec() {
        this.size = SIZE + salt.length;
    }

    public byte[] encode(MyUser myUser) {
        MessageDigest digest = md();
        digest.update(salt);

        byte[] buffer = new byte[size];

        ByteBuffer bb = ByteBuffer.wrap(buffer);
        bb.putInt(myUser.getPermission().ordinal());
        bb.putLong(myUser.getUserId());
        bb.putLong(myUser.getExpiry());

        digest.update(buffer, 0, 20);
        for(byte b : buffer){
            System.out.print(b);System.out.print(",");
        }
        bb.put(digest.digest());

        System.out.println();
        for(byte b : buffer){
            System.out.print(b);System.out.print(",");
        }
        return buffer;
    }

    public BaseUser decode(byte[] message) {
        if (message.length != size) {
            LOGGER.log(Level.WARNING, "Invalid with error_code {0}, {1}", new Object[]{message.length, size});
            return InvalidUser.INSTANCE;
        }
        System.out.print("\nmessage:\n" );
        for(byte b : message){
            System.out.print(b);System.out.print(",");
        }
        MessageDigest digest = md();
        digest.update(salt);
        digest.update(message, 0, 20);

        byte[] hash = digest.digest();
        System.out.print("\nhash:\n");
        for(byte b : hash){
            System.out.print(b);
            System.out.print(",");
        }

        for (int i = 0; i < salt.length; i++) {
            if (hash[i] != message[i + 20]) {
                System.out.println("Invalid User");
                return InvalidUser.INSTANCE;
            }
        }

        ByteBuffer bb = ByteBuffer.wrap(message);

        int ordinal = bb.getInt();
        long id = bb.getLong();
        long timestamp = bb.getLong();

        if (timestamp < System.currentTimeMillis() || ordinal >= MyPermission.values().length) {
            // expired
            return InvalidUser.INSTANCE;
        }

        return new MyUser(id, MyPermission.values()[ordinal], timestamp);
    }

    protected static MessageDigest md() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.WARNING, "not possbile, jdk requires sha-256. {0}", ex);
            throw new RuntimeException("not possbile, jdk requires sha-256");
        }
    }

    private static byte[] loadSalt() {
        final String saltStr = "ABC";
        MessageDigest digest = md();
        digest.update(saltStr.getBytes(StandardCharsets.UTF_8));
        return digest.digest();
    }
}
