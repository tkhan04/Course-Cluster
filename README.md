# 🏠 Dorm Room Layout Designer

A Spring Boot + Java web application that integrates **Principles of Database Systems** and **Fundamentals of Scenic Design** courses. Students can design and visualize their dorm room layouts using a simple 2D grid interface.

## 📚 Project Overview

This educational project demonstrates:
- **Database Systems**: Relational database design, JPA/Hibernate ORM, CRUD operations
- **Scenic Design**: Spatial planning, object placement, 2D visualization
- **Full-Stack Development**: REST APIs, responsive web interface, real-time updates

## ✨ Features

- ✅ Create custom room dimensions
- ✅ Pre-loaded furniture database (beds, desks, chairs, etc.)
- ✅ Drag-and-place furniture on 2D canvas
- ✅ Visual grid system (1 foot per grid square)
- ✅ Persistent storage of room layouts
- ✅ Remove/modify placed objects
- ✅ Real-time visualization with color-coded furniture

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3.5.6
- **Database**: H2 (in-memory) / MySQL (production)
- **ORM**: Spring Data JPA / Hibernate
- **API**: RESTful endpoints with Spring Web
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Build Tool**: Gradle

## 📊 Database Schema

### Tables

#### `rooms`
| Column   | Type   | Description           |
|----------|--------|-----------------------|
| room_id  | BIGINT | Primary Key (auto)    |
| name     | VARCHAR| Room name             |
| length   | DOUBLE | Room length (feet)    |
| width    | DOUBLE | Room width (feet)     |

#### `objects`
| Column    | Type    | Description              |
|-----------|---------|--------------------------|
| object_id | BIGINT  | Primary Key (auto)       |
| name      | VARCHAR | Furniture name           |
| width     | DOUBLE  | Object width (feet)      |
| height    | DOUBLE  | Object height (feet)     |
| color     | VARCHAR | Hex color for display    |

#### `placements`
| Column       | Type   | Description                |
|--------------|--------|----------------------------|
| placement_id | BIGINT | Primary Key (auto)         |
| room_id      | BIGINT | Foreign Key → rooms        |
| object_id    | BIGINT | Foreign Key → objects      |
| x            | DOUBLE | X-coordinate (feet)        |
| y            | DOUBLE | Y-coordinate (feet)        |
| rotation     | DOUBLE | Rotation angle (optional)  |

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Gradle (included via wrapper)
- Any modern web browser

### Installation & Running

1. **Clone the repository** (or navigate to project directory):
   ```bash
   cd /Applications/CourseCluster/CourseCluster
   ```

2. **Build the project**:
   ```bash
   ./gradlew build
   ```

3. **Run the application**:
   ```bash
   ./gradlew bootRun
   ```

4. **Access the application**:
   - **Web Interface**: http://localhost:8080
   - **H2 Console**: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:roomlayout`
     - Username: `sa`
     - Password: (leave empty)

## 📡 REST API Endpoints

### Rooms
- `GET /api/rooms` - Get all rooms
- `GET /api/rooms/{id}` - Get room by ID
- `POST /api/rooms` - Create new room
- `PUT /api/rooms/{id}` - Update room
- `DELETE /api/rooms/{id}` - Delete room

### Objects (Furniture)
- `GET /api/objects` - Get all furniture objects
- `GET /api/objects/{id}` - Get object by ID
- `POST /api/objects` - Create new object
- `PUT /api/objects/{id}` - Update object
- `DELETE /api/objects/{id}` - Delete object

### Placements
- `GET /api/placements` - Get all placements
- `GET /api/placements/room/{roomId}` - Get placements for a room
- `POST /api/placements` - Create new placement
- `PUT /api/placements/{id}` - Update placement
- `DELETE /api/placements/{id}` - Delete placement

## 🎨 Using the Application

### Step 1: Create a Room
1. Enter room name (e.g., "My Dorm Room")
2. Set dimensions (length and width in feet)
3. Click "Create Room"

### Step 2: Place Furniture
1. Select furniture from the left panel
2. Click on the canvas where you want to place it
3. The object will appear as a colored rectangle

### Step 3: Manage Layout
- Click on placed furniture to remove it
- All changes are saved automatically to the database
- Load existing rooms using "Load Existing Rooms" button

## 🔄 Switching to MySQL

To use MySQL instead of H2:

1. **Install MySQL** and create a database:
   ```sql
   CREATE DATABASE roomlayout;
   ```

2. **Update `application.properties`**:
   - Comment out H2 configuration
   - Uncomment MySQL configuration
   - Update username/password

3. **Restart the application**

## 📦 Pre-loaded Furniture

The application comes with 10 common dorm furniture items:
- Twin Bed (3' × 6.5')
- Desk (4' × 2')
- Chair (1.5' × 1.5')
- Dresser (3' × 1.5')
- Bookshelf (2.5' × 1')
- Nightstand (1.5' × 1.5')
- Mini Fridge (2' × 2')
- Wardrobe (3' × 2')
- Bean Bag Chair (2.5' × 2.5')
- Small Table (2' × 2')

## 🧪 Testing the API

### Using cURL

**Create a room:**
```bash
curl -X POST http://localhost:8080/api/rooms \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Room","length":15,"width":12}'
```

**Get all objects:**
```bash
curl http://localhost:8080/api/objects
```

**Create a placement:**
```bash
curl -X POST http://localhost:8080/api/placements \
  -H "Content-Type: application/json" \
  -d '{"roomId":1,"objectId":1,"x":2.0,"y":3.0,"rotation":0}'
```

## 📁 Project Structure

```
src/main/java/com/example/CourseCluster/
├── CourseClusterApplication.java    # Main application
├── config/
│   └── DataInitializer.java         # Sample data loader
├── controller/
│   ├── RoomController.java          # Room REST endpoints
│   ├── RoomObjectController.java    # Object REST endpoints
│   └── PlacementController.java     # Placement REST endpoints
├── dto/
│   ├── PlacementRequest.java        # Request DTO
│   └── PlacementResponse.java       # Response DTO
├── entity/
│   ├── Room.java                    # Room entity
│   ├── RoomObject.java              # Furniture entity
│   └── Placement.java               # Placement entity
├── repository/
│   ├── RoomRepository.java          # Room data access
│   ├── RoomObjectRepository.java    # Object data access
│   └── PlacementRepository.java     # Placement data access
└── service/
    ├── RoomService.java             # Room business logic
    ├── RoomObjectService.java       # Object business logic
    └── PlacementService.java        # Placement business logic

src/main/resources/
├── application.properties           # Configuration
└── static/
    ├── index.html                   # Frontend UI
    ├── styles.css                   # Styling
    └── app.js                       # JavaScript logic
```

## 🎓 Learning Outcomes

### Database Systems Concepts
- ✅ Relational database design (1:N relationships)
- ✅ Primary and foreign keys
- ✅ JPA entity mapping and annotations
- ✅ CRUD operations with Spring Data JPA
- ✅ Query methods and custom queries

### Scenic Design Concepts
- ✅ Spatial planning and layout
- ✅ Scale and proportion (1 foot = 40 pixels)
- ✅ Object placement and visualization
- ✅ 2D representation of 3D spaces

### Software Engineering
- ✅ RESTful API design
- ✅ MVC architecture pattern
- ✅ DTO pattern for data transfer
- ✅ Service layer separation
- ✅ Frontend-backend integration

## 🔧 Troubleshooting

### Port 8080 already in use
```bash
# Kill process on port 8080 (Mac/Linux)
lsof -ti:8080 | xargs kill -9
```

### Database not persisting
- Check that H2 console shows tables
- Verify `spring.jpa.hibernate.ddl-auto=create-drop` in properties
- For persistence, change to `update` instead

### CORS errors
- Controllers have `@CrossOrigin(origins = "*")`
- For production, specify allowed origins

## 🚀 Future Enhancements

- [ ] Drag-and-drop furniture placement
- [ ] Rotation of objects
- [ ] Undo/redo functionality
- [ ] Export layout as image/PDF
- [ ] User authentication
- [ ] Multiple floor support
- [ ] 3D visualization
- [ ] Collision detection
- [ ] Furniture templates/presets

## 📝 License

This is an educational project for course integration purposes.

## 👥 Contributing

This is a student learning project. Feel free to fork and extend for your own educational purposes!

## 📞 Support

For issues or questions:
1. Check the H2 console for database state
2. Review browser console for JavaScript errors
3. Check Spring Boot logs for backend errors
4. If you really get stuck, just email me @ khanta01@gettysburg.edu

---

**Built with ❤️ for my Database Systems × Scenic Design class** 
