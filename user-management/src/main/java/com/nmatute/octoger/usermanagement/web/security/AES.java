package com.nmatute.octoger.usermanagement.web.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Clase para encriptar y desencriptar datos sensibles.
 *
 * @author: NM4TT
 */
public class AES implements PasswordEncoder {

    private static final String KEY = "mysecretkey12345";
    private static final String ALGORITHM = "AES";
    private final Logger log = LoggerFactory.getLogger(AES.class);

    /**
     * Metodo para realizar un encode o un decode.
     * @param input Es la data sensible.
     * @param action Es la acción a tomar.
     * @return Data procesada.
     */
    public String perform(String input, Action action){
        String output = "";
        try {
            SecretKeySpec keySpec = null;
            Cipher cipher = null;

            switch (action) {
                case ENCRYPT:
                        keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
                        cipher = Cipher.getInstance(ALGORITHM);
                        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
                        byte[] encryptedTextBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
                        output = Base64.getEncoder().encodeToString(encryptedTextBytes);
                    break;
            
                case DECRYPT:
                    keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
                    cipher = Cipher.getInstance(ALGORITHM);
                    cipher.init(Cipher.DECRYPT_MODE, keySpec);
                    byte[] decryptedTextBytes = cipher.doFinal(Base64.getDecoder().decode(input));
                    output = new String(decryptedTextBytes, StandardCharsets.UTF_8); 
            }

            return output;

        } catch (Exception e) {
            log.debug("ERROR ENCODING WITH DES.java: " + e.getMessage());
        }

        log.debug("Data without encrypt: " + input + "\n" + "Data after encrypt: " + output);
        return null;
    }

    /**
     * Enum para clasificar Acciones del metodo perform().
     */
    public enum Action{
        ENCRYPT,
        DECRYPT
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String output = perform(rawPassword.toString(), Action.ENCRYPT);
        log.debug("ENCODE OUTPUT: " + output);
        return output;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        boolean output = encodedPassword.equals(encode(rawPassword));
        log.debug("MATCHES: " + output);
        return output;
    }
}
