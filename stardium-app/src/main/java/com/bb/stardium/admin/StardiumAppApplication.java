package com.bb.stardium.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StardiumAppApplication {

    public static void main(final String[] args) {
        new SpringApplicationBuilder(StardiumAppApplication.class)
                .properties(
                        "spring.config.location=" +
                                "classpath:/application-domain.yml" +
                                ", file:/com/bb/stardium/" +
                                ", file:/com/bb/stardium/application-domain.yml"
                ).run(args);
    }
}