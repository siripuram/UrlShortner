package com.siripuram.urlshortner.dto;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UrlShortnerDTOTest {

    @Mock
    UrlShortnerDTO mockUrlShortnerDTO;

    @BeforeEach
    public void setUp() throws Exception {
        mockUrlShortnerDTO = new UrlShortnerDTO();
    }

    @Test
    void testSetInputURL(){
        mockUrlShortnerDTO.setInputURL("https://www.apple.com/iphone");
        assertTrue(mockUrlShortnerDTO.getInputURL().equals("https://www.apple.com/iphone"));

    }


}
