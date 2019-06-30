package com.kail.encryption;

import com.kail.encryption.model.BaseUser;
import com.kail.encryption.model.MyPermission;
import com.kail.encryption.model.MyUser;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String args[]){
        cookieEncryp();
    }

    public static void aes256(){
        final String key = "ABHDKELS123DSASF";
        String data = "The filter is applied for both lists. The list(s) which contains filtered Username will display corresponding record.\nFix the height of 2 boxes and add scrollbar if it extends the height";

        String cipher = AES256Encryption.encryptToString(data, key);
        System.out.println(cipher);
        System.out.println(AES256Encryption.decryptToString(cipher, key));
    }

    public static void cookieEncryp(){
        MyUser user = new MyUser(1234L, MyPermission.ADMIN, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));
        CookieCodec decode = new CookieCodec();
        String ciper = Base64.getUrlEncoder().encodeToString(decode.encode(user));
        byte[] hash = Base64.getUrlDecoder().decode(ciper);
        BaseUser userDecode = decode.decode(hash);
        System.out.println(userDecode);
    }
}
