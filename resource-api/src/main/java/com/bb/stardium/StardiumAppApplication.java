package com.bb.stardium;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StardiumAppApplication {

    private static final String APP_PROPERTIES = "spring.config.location=" +
            "classpath:/application-app-api.yml" +
            ",classpath:/application-domain-service.yml" +
            ",classpath:/application-security-service.yml";

    public static void main(final String[] args) {
        new SpringApplicationBuilder(StardiumAppApplication.class)
                .properties(APP_PROPERTIES)
                .run(args);
    }
}
