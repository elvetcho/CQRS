package com.kata.demo.core.infrastructure.referential.repository;

import com.kata.demo.core.infrastructure.referential.model.MSHotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<MSHotel, String> {
}
