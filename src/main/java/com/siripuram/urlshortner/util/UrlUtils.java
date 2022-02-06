package com.siripuram.urlshortner.util;

import java.net.URLDecoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtils {

    public String computeCheckSum(String url) {

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
            return "Issue while decoding" + e.getMessage();

        }
    }

    String generateOTP(int len)
    {
        // All possible characters of my OTP
        String str = "abcdefghijklmnopqrstuvwxyzABCD"
                +"EFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int n = str.length();

        // String to hold my OTP
        String OTP="";

        for (int i = 1; i <= len; i++)
            OTP += (str.charAt((int) ((Math.random()*10) % n)));

        return(OTP);
    }


}
