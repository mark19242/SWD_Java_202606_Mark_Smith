package org.apprenti.app_bff.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RsaKeyConfig {

    @Bean
    public KeyPair keyPair() {
        try {
            var generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate RSA KeyPair", e);
        }
    }

    @Bean
    public RSAPublicKey publicKey(KeyPair keyPair) {
        return (RSAPublicKey) keyPair.getPublic();
    }

    @Bean
    public RSAPrivateKey privateKey(KeyPair keyPair) {
        return (RSAPrivateKey) keyPair.getPrivate();
    }
}
