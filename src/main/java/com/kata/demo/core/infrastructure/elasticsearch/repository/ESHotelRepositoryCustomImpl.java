package com.kata.demo.core.infrastructure.elasticsearch.repository;

import com.kata.demo.core.infrastructure.elasticsearch.model.ESHotel;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ESHotelRepositoryCustomImpl implements ESHotelRepositoryCustom {
    private final ElasticsearchOperations operations;

    public ESHotelRepositoryCustomImpl(ElasticsearchOperations operations) {
        this.operations = operations;
    }

    @Override
    public List<String> searchWithin(Query query) {
        return operations.search(query, ESHotel.class).get()
                .map(SearchHit::getContent)
                .map(ESHotel::getId)
                .collect(Collectors.toList());
    }
}
