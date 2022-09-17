package com.example.essentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EssentialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EssentialsApplication.class, args);
    }

}

@RestController
class TestController {

    @GetMapping("/health")
    public String health() {
        return "Hello world!";
    }
}