package com.siripuram.urlshortner.repository;

import com.siripuram.urlshortner.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortnerRepository extends JpaRepository<UrlEntity, Long> {

}
