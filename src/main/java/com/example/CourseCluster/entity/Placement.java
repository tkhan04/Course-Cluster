package com.example.CourseCluster.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "placements")
public class Placement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placementId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @JsonIgnore
    private Room room;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "object_id", nullable = false)
    private RoomObject roomObject;
    
    @Column(nullable = false)
    private Double x; // x-coordinate in feet
    
    @Column(nullable = false)
    private Double y; // y-coordinate in feet
    
    @Column
    private Double rotation; // rotation in degrees (0-360), optional for future enhancement
    
    // Constructors
    public Placement() {}
    
    public Placement(Room room, RoomObject roomObject, Double x, Double y) {
        this.room = room;
        this.roomObject = roomObject;
        this.x = x;
        this.y = y;
        this.rotation = 0.0;
    }
    
    // Getters and Setters
    public Long getPlacementId() {
        return placementId;
    }
    
    public void setPlacementId(Long placementId) {
        this.placementId = placementId;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public RoomObject getRoomObject() {
        return roomObject;
    }
    
    public void setRoomObject(RoomObject roomObject) {
        this.roomObject = roomObject;
    }
    
    public Double getX() {
        return x;
    }
    
    public void setX(Double x) {
        this.x = x;
    }
    
    public Double getY() {
        return y;
    }
    
    public void setY(Double y) {
        this.y = y;
    }
    
    public Double getRotation() {
        return rotation;
    }
    
    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }
}
