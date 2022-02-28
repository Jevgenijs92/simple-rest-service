package com.example.simplerestservice.security.util;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtHelper {
    private static final String SECRET_KEY = "secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());

    public static Algorithm getAlgorithm() {
        return algorithm;
    }
}
