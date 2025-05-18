package com.messaging;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;


public class ClasspathDebug {
    @Test
    void printWhereSpringApplicationComesFrom() {
        System.out.println("SpringApplication loaded from: " +
                SpringApplication.class.getProtectionDomain().getCodeSource());
    }
}