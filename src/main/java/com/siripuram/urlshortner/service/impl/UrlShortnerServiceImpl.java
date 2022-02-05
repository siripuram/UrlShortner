package com.siripuram.urlshortner.service.impl;

import com.siripuram.urlshortner.controller.UrlShortnerController;
import com.siripuram.urlshortner.dto.UrlShortnerDTO;
import com.siripuram.urlshortner.entity.UrlEntity;
import com.siripuram.urlshortner.repository.UrlShortnerRepository;
import com.siripuram.urlshortner.service.BaseConversion;
import com.siripuram.urlshortner.service.UrlShortnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {

    private final UrlShortnerRepository urlShortnerRepository;
    private final BaseConversion conversion;

    private static final Logger LOG = LoggerFactory.getLogger(UrlShortnerServiceImpl.class);

    public UrlShortnerServiceImpl(UrlShortnerRepository urlShortnerRepository, BaseConversion baseConversion) {
        this.urlShortnerRepository = urlShortnerRepository;
        this.conversion = baseConversion;
    }

    @Override
    public String convertToShortUrl(UrlShortnerDTO urlShortnerDTO) {
        var url = new UrlEntity();
        int expiryDays = 90;
        Date currentDate = new Date();
        LOG.info("All input URL's from the table:"+urlShortnerRepository.findAll());

        url.setInputUrl(urlShortnerDTO.getInputURL());
        //url.setInputUrl("https://www.apple.com/iphone");

        long time = currentDate.getTime()+7*24*60*60*1000; // 7 Days for now ...
        Date expiryDate = new Date(time);

        //url.setExpiryDate(urlShortnerDTO.getUrlExpiryDate());
        url.setExpiryDate(expiryDate);
        url.setCreatedDate(new Date());

        var entity = urlShortnerRepository.save(url);

        return conversion.encode(entity.getId());
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        var id = conversion.decode(shortUrl);
        var entity = urlShortnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        LOG.info("Expiry Date:"+entity.getExpiryDate());
        if (entity.getExpiryDate() != null && entity.getExpiryDate().before(new Date())){
            urlShortnerRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getInputUrl();
    }
}
