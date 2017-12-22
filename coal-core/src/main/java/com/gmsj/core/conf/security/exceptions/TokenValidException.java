package com.gmsj.core.conf.security.exceptions;

public class TokenValidException extends RuntimeException {

    public static TokenValidException tokenValidException() {
        return new TokenValidException();
    }
}
