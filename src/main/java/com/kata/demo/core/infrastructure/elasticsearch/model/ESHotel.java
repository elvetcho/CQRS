package com.kata.demo.core.infrastructure.elasticsearch.model;

import com.kata.demo.core.domain.model.Hotel;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import javax.persistence.Id;

@Document(indexName = "hotel")
public class ESHotel {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    private GeoPoint location;

    public ESHotel() {
    }

    public ESHotel(String id, String name, GeoPoint location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Hotel toModel(){
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
