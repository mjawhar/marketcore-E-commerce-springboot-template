package com.marketcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarketCoreProV3Application {
    public static void main(String[] args) {
        SpringApplication.run(MarketCoreProV3Application.class, args);
    }
}
