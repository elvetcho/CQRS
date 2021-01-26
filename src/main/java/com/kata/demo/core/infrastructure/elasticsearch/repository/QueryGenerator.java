package com.kata.demo.core.infrastructure.elasticsearch.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder;
import org.springframework.data.elasticsearch.core.query.Query;

public class QueryGenerator {
    public static Query generateHotelSearchQuery(GeoPoint geoPoint, Double distance, String unit){
        Query query = new CriteriaQuery(
                new Criteria("location").within(geoPoint, distance.toString() + unit)
        );
        Sort sort = Sort.by(new GeoDistanceOrder("location", geoPoint).withUnit(unit));
        query.addSort(sort);
        return query;
    }
}
