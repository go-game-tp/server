package org.gogame.server.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class GameServiceConfig {
    @Bean
    Random random() {
        return new Random();
    }
}
