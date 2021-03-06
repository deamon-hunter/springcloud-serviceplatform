package com.gmsj.core.conf.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gmsj.security")
public class ApplicationSecuritySettings {
    private Token token = new Token();

    public Token getToken() {
        return token;
    }

    public static class Token {
        private String secret;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }
}
