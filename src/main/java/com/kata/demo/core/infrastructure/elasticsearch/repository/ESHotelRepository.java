package com.kata.demo.core.infrastructure.elasticsearch.repository;

import com.kata.demo.core.infrastructure.elasticsearch.model.ESHotel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESHotelRepository extends ElasticsearchRepository<ESHotel, String>, ESHotelRepositoryCustom {
}
