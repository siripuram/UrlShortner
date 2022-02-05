package com.siripuram.urlshortner.service;

public interface BaseConversion {

    String encode(long input);
    long decode(String input);
}
