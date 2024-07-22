package com.example;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class api implements Function<Mono<String>, Mono<String>> {

    public Mono<String> apply(Mono<String> mono) {
        return mono.map(name -> ("Hello from Spring, Pulumi!"));
    }
}
