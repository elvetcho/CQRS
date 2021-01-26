package com.kata.demo.common.util;

import com.kata.demo.core.domain.model.Coordinates;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

public class GeoLocationUtil {
    public static GeoPoint geoPointFromCoordinates(Coordinates location){
        return new GeoPoint(location.getLatitude(), location.getLongitude());
    }
}
