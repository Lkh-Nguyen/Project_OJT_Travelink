package com.example.travelink.repository;

import com.example.travelink.model.RoomFacility;
import com.example.travelink.model.RoomFacility.RoomFacilityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomFacility, RoomFacilityKey> {
    List<RoomFacility> findByRoom_RoomId(int roomId);
}
