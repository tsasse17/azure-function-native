package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
public class Controller {
  @GetMapping("/")
  public String hello(){
        return "Guten Morgen";
    }
    @Bean
    public static Function<String, String> lower() {
           return String::toLowerCase;
    }
}
