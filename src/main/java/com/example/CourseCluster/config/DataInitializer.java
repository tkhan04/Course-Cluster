package com.example.CourseCluster.config;

import com.example.CourseCluster.entity.RoomObject;
import com.example.CourseCluster.repository.RoomObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private RoomObjectRepository roomObjectRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize common dorm room furniture with realistic dimensions (in feet)
        if (roomObjectRepository.count() == 0) {
            roomObjectRepository.save(new RoomObject("Twin Bed", 3.0, 6.5, "#8B4513"));
            roomObjectRepository.save(new RoomObject("Desk", 4.0, 2.0, "#D2691E"));
            roomObjectRepository.save(new RoomObject("Chair", 1.5, 1.5, "#A0522D"));
            roomObjectRepository.save(new RoomObject("Dresser", 3.0, 1.5, "#CD853F"));
            roomObjectRepository.save(new RoomObject("Bookshelf", 2.5, 1.0, "#DEB887"));
            roomObjectRepository.save(new RoomObject("Nightstand", 1.5, 1.5, "#F4A460"));
            roomObjectRepository.save(new RoomObject("Mini Fridge", 2.0, 2.0, "#C0C0C0"));
            roomObjectRepository.save(new RoomObject("Wardrobe", 3.0, 2.0, "#8B7355"));
            roomObjectRepository.save(new RoomObject("Bean Bag Chair", 2.5, 2.5, "#FF6347"));
            roomObjectRepository.save(new RoomObject("Small Table", 2.0, 2.0, "#DAA520"));
            
            System.out.println("Sample furniture objects initialized!");
        }
    }
}
