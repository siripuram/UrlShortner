package com.siripuram.urlshortner.dto;

import java.util.Date;

public class UrlShortnerDTO {

    private String inputURL;
    private Date urlExpiryDate;

    public String getInputURL() {
        return inputURL;
    }

    public void setInputURL(String inputURL) {
        this.inputURL = inputURL;
    }

    public Date getUrlExpiryDate() {
        return urlExpiryDate;
    }

    public void setUrlExpiryDate(Date urlExpiryDate) {
        this.urlExpiryDate = urlExpiryDate;
    }

}
