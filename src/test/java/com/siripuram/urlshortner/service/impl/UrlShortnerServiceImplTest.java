package com.siripuram.urlshortner.service.impl;

import com.siripuram.urlshortner.dto.UrlShortnerDTO;
import com.siripuram.urlshortner.entity.UrlEntity;
import com.siripuram.urlshortner.repository.UrlShortnerRepository;
import com.siripuram.urlshortner.service.BaseConversion;
import com.siripuram.urlshortner.service.UrlShortnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
 class UrlShortnerServiceImplTest {

    @Mock
    UrlShortnerRepository mockUrlShortnerRepository;
    BaseConversionImpl mockBaseConversionImpl;
    UrlShortnerServiceImpl mockUrlShortnerServiceImpl;

    @BeforeEach
    public void setUp() throws Exception {
        mockBaseConversionImpl = new BaseConversionImpl();
        mockUrlShortnerServiceImpl = new UrlShortnerServiceImpl(mockUrlShortnerRepository,mockBaseConversionImpl);
    }

    @Test
    void convertToShortUrlTest() {
        var url = new UrlEntity();
        url.setInputUrl("https://www.apple.com/iphone");
        url.setCreatedDate(new Date());
        url.setId(22);


        when(mockUrlShortnerRepository.save(any(UrlEntity.class))).thenReturn(url);
        when(mockBaseConversionImpl.encode(url.getId())).thenReturn("f");

        var urlRequest = new UrlShortnerDTO();
        urlRequest.setInputURL("https://www.apple.com/iphone");

        assertEquals("w", mockUrlShortnerServiceImpl.convertToShortUrl(urlRequest));
    }

    @Test
     void getOriginalUrlTest() {
        when(mockBaseConversionImpl.decode("H")).thenReturn((long) 33);

        var url = new UrlEntity();
        url.setInputUrl("https://www.apple.com/iphone");
        url.setCreatedDate(new Date());
        url.setId(33);

        when(mockUrlShortnerRepository.findById((long) 33)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://www.apple.com/iphone", mockUrlShortnerServiceImpl.getOriginalUrl("H"));

    }
}
