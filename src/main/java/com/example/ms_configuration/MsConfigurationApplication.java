package com.example.ms_configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class MsConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsConfigurationApplication.class, args);
    }

}
