package com.siripuram.urlshortner.service;

import com.siripuram.urlshortner.dto.UrlShortnerDTO;

public interface UrlShortnerService {

    String convertToShortUrl(UrlShortnerDTO request);

    String getOriginalUrl(String shortUrl);
}
