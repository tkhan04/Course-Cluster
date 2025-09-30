package com.example.CourseCluster.controller;

import com.example.CourseCluster.dto.PlacementRequest;
import com.example.CourseCluster.dto.PlacementResponse;
import com.example.CourseCluster.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placements")
@CrossOrigin(origins = "*")
public class PlacementController {
    
    @Autowired
    private PlacementService placementService;
    
    @GetMapping
    public ResponseEntity<List<PlacementResponse>> getAllPlacements() {
        return ResponseEntity.ok(placementService.getAllPlacements());
    }
    
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<PlacementResponse>> getPlacementsByRoomId(@PathVariable Long roomId) {
        return ResponseEntity.ok(placementService.getPlacementsByRoomId(roomId));
    }
    
    @PostMapping
    public ResponseEntity<PlacementResponse> createPlacement(@RequestBody PlacementRequest request) {
        try {
            PlacementResponse placement = placementService.createPlacement(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(placement);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PlacementResponse> updatePlacement(@PathVariable Long id, @RequestBody PlacementRequest request) {
        try {
            PlacementResponse placement = placementService.updatePlacement(id, request);
            return ResponseEntity.ok(placement);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlacement(@PathVariable Long id) {
        placementService.deletePlacement(id);
        return ResponseEntity.noContent().build();
    }
}
