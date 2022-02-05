package com.siripuram.urlshortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
public class UrlShortnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortnerApplication.class, args);
    }

    @GetMapping("/myurl")
    public String sayHello(@RequestParam(value = "Welcome to ", defaultValue = "Siripuram's URL Shortner service !!!") String name) {
        return String.format("Hello to %s!", name);
    }
}
