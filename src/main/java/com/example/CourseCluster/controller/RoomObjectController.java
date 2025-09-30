package com.example.CourseCluster.controller;

import com.example.CourseCluster.entity.RoomObject;
import com.example.CourseCluster.service.RoomObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objects")
@CrossOrigin(origins = "*")
public class RoomObjectController {
    
    @Autowired
    private RoomObjectService roomObjectService;
    
    @GetMapping
    public ResponseEntity<List<RoomObject>> getAllObjects() {
        return ResponseEntity.ok(roomObjectService.getAllObjects());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RoomObject> getObjectById(@PathVariable Long id) {
        return roomObjectService.getObjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<RoomObject> createObject(@RequestBody RoomObject roomObject) {
        RoomObject createdObject = roomObjectService.createObject(roomObject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObject);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RoomObject> updateObject(@PathVariable Long id, @RequestBody RoomObject roomObject) {
        try {
            RoomObject updatedObject = roomObjectService.updateObject(id, roomObject);
            return ResponseEntity.ok(updatedObject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObject(@PathVariable Long id) {
        roomObjectService.deleteObject(id);
        return ResponseEntity.noContent().build();
    }
}
