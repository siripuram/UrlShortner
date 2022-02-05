package com.siripuram.urlshortner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortnerApplication {

    private static final Logger LOG = LoggerFactory.getLogger(UrlShortnerApplication.class);

    public static void main(String[] args) {

        LOG.info("Staring my UrlShortnerApplication ... ");
        SpringApplication.run(UrlShortnerApplication.class, args);
    }

}
