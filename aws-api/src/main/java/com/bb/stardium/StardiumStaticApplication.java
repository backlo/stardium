package com.bb.stardium;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StardiumStaticApplication {

    private static final String AUTH_PROPERTIES = "spring.config.location=" +
            "classpath:/application-aws-api.yml" +
            ",classpath:/application-security-service.yml" +
            ",classpath:/application-domain-service.yml";

    public static void main(final String[] args) {
        new SpringApplicationBuilder(StardiumStaticApplication.class)
                .properties(AUTH_PROPERTIES)
                .run(args);
    }
}
