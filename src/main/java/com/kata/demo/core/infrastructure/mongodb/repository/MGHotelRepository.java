package com.kata.demo.core.infrastructure.mongodb.repository;

import com.kata.demo.core.infrastructure.mongodb.model.MGHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MGHotelRepository extends
        MongoRepository<MGHotel, String>, JpaRepository<MGHotel, String> {

}
