package com.siripuram.urlshortner.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "UrlEntity")
public class UrlEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(nullable = false)
        private String inputUrl;

        @Column(nullable = false)
        private Date createdDate;

        private Date expiryDate;


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getInputUrl() {
            return inputUrl;
        }

        public void setInputUrl(String inputUrl) {
            this.inputUrl = inputUrl;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(Date expireDate) {
            this.expiryDate = expireDate;
        }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "id=" + id +
                ", inputUrl='" + inputUrl + '\'' +
                ", createdDate=" + createdDate +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
