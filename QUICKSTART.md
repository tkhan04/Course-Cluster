# 🚀 Quick Start Guide

## Start the Application

1. **Navigate to project directory:**
   ```bash
   cd /Applications/CourseCluster/CourseCluster
   ```

2. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

3. **Open your browser:**
   - Main Application: http://localhost:8080
   - H2 Database Console: http://localhost:8080/h2-console

## Using the Web Interface

### Step 1: Create Your Room
1. Enter a room name (e.g., "My Dorm Room")
2. Set dimensions:
   - Length: 15 feet (typical dorm room)
   - Width: 12 feet
3. Click **"Create Room"**

### Step 2: Place Furniture
1. Click on any furniture item in the left panel (it will highlight)
2. Click anywhere on the canvas to place it
3. The furniture appears as a colored rectangle with its name

### Step 3: Manage Your Layout
- **Remove furniture**: Click on any placed item and confirm deletion
- **Load saved rooms**: Click "Load Existing Rooms" button
- **Create new rooms**: Adjust dimensions and create another room

## Testing the API with cURL

### Get all furniture objects:
```bash
curl http://localhost:8080/api/objects
```

### Create a room:
```bash
curl -X POST http://localhost:8080/api/rooms \
  -H "Content-Type: application/json" \
  -d '{"name":"My Room","length":15,"width":12}'
```

### Get all rooms:
```bash
curl http://localhost:8080/api/rooms
```

### Place furniture (use room_id=1, object_id=1 for Twin Bed):
```bash
curl -X POST http://localhost:8080/api/placements \
  -H "Content-Type: application/json" \
  -d '{"roomId":1,"objectId":1,"x":2.0,"y":3.0,"rotation":0}'
```

### Get placements for a room:
```bash
curl http://localhost:8080/api/placements/room/1
```

## H2 Database Console

1. Go to: http://localhost:8080/h2-console
2. Use these settings:
   - **JDBC URL**: `jdbc:h2:mem:roomlayout`
   - **Username**: `sa`
   - **Password**: (leave empty)
3. Click **Connect**

### Useful SQL Queries:

**View all furniture:**
```sql
SELECT * FROM objects;
```

**View all rooms:**
```sql
SELECT * FROM rooms;
```

**View placements with details:**
```sql
SELECT 
    p.placement_id,
    r.name as room_name,
    o.name as object_name,
    p.x, p.y
FROM placements p
JOIN rooms r ON p.room_id = r.room_id
JOIN objects o ON p.object_id = o.object_id;
```

## Available Furniture (Pre-loaded)

| ID | Name | Dimensions | Color |
|----|------|------------|-------|
| 1 | Twin Bed | 3' × 6.5' | Brown |
| 2 | Desk | 4' × 2' | Saddle Brown |
| 3 | Chair | 1.5' × 1.5' | Sienna |
| 4 | Dresser | 3' × 1.5' | Peru |
| 5 | Bookshelf | 2.5' × 1' | Burlywood |
| 6 | Nightstand | 1.5' × 1.5' | Sandy Brown |
| 7 | Mini Fridge | 2' × 2' | Silver |
| 8 | Wardrobe | 3' × 2' | Dark Brown |
| 9 | Bean Bag Chair | 2.5' × 2.5' | Tomato |
| 10 | Small Table | 2' × 2' | Goldenrod |

## Stopping the Application

Press `Ctrl+C` in the terminal where the application is running.

## Troubleshooting

### Port 8080 already in use:
```bash
# Find and kill the process
lsof -ti:8080 | xargs kill -9
```

### Application won't start:
```bash
# Clean and rebuild
./gradlew clean build
./gradlew bootRun
```

### Frontend not loading:
- Make sure you're accessing http://localhost:8080 (not https)
- Check browser console for errors (F12)
- Verify the application started successfully in terminal

### Database issues:
- The H2 database is in-memory, so data is lost when you restart
- For persistent data, switch to MySQL (see README.md)

## Next Steps

1. ✅ Create multiple rooms with different dimensions
2. ✅ Experiment with different furniture arrangements
3. ✅ Try the REST API endpoints with cURL or Postman
4. ✅ Explore the H2 console to see database structure
5. ✅ Modify furniture colors in the database
6. ✅ Add custom furniture items via API

## Tips for Success

- **Scale**: 1 grid square = 1 foot
- **Typical dorm dimensions**: 12-15 feet × 10-12 feet
- **Plan before placing**: Think about traffic flow and functionality
- **Use the grid**: Align furniture to grid lines for neat layouts
- **Save often**: Placements are auto-saved to database

---

**Happy Room Designing! 🏠✨**
