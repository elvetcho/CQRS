package com.kata.demo.core.infrastructure.elasticsearch.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QueryGeneratorTest {

    @Test
    public void shouldReturnHotelSearchQuery_GivenGeoPointDistanceAndUnit(){
        // Given
        GeoPoint geoPoint = new GeoPoint(48.8534, 2.3488);
        Double distance = 10D;
        String unit = "km";

        // When
        Query query = QueryGenerator.generateHotelSearchQuery(geoPoint, distance, unit);

        // Then
        assertThat(query, instanceOf(CriteriaQuery.class));
        CriteriaQuery criteriaQuery = (CriteriaQuery) query;
        assert(criteriaQuery.getCriteria().getField().getName()).equalsIgnoreCase("location");
        assertEquals(criteriaQuery.getCriteria().getFilterCriteriaEntries().size(), 1);
        assertNotNull(criteriaQuery.getSort().getOrderFor("location"));
    }
}
