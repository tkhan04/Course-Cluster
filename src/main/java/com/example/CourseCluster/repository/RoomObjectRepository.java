package com.example.CourseCluster.repository;

import com.example.CourseCluster.entity.RoomObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomObjectRepository extends JpaRepository<RoomObject, Long> {
}
