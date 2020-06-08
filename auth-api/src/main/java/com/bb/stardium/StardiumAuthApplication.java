package com.bb.stardium;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StardiumAuthApplication {

    private static final String AUTH_PROPERTIES = "spring.config.location=" +
            "classpath:/application-auth-api.yml" +
            ",classpath:/application-domain-service.yml" +
            ",classpath:/application-security.yml";

    public static void main(final String[] args) {
        new SpringApplicationBuilder(StardiumAuthApplication.class)
                .properties(AUTH_PROPERTIES)
                .run(args);
    }
}

