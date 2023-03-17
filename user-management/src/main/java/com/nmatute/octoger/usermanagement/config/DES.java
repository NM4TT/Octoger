package com.nmatute.octoger.usermanagement.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.nmatute.octoger.usermanagement.Common.Common;

public class DES {

    private static final Logger logger = LoggerFactory.getLogger(DES.class);
    private final String INSTANCE = "DES/ECB/PKCS5Padding";
    private final Charset STANDARD = StandardCharsets.UTF_8;
    
    @Value("${server.secretEncryptionKey}")
    private String myKey;
    byte[] encodedKey = Base64.getEncoder().encode(myKey.getBytes(STANDARD));
    SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");

    public String perform(String input, Action action){
         
        try {
            Cipher cipher = Cipher.getInstance(INSTANCE);
            byte[] inputBytes = new byte[0];
            byte[] outputBytes = new byte[0];
            switch (action) {
                case ENCRYPT:
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    inputBytes = input.getBytes(STANDARD);
                    outputBytes = cipher.doFinal(inputBytes);
                    break;
            
                case DECRYPT:
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                    inputBytes = input.getBytes(STANDARD);
                    outputBytes = cipher.doFinal(inputBytes);
                    break;   
            }

            return new String(outputBytes, STANDARD);

        } catch (Exception e) {
            logger.debug(Common.exceptionDetail(e.getStackTrace()));
        }

        return null;
    }

    enum Action{
        ENCRYPT,
        DECRYPT
    }
}
