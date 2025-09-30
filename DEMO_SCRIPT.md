# 🎬 Demo Script: Dorm Room Layout Designer

## Presentation Guide for Course Integration Project

---

## Opening (1 minute)

**"Today I'm presenting a web application that bridges two courses: Principles of Database Systems and Fundamentals of Scenic Design."**

**"The application is called the Dorm Room Layout Designer - a tool that helps students plan their dorm room layouts using database-backed spatial design."**

---

## Part 1: Project Overview (2 minutes)

### The Problem
**"Students moving into dorms face a common challenge: how to arrange furniture in a limited space. This project solves that by combining:**
- **Database Systems**: Persistent storage of rooms, furniture, and layouts
- **Scenic Design**: Spatial planning, scale, and 2D visualization"

### The Solution
**"A full-stack web application with:**
- Spring Boot backend with REST APIs
- MySQL/H2 database with JPA
- Interactive HTML5 canvas frontend
- Real-time layout visualization"

---

## Part 2: Database Design (3 minutes)

### Show H2 Console
**Navigate to**: http://localhost:8080/h2-console

**"Let me show you the database structure..."**

### Execute Queries:

**1. Show Tables**
```sql
SHOW TABLES;
```
**"We have three main tables: ROOMS, OBJECTS, and PLACEMENTS"**

**2. Show Furniture Catalog**
```sql
SELECT * FROM objects;
```
**"The OBJECTS table stores our furniture catalog - 10 common dorm items with dimensions and colors for visualization."**

**3. Show Relationships**
```sql
SELECT 
    r.name as room_name,
    o.name as furniture,
    p.x, p.y
FROM placements p
JOIN rooms r ON p.room_id = r.room_id
JOIN objects o ON p.object_id = o.object_id;
```
**"PLACEMENTS creates the many-to-many relationship between rooms and objects, storing exact coordinates."**

### Key Database Concepts
**"This demonstrates:**
- ✅ Proper normalization (3NF)
- ✅ Foreign key relationships
- ✅ JPA entity mapping
- ✅ One-to-many relationships"

---

## Part 3: Backend Architecture (2 minutes)

### Show Code Structure
**Open**: `src/main/java/com/example/CourseCluster/`

**"The backend follows clean architecture:**

**1. Entity Layer** (show `entity/Room.java`)
```java
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    // ... JPA annotations mapping to database
}
```

**2. Repository Layer** (show `repository/RoomRepository.java`)
```java
public interface RoomRepository extends JpaRepository<Room, Long> {
    // Spring Data JPA provides CRUD automatically
}
```

**3. Service Layer** (show `service/RoomService.java`)
```java
@Service
public class RoomService {
    // Business logic for room management
}
```

**4. Controller Layer** (show `controller/RoomController.java`)
```java
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    // REST endpoints
}
```

---

## Part 4: REST API Demo (3 minutes)

### Terminal Demo

**"Let me demonstrate the REST API..."**

**1. Get Furniture Catalog**
```bash
curl http://localhost:8080/api/objects
```
**"Returns all available furniture in JSON format"**

**2. Create a Room**
```bash
curl -X POST http://localhost:8080/api/rooms \
  -H "Content-Type: application/json" \
  -d '{"name":"Demo Room","length":15,"width":12}'
```
**"Creates a new room and returns it with an assigned ID"**

**3. Place Furniture**
```bash
curl -X POST http://localhost:8080/api/placements \
  -H "Content-Type: application/json" \
  -d '{"roomId":1,"objectId":1,"x":2,"y":3,"rotation":0}'
```
**"Places a Twin Bed at coordinates (2, 3) in the room"**

**4. Get Room Layout**
```bash
curl http://localhost:8080/api/placements/room/1
```
**"Retrieves all furniture placements for the room"**

---

## Part 5: Frontend Demo (5 minutes)

### Open Application
**Navigate to**: http://localhost:8080

**"Now let's see the user interface..."**

### Step 1: Create a Room
**"First, I'll create a typical dorm room:"**
1. Enter name: "Sample Dorm Room"
2. Length: 15 feet
3. Width: 12 feet
4. Click "Create Room"

**"Notice the canvas now shows a grid - each square represents 1 foot. This demonstrates the scenic design principle of scale."**

### Step 2: Place Furniture
**"Let me design a functional layout:"**

1. **Select Twin Bed** → Click near top-left
   - **"The bed is 3 feet by 6.5 feet - typical twin size"**

2. **Select Nightstand** → Place next to bed
   - **"1.5 feet square - perfect bedside table"**

3. **Select Desk** → Place along wall
   - **"4 feet wide - standard desk size"**

4. **Select Chair** → Place at desk
   - **"Notice how I'm considering traffic flow and functionality"**

5. **Select Dresser** → Place on opposite wall
   - **"Creating balance in the space"**

6. **Select Bookshelf** → Place near desk
   - **"Study area grouping - scenic design principle"**

### Step 3: Demonstrate Features

**Boundary Validation**:
- Try to place furniture outside room
- **"The system validates that objects fit within the room boundaries"**

**Removal**:
- Click on a placed item
- **"Click any item to remove it - changes save immediately to the database"**

**Persistence**:
- Refresh the page
- Click "Load Existing Rooms"
- **"All placements are stored in the database and can be reloaded"**

---

## Part 6: Scenic Design Principles (2 minutes)

**"This project demonstrates key scenic design concepts:"**

### 1. Scale and Proportion
**"The 1:40 scale ratio (1 foot = 40 pixels) maintains accurate proportions"**

### 2. Spatial Planning
**"Students learn to consider:**
- Traffic flow (pathways between furniture)
- Functional zones (sleep, study, storage)
- Balance and symmetry"

### 3. Visual Representation
**"Color-coding helps differentiate furniture types at a glance"**

### 4. Top-Down View
**"Standard architectural/theatrical floor plan perspective"**

---

## Part 7: Technical Highlights (2 minutes)

### Full-Stack Integration
**"This project showcases:**
- ✅ **Backend**: Spring Boot, JPA, REST APIs
- ✅ **Database**: Relational design, foreign keys, joins
- ✅ **Frontend**: HTML5 Canvas, async JavaScript, fetch API
- ✅ **Architecture**: MVC pattern, service layer, DTOs

### Code Quality
**"Professional practices:**
- ✅ Proper package structure
- ✅ Separation of concerns
- ✅ RESTful API design
- ✅ Error handling
- ✅ CORS configuration
- ✅ Comprehensive documentation

---

## Part 8: Learning Outcomes (1 minute)

### Database Systems
**"Students learn:**
- Relational database design
- JPA/Hibernate ORM
- CRUD operations
- Query optimization
- Transaction management"

### Scenic Design
**"Students learn:**
- Spatial planning
- Scale and proportion
- Layout composition
- Functional design
- Visual representation"

---

## Part 9: Future Enhancements (1 minute)

**"Potential expansions:**
- 🔄 Drag-and-drop furniture movement
- 🔄 Object rotation
- 🔄 3D visualization
- 🔄 User authentication
- 🔄 Collaborative editing
- 🔄 AI layout suggestions
- 🔄 Export to PDF/PNG
- 🔄 Mobile app version"

---

## Part 10: Live Q&A Preparation

### Common Questions & Answers

**Q: Why H2 instead of MySQL?**
**A:** "H2 is perfect for development and demos - zero configuration. The application supports MySQL for production by simply changing application.properties."

**Q: How do you handle concurrent users?**
**A:** "Spring Boot handles this automatically. For production, we'd add user authentication and room ownership."

**Q: Can furniture overlap?**
**A:** "Currently yes - that's a future enhancement. We could add collision detection using bounding box algorithms."

**Q: How accurate are the dimensions?**
**A:** "Based on standard furniture sizes. Students can add custom furniture with exact dimensions via the API."

**Q: What about 3D visualization?**
**A:** "Great question! That's a planned enhancement using Three.js. The database structure already supports rotation for 3D."

**Q: Is this mobile-friendly?**
**A:** "The UI is responsive and works on tablets. A dedicated mobile app would be a great next step."

---

## Closing (1 minute)

**"In summary, this project successfully integrates:**
- ✅ Database Systems: Full relational database with JPA
- ✅ Scenic Design: Spatial planning and visualization
- ✅ Software Engineering: Professional full-stack architecture

**"The application is:**
- ✅ Fully functional and tested
- ✅ Well-documented with README, guides, and API docs
- ✅ Extensible for future enhancements
- ✅ Achievable in 1-2 weeks for students

**"Thank you! I'm happy to answer any questions or demonstrate specific features."**

---

## Demo Checklist

### Before Presentation
- [ ] Application running (`./gradlew bootRun`)
- [ ] Browser open to http://localhost:8080
- [ ] H2 console tab ready
- [ ] Terminal ready for cURL commands
- [ ] Code editor open to show structure
- [ ] README.md visible for reference

### During Presentation
- [ ] Speak clearly and pace yourself
- [ ] Show, don't just tell
- [ ] Highlight both database and design aspects
- [ ] Demonstrate actual functionality
- [ ] Be ready to handle questions
- [ ] Have backup examples ready

### After Presentation
- [ ] Provide GitHub link or project files
- [ ] Share documentation (README, QUICKSTART)
- [ ] Offer to help with setup
- [ ] Collect feedback for improvements

---

## Time Breakdown (20 minutes total)

| Section | Time | Key Points |
|---------|------|------------|
| Opening | 1 min | Problem & solution |
| Database Design | 3 min | Schema, queries, relationships |
| Backend Architecture | 2 min | Layers, JPA, Spring Boot |
| REST API Demo | 3 min | Live cURL commands |
| Frontend Demo | 5 min | Create room, place furniture |
| Scenic Design | 2 min | Scale, spatial planning |
| Technical Highlights | 2 min | Full-stack integration |
| Learning Outcomes | 1 min | Course objectives |
| Future Enhancements | 1 min | Extensibility |

---

**Good luck with your presentation! 🎓✨**
