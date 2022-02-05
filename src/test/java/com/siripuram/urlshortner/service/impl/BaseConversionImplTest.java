package com.siripuram.urlshortner.service.impl;

import com.siripuram.urlshortner.service.BaseConversion;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class BaseConversionImplTest {

    private BaseConversionImpl baseConversion = new BaseConversionImpl();

    @Test
    void encodeSingleCharacter() {
        assertEquals("s", baseConversion.encode(18));
    }

    @Test
    void encodeDoubleCharacter() {
        assertEquals("bq", baseConversion.encode(78));
    }

    @Test
    void decodeSingleCharacter() {
        assertEquals(26, baseConversion.decode("A"));
    }

}
