package com.kata.demo.core.infrastructure.referential.repository;

import com.kata.demo.core.infrastructure.referential.model.MSBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<MSBooking, String> {
}
