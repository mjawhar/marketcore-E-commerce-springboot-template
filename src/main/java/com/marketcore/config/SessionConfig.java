package com.marketcore.config;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration pour gérer le cycle de vie des sessions HTTP
 * Nettoie automatiquement les ressources quand une session expire
 */
@Configuration
public class SessionConfig {

    private static final Logger logger = LoggerFactory.getLogger(SessionConfig.class);
    private static int activeSessions = 0;

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                activeSessions++;
           }

            @Override
            public void sessionDestroyed(HttpSessionEvent se) {
                activeSessions--;

                // Nettoyer les attributs de session pour libérer la mémoire
                se.getSession().getAttributeNames().asIterator()
                    .forEachRemaining(attr -> se.getSession().removeAttribute(attr));
            }
        };
    }
}

