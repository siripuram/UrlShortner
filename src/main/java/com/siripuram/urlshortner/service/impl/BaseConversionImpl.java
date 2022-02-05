package com.siripuram.urlshortner.service.impl;

import com.siripuram.urlshortner.service.BaseConversion;
import org.springframework.stereotype.Service;

@Service
public class BaseConversionImpl implements BaseConversion {

    private static final String ALLOWED_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = ALLOWED_STRING.toCharArray();
    private int base = allowedCharacters.length;

    @Override
    public String encode(long input){
        var encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }

    @Override
    public long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;

        var decoded = 0;

        //counter is used to avoid reversing input string
        double counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += ALLOWED_STRING.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }

}
