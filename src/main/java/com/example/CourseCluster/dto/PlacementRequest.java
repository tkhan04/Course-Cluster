package com.example.CourseCluster.dto;

public class PlacementRequest {
    private Long roomId;
    private Long objectId;
    private Double x;
    private Double y;
    private Double rotation;
    
    // Constructors
    public PlacementRequest() {}
    
    public PlacementRequest(Long roomId, Long objectId, Double x, Double y, Double rotation) {
        this.roomId = roomId;
        this.objectId = objectId;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }
    
    // Getters and Setters
    public Long getRoomId() {
        return roomId;
    }
    
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    
    public Long getObjectId() {
        return objectId;
    }
    
    public void setObjectId(Long objectId) {
        this.objectId = objectId;
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
