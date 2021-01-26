package com.kata.demo.core.infrastructure.referential.repository;

import com.kata.demo.core.infrastructure.referential.model.MSRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<MSRoom, String> {
}
