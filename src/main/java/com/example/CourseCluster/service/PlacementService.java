package com.example.CourseCluster.service;

import com.example.CourseCluster.dto.PlacementRequest;
import com.example.CourseCluster.dto.PlacementResponse;
import com.example.CourseCluster.entity.Placement;
import com.example.CourseCluster.entity.Room;
import com.example.CourseCluster.entity.RoomObject;
import com.example.CourseCluster.repository.PlacementRepository;
import com.example.CourseCluster.repository.RoomRepository;
import com.example.CourseCluster.repository.RoomObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacementService {
    
    @Autowired
    private PlacementRepository placementRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private RoomObjectRepository roomObjectRepository;
    
    public List<PlacementResponse> getAllPlacements() {
        return placementRepository.findAll().stream()
                .map(PlacementResponse::new)
                .collect(Collectors.toList());
    }
    
    public List<PlacementResponse> getPlacementsByRoomId(Long roomId) {
        return placementRepository.findByRoom_RoomId(roomId).stream()
                .map(PlacementResponse::new)
                .collect(Collectors.toList());
    }
    
    public PlacementResponse createPlacement(PlacementRequest request) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + request.getRoomId()));
        
        RoomObject roomObject = roomObjectRepository.findById(request.getObjectId())
                .orElseThrow(() -> new RuntimeException("Object not found with id: " + request.getObjectId()));
        
        Placement placement = new Placement(room, roomObject, request.getX(), request.getY());
        if (request.getRotation() != null) {
            placement.setRotation(request.getRotation());
        }
        
        Placement savedPlacement = placementRepository.save(placement);
        return new PlacementResponse(savedPlacement);
    }
    
    public PlacementResponse updatePlacement(Long id, PlacementRequest request) {
        Placement placement = placementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + id));
        
        placement.setX(request.getX());
        placement.setY(request.getY());
        if (request.getRotation() != null) {
            placement.setRotation(request.getRotation());
        }
        
        Placement updatedPlacement = placementRepository.save(placement);
        return new PlacementResponse(updatedPlacement);
    }
    
    public void deletePlacement(Long id) {
        placementRepository.deleteById(id);
    }
}
