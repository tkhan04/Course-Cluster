package com.example.CourseCluster.dto;

import com.example.CourseCluster.entity.Placement;

public class PlacementResponse {
    private Long placementId;
    private Long roomId;
    private Long objectId;
    private String objectName;
    private Double objectWidth;
    private Double objectHeight;
    private String objectColor;
    private Double x;
    private Double y;
    private Double rotation;
    
    // Constructor from Placement entity
    public PlacementResponse(Placement placement) {
        this.placementId = placement.getPlacementId();
        this.roomId = placement.getRoom().getRoomId();
        this.objectId = placement.getRoomObject().getObjectId();
        this.objectName = placement.getRoomObject().getName();
        this.objectWidth = placement.getRoomObject().getWidth();
        this.objectHeight = placement.getRoomObject().getHeight();
        this.objectColor = placement.getRoomObject().getColor();
        this.x = placement.getX();
        this.y = placement.getY();
        this.rotation = placement.getRotation();
    }
    
    // Getters and Setters
    public Long getPlacementId() {
        return placementId;
    }
    
    public void setPlacementId(Long placementId) {
        this.placementId = placementId;
    }
    
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
    
    public String getObjectName() {
        return objectName;
    }
    
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    
    public Double getObjectWidth() {
        return objectWidth;
    }
    
    public void setObjectWidth(Double objectWidth) {
        this.objectWidth = objectWidth;
    }
    
    public Double getObjectHeight() {
        return objectHeight;
    }
    
    public void setObjectHeight(Double objectHeight) {
        this.objectHeight = objectHeight;
    }
    
    public String getObjectColor() {
        return objectColor;
    }
    
    public void setObjectColor(String objectColor) {
        this.objectColor = objectColor;
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
