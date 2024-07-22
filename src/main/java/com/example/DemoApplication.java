package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.function.Function;

@SpringBootApplication
public class DemoApplication {


    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        String functionServerPort = System.getenv("FUNCTIONS_HTTPWORKER_PORT");
        if (functionServerPort != null) {
            app.setDefaultProperties(Collections
                    .singletonMap("server.port", functionServerPort));
        }
        app.setWebApplicationType(WebApplicationType.REACTIVE);

        app.run(args);
     //   SpringApplication.run(DemoApplication.class);
    }
}
