package com.siripuram.urlshortner.util;

import java.net.URLDecoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtils {

    public String computeCheckSum(String url){

        //LOG.info("url:{}",url);
        String decodedUrl = decode(url);

        String md5Hex = DigestUtils.md5Hex(decodedUrl).toUpperCase();

        return md5Hex;

    }

    public String decode(String url) {
        // using try and catch blocks for catching an errors
        try {
            String decodedUrlString = URLDecoder.decode(url, "UTF-8");
            return decodedUrlString;
        } catch (Exception e) {
            // TODO: handle exception
            return "Issue while decoding"+e.getMessage();

        }


    }



}
