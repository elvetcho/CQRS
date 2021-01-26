package com.kata.demo.core.infrastructure.elasticsearch.repository;

import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public interface ESHotelRepositoryCustom {
    List<String> searchWithin(Query query);
}
