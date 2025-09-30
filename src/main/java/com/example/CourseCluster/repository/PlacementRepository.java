package com.example.CourseCluster.repository;

import com.example.CourseCluster.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Long> {
    List<Placement> findByRoom_RoomId(Long roomId);
}
