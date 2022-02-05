package com.siripuram.urlshortner.service.impl;

import antlr.StringUtils;
import com.siripuram.urlshortner.dto.UrlShortnerDTO;
import com.siripuram.urlshortner.entity.UrlCheckSum;
import com.siripuram.urlshortner.entity.UrlEntity;
import com.siripuram.urlshortner.repository.UrlCheckSumRepository;
import com.siripuram.urlshortner.repository.UrlShortnerRepository;
import com.siripuram.urlshortner.service.BaseConversion;
import com.siripuram.urlshortner.service.UrlShortnerService;
import com.siripuram.urlshortner.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;


@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {

    private final UrlShortnerRepository urlShortnerRepository;
    private final BaseConversion conversion;
    private final UrlCheckSumRepository checkSumRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UrlShortnerServiceImpl.class);

    public UrlShortnerServiceImpl(UrlShortnerRepository urlShortnerRepository, BaseConversion baseConversion, UrlCheckSumRepository checkSumRepository ) {
        this.urlShortnerRepository = urlShortnerRepository;
        this.conversion = baseConversion;
        this.checkSumRepository = checkSumRepository;

    }

    @Override
    public String convertToShortUrl(UrlShortnerDTO urlShortnerDTO) {

        UrlUtils urlUtils = new UrlUtils();
        String checkSum = urlUtils.computeCheckSum(urlShortnerDTO.getInputURL());
        LOG.info("Checksum{}:",checkSum);

        //lookup to see if the input URL is already shortened by the system

        var entityCheckSum = checkSumRepository.findById(checkSum);

        if (entityCheckSum.isPresent()) {
            LOG.info("I have shortURL{}:",entityCheckSum);
            return entityCheckSum.get().getShortUrl();
        }

        var url = new UrlEntity();
        Date currentDate = new Date();
        LOG.info("All input URL's from the table:{}",urlShortnerRepository.findAll());

        url.setInputUrl(urlShortnerDTO.getInputURL());

        long time = currentDate.getTime()+7*24*60*60*1000; // 7 Days for now ...
        Date expiryDate = new Date(time);

        url.setExpiryDate(expiryDate);
        url.setCreatedDate(new Date());

        var entity = urlShortnerRepository.save(url);

        // I have connection with ID + input URL  //entity  // new system of records

        // I need a reference of input URL existence in the system.


        String shortenURL =  conversion.encode(entity.getId());

        var checkSumEntity = new UrlCheckSum();
        checkSumEntity.setCheckSum(checkSum);
        checkSumEntity.setShortUrl(shortenURL);

        checkSumRepository.save(checkSumEntity);

        return shortenURL;
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        var id = conversion.decode(shortUrl);
        var entity = urlShortnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        LOG.info("Expiry Date:{}",entity.getExpiryDate());
        if (entity.getExpiryDate() != null && entity.getExpiryDate().before(new Date())){
            urlShortnerRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getInputUrl();
    }
}
