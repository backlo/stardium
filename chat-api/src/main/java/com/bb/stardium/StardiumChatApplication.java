package com.bb.stardium;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StardiumChatApplication {

    private static final String APP_PROPERTIES = "spring.config.location=" +
            "classpath:/application-chat-api.yml" +
            ",classpath:/application-security-service.yml" +
            ",classpath:/application-chat-service.yml";

    public static void main(final String[] args) {
        new SpringApplicationBuilder(StardiumChatApplication.class)
                .properties(APP_PROPERTIES)
                .run(args);
    }
}
