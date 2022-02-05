package com.siripuram.urlshortner.entity;

import javax.persistence.*;

@Entity
//@Table(name = "UrlCheckSum")
public class UrlCheckSum {
    @Id
    private String checkSum;

    @Column(nullable = false)
    private String shortUrl;

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
