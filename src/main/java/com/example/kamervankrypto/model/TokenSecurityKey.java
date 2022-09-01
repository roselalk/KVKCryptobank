package com.example.kamervankrypto.model;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class TokenSecurityKey {
    // this model does nothing else than have and store a key. The key is normally saved in a way way more secured location
    // the key is used to sign the JWT Token. This model class just exist to simulate a separate stored key.
    // THere is no way to alter the key, the only option is to get one from this class. The method is static, and the
    // key is identical for each JWT for each run of the server.

    private static Key secretKey;

    // CONSTRUCTOR

    // METHODS

    // GETTERS AND SETTERS


    public static Key getSecretKey() {
        if (secretKey == null) {
            secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        }

        return secretKey;
    }
}
