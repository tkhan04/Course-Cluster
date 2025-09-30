package com.example.CourseCluster.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Double length; // in feet
    
    @Column(nullable = false)
    private Double width; // in feet
    
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Placement> placements = new ArrayList<>();
    
    // Constructors
    public Room() {}
    
    public Room(String name, Double length, Double width) {
        this.name = name;
        this.length = length;
        this.width = width;
    }
    
    // Getters and Setters
    public Long getRoomId() {
        return roomId;
    }
    
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getLength() {
        return length;
    }
    
    public void setLength(Double length) {
        this.length = length;
    }
    
    public Double getWidth() {
        return width;
    }
    
    public void setWidth(Double width) {
        this.width = width;
    }
    
    public List<Placement> getPlacements() {
        return placements;
    }
    
    public void setPlacements(List<Placement> placements) {
        this.placements = placements;
    }
}
