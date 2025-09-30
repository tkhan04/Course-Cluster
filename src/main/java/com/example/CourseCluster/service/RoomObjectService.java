package com.example.CourseCluster.service;

import com.example.CourseCluster.entity.RoomObject;
import com.example.CourseCluster.repository.RoomObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomObjectService {
    
    @Autowired
    private RoomObjectRepository roomObjectRepository;
    
    public List<RoomObject> getAllObjects() {
        return roomObjectRepository.findAll();
    }
    
    public Optional<RoomObject> getObjectById(Long id) {
        return roomObjectRepository.findById(id);
    }
    
    public RoomObject createObject(RoomObject roomObject) {
        return roomObjectRepository.save(roomObject);
    }
    
    public RoomObject updateObject(Long id, RoomObject objectDetails) {
        RoomObject roomObject = roomObjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Object not found with id: " + id));
        
        roomObject.setName(objectDetails.getName());
        roomObject.setWidth(objectDetails.getWidth());
        roomObject.setHeight(objectDetails.getHeight());
        roomObject.setColor(objectDetails.getColor());
        
        return roomObjectRepository.save(roomObject);
    }
    
    public void deleteObject(Long id) {
        roomObjectRepository.deleteById(id);
    }
}
