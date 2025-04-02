package com.jhojan.springboot.crudjpa.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class JWTConfig {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";

}
