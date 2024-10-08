package com.example.travelink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.ReservedRoom;

@Repository
public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Integer> {
}
