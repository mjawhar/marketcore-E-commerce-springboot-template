package com.marketcore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigurationLogger {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationLogger.class);

    private final Environment env;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    public ConfigurationLogger(Environment env) {
        this.env = env;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logConfiguration() {
        logger.info("==== Configuration de l'application ====");
        logger.info("Profile actif: " + activeProfile);
        logger.info("Base de données URL: " + env.getProperty("spring.datasource.url"));
        logger.info("Base de données User: " + env.getProperty("spring.datasource.username"));
        logger.info("MYSQL_ADDON_HOST: " + env.getProperty("MYSQL_ADDON_HOST"));
        logger.info("MYSQL_ADDON_PORT: " + env.getProperty("MYSQL_ADDON_PORT"));
        logger.info("MYSQL_ADDON_DB: " + env.getProperty("MYSQL_ADDON_DB"));
        logger.info("==============================");
    }
}
