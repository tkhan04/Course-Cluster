-- Database Schema for Dorm Room Layout Designer
-- This file is for reference - JPA will auto-generate tables

-- Rooms Table
CREATE TABLE rooms (
    room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    length DOUBLE NOT NULL,
    width DOUBLE NOT NULL
);

-- Objects (Furniture) Table
CREATE TABLE objects (
    object_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    width DOUBLE NOT NULL,
    height DOUBLE NOT NULL,
    color VARCHAR(7)  -- Hex color code
);

-- Placements Table
CREATE TABLE placements (
    placement_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_id BIGINT NOT NULL,
    object_id BIGINT NOT NULL,
    x DOUBLE NOT NULL,
    y DOUBLE NOT NULL,
    rotation DOUBLE DEFAULT 0,
    FOREIGN KEY (room_id) REFERENCES rooms(room_id) ON DELETE CASCADE,
    FOREIGN KEY (object_id) REFERENCES objects(object_id) ON DELETE CASCADE
);

-- Sample Data Inserts

-- Insert sample furniture objects
INSERT INTO objects (name, width, height, color) VALUES
('Twin Bed', 3.0, 6.5, '#8B4513'),
('Desk', 4.0, 2.0, '#D2691E'),
('Chair', 1.5, 1.5, '#A0522D'),
('Dresser', 3.0, 1.5, '#CD853F'),
('Bookshelf', 2.5, 1.0, '#DEB887'),
('Nightstand', 1.5, 1.5, '#F4A460'),
('Mini Fridge', 2.0, 2.0, '#C0C0C0'),
('Wardrobe', 3.0, 2.0, '#8B7355'),
('Bean Bag Chair', 2.5, 2.5, '#FF6347'),
('Small Table', 2.0, 2.0, '#DAA520');

-- Insert a sample room
INSERT INTO rooms (name, length, width) VALUES
('Sample Dorm Room', 15.0, 12.0);

-- Insert sample placements (assuming room_id=1)
INSERT INTO placements (room_id, object_id, x, y, rotation) VALUES
(1, 1, 1.0, 1.0, 0),    -- Twin Bed at (1, 1)
(1, 2, 8.0, 1.0, 0),    -- Desk at (8, 1)
(1, 3, 8.0, 3.5, 0),    -- Chair at (8, 3.5)
(1, 4, 1.0, 8.0, 0);    -- Dresser at (1, 8)

-- Useful Queries

-- Get all placements for a specific room with object details
SELECT 
    p.placement_id,
    r.name as room_name,
    o.name as object_name,
    o.width,
    o.height,
    o.color,
    p.x,
    p.y,
    p.rotation
FROM placements p
JOIN rooms r ON p.room_id = r.room_id
JOIN objects o ON p.object_id = o.object_id
WHERE r.room_id = 1;

-- Count objects in each room
SELECT 
    r.room_id,
    r.name,
    COUNT(p.placement_id) as object_count
FROM rooms r
LEFT JOIN placements p ON r.room_id = p.room_id
GROUP BY r.room_id, r.name;

-- Find most used furniture items
SELECT 
    o.name,
    COUNT(p.placement_id) as usage_count
FROM objects o
LEFT JOIN placements p ON o.object_id = p.object_id
GROUP BY o.object_id, o.name
ORDER BY usage_count DESC;
