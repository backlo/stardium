package com.bb.stardium;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StardiumSearchApplication {

    private static final String APP_PROPERTIES = "spring.config.location=" +
            "classpath:/application-search-api.yml" +
            ",classpath:/application-domain-service.yml" +
            ",classpath:/application-security-service.yml";

    public static void main(final String[] args) {
        new SpringApplicationBuilder(StardiumSearchApplication.class)
                .properties(APP_PROPERTIES)
                .run(args);
    }
}
