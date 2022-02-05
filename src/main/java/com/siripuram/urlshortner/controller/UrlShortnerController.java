package com.siripuram.urlshortner.controller;

import com.siripuram.urlshortner.dto.UrlShortnerDTO;
import com.siripuram.urlshortner.service.UrlShortnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlShortnerController {

    private static final Logger LOG = LoggerFactory.getLogger(UrlShortnerController.class);

    private final UrlShortnerService urlShortnerService;

    public UrlShortnerController(UrlShortnerService urlShortnerService) {
        this.urlShortnerService = urlShortnerService;
    }

    @PostMapping("createShortUrl")
    public String convertToShortUrl(@RequestBody UrlShortnerDTO request) {
        LOG.info("Calling convertToShortUrl():");
        String returnShortUrlValue = urlShortnerService.convertToShortUrl(request);
        LOG.info("Hari's returnShortUrlValue:"+returnShortUrlValue);

        return returnShortUrlValue;
    }

    @GetMapping(value = "{shortUrl:^(?!index).*}")
    //@GetMapping(value = "{shortUrl:(?!.*-from-).+}.html")

    //@GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {

        LOG.info("Entering getAndRedirect():");
        String inputUrl = urlShortnerService.getOriginalUrl(shortUrl);
        LOG.info("getAndRedirect() output as original URL:{}",inputUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(inputUrl))
                .build();
    }


}
