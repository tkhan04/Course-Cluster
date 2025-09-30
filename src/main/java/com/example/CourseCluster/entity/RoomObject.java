package com.example.CourseCluster.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "objects")
public class RoomObject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long objectId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Double width; // in feet
    
    @Column(nullable = false)
    private Double height; // in feet (length in 2D top-down view)
    
    @Column
    private String color; // hex color for visualization
    
    @OneToMany(mappedBy = "roomObject", cascade = CascadeType.ALL)
    private List<Placement> placements = new ArrayList<>();
    
    // Constructors
    public RoomObject() {}
    
    public RoomObject(String name, Double width, Double height, String color) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    // Getters and Setters
    public Long getObjectId() {
        return objectId;
    }
    
    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getWidth() {
        return width;
    }
    
    public void setWidth(Double width) {
        this.width = width;
    }
    
    public Double getHeight() {
        return height;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public List<Placement> getPlacements() {
        return placements;
    }
    
    public void setPlacements(List<Placement> placements) {
        this.placements = placements;
    }
}
