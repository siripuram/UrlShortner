package com.siripuram.urlshortner.repository;

import com.siripuram.urlshortner.entity.UrlCheckSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlCheckSumRepository extends JpaRepository<UrlCheckSum, String>{

}
