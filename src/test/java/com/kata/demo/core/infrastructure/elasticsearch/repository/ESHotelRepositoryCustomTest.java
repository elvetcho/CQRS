package com.kata.demo.core.infrastructure.elasticsearch.repository;

import com.kata.demo.core.infrastructure.elasticsearch.model.ESHotel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ESHotelRepositoryCustomTest {

    @Mock
    private ElasticsearchOperations operations;

    @InjectMocks
    private ESHotelRepositoryCustomImpl esHotelRepositoryCustom;

    @Test
    public void shouldReturnHotelsWithinSearchRadius(){
        //Given
        GeoPoint geoPoint = new GeoPoint(48.8534, 2.3488);
        Double distance = 10D;
        String unit = "km";
        Query query = new CriteriaQuery(
                new Criteria("location").within(geoPoint, distance.toString() + unit)
        );
        Sort sort = Sort.by(new GeoDistanceOrder("location", geoPoint).withUnit(unit));
        query.addSort(sort);
        ESHotel hotel = mock(ESHotel.class);
        SearchHits<ESHotel> searchHits = mock(SearchHits.class);
        SearchHit<ESHotel> searchHit = mock(SearchHit.class);
        when(operations.search((Query) any(), any(Class.class)))
                .thenReturn(searchHits);
        when(searchHits.get()).thenReturn(Stream.of(searchHit));
        when(searchHit.getContent()).thenReturn(hotel);
        when(hotel.getId()).thenReturn("1");

        // When
        List<String> hotelIds = esHotelRepositoryCustom.searchWithin(query);

        // Then
        assertEquals(hotelIds.size(), 1);
        assertEquals(hotelIds.get(0), "1");
        verify(operations,times(1)).search((Query) any(), any(Class.class));
    }
}
