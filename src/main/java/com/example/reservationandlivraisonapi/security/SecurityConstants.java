package com.example.reservationandlivraisonapi.security;

public class SecurityConstants  {
    public static final String SECRET = "pfa@livres.com";
    public static final long EXPIRATION_TIME = 864_000_000; //10 j
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
