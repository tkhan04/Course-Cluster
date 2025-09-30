# 🧪 Testing Guide

## Complete Testing Workflow

This guide walks you through testing all features of the Dorm Room Layout Designer.

---

## 1. Backend API Testing

### Test 1: Get All Furniture Objects
```bash
curl http://localhost:8080/api/objects
```

**Expected Result**: JSON array with 10 furniture items

**Verify**:
- ✅ 10 objects returned
- ✅ Each has: objectId, name, width, height, color
- ✅ Twin Bed is first (objectId: 1)

---

### Test 2: Create a Room
```bash
curl -X POST http://localhost:8080/api/rooms \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Dorm Room",
    "length": 15.0,
    "width": 12.0
  }'
```

**Expected Result**: 
```json
{
  "roomId": 1,
  "name": "Test Dorm Room",
  "length": 15.0,
  "width": 12.0,
  "placements": []
}
```

**Verify**:
- ✅ roomId is assigned (likely 1)
- ✅ All fields match input
- ✅ placements array is empty

---

### Test 3: Get All Rooms
```bash
curl http://localhost:8080/api/rooms
```

**Expected Result**: Array with the room you just created

**Verify**:
- ✅ Room appears in list
- ✅ Data matches what you created

---

### Test 4: Create a Placement
```bash
curl -X POST http://localhost:8080/api/placements \
  -H "Content-Type: application/json" \
  -d '{
    "roomId": 1,
    "objectId": 1,
    "x": 2.0,
    "y": 3.0,
    "rotation": 0
  }'
```

**Expected Result**:
```json
{
  "placementId": 1,
  "roomId": 1,
  "objectId": 1,
  "objectName": "Twin Bed",
  "objectWidth": 3.0,
  "objectHeight": 6.5,
  "objectColor": "#8B4513",
  "x": 2.0,
  "y": 3.0,
  "rotation": 0.0
}
```

**Verify**:
- ✅ placementId assigned
- ✅ Object details embedded (name, dimensions, color)
- ✅ Coordinates match input

---

### Test 5: Get Placements for a Room
```bash
curl http://localhost:8080/api/placements/room/1
```

**Expected Result**: Array with the placement you created

**Verify**:
- ✅ Placement appears
- ✅ All object details included

---

### Test 6: Update a Room
```bash
curl -X PUT http://localhost:8080/api/rooms/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Room Name",
    "length": 16.0,
    "width": 13.0
  }'
```

**Expected Result**: Updated room with new values

**Verify**:
- ✅ Name changed
- ✅ Dimensions updated
- ✅ roomId unchanged

---

### Test 7: Delete a Placement
```bash
curl -X DELETE http://localhost:8080/api/placements/1
```

**Expected Result**: 204 No Content

**Verify**:
```bash
curl http://localhost:8080/api/placements/room/1
```
- ✅ Returns empty array

---

## 2. Database Console Testing

### Access H2 Console
1. Open browser: http://localhost:8080/h2-console
2. Enter credentials:
   - **JDBC URL**: `jdbc:h2:mem:roomlayout`
   - **Username**: `sa`
   - **Password**: (empty)
3. Click **Connect**

### Test Query 1: View All Tables
```sql
SHOW TABLES;
```

**Expected Result**: 3 tables (OBJECTS, PLACEMENTS, ROOMS)

---

### Test Query 2: Count Furniture
```sql
SELECT COUNT(*) FROM objects;
```

**Expected Result**: 10

---

### Test Query 3: View All Furniture
```sql
SELECT * FROM objects ORDER BY object_id;
```

**Verify**:
- ✅ 10 rows
- ✅ IDs from 1-10
- ✅ All have names, dimensions, colors

---

### Test Query 4: View Rooms
```sql
SELECT * FROM rooms;
```

**Verify**:
- ✅ Shows rooms you created via API or UI
- ✅ All fields populated

---

### Test Query 5: Join Query (Placements with Details)
```sql
SELECT 
    p.placement_id,
    r.name as room_name,
    o.name as object_name,
    o.width,
    o.height,
    p.x,
    p.y
FROM placements p
JOIN rooms r ON p.room_id = r.room_id
JOIN objects o ON p.object_id = o.object_id;
```

**Verify**:
- ✅ Shows all placements with readable names
- ✅ Joins work correctly

---

## 3. Frontend UI Testing

### Access the Application
Open browser: http://localhost:8080

---

### Test 1: Page Load
**Steps**: Load the page

**Verify**:
- ✅ Page loads without errors
- ✅ Header shows "🏠 Dorm Room Layout Designer"
- ✅ Left panel shows furniture list
- ✅ Right panel shows canvas
- ✅ 10 furniture items displayed
- ✅ Each item shows name and dimensions

---

### Test 2: Create a Room via UI
**Steps**:
1. Enter room name: "My Test Room"
2. Set length: 15
3. Set width: 12
4. Click "Create Room"

**Verify**:
- ✅ Alert: "Room created successfully!"
- ✅ Canvas header shows room name
- ✅ Room info shows dimensions
- ✅ Grid appears on canvas
- ✅ Canvas shows room boundary

---

### Test 3: Select Furniture
**Steps**:
1. Click on "Twin Bed" in furniture list

**Verify**:
- ✅ Item highlights (blue border/background)
- ✅ "Selected: Twin Bed" appears at bottom
- ✅ Other items not highlighted

---

### Test 4: Place Furniture
**Steps**:
1. With Twin Bed selected, click on canvas (e.g., near top-left)

**Verify**:
- ✅ Brown rectangle appears on canvas
- ✅ "Twin Bed" label visible on rectangle
- ✅ Rectangle size proportional to dimensions (3' × 6.5')
- ✅ Rectangle positioned where you clicked

---

### Test 5: Place Multiple Items
**Steps**:
1. Select "Desk" from list
2. Click on canvas in different location
3. Select "Chair" from list
4. Click on canvas near desk

**Verify**:
- ✅ Each item appears with correct color
- ✅ Each item shows its name
- ✅ Items don't overlap (if placed correctly)
- ✅ All items visible simultaneously

---

### Test 6: Boundary Validation
**Steps**:
1. Select any furniture
2. Click very close to room edge (where it won't fit)

**Verify**:
- ✅ Alert: "Object doesn't fit at this position!"
- ✅ Object NOT placed
- ✅ Canvas unchanged

---

### Test 7: Remove Furniture
**Steps**:
1. Click on a placed furniture item
2. Confirm deletion in popup

**Verify**:
- ✅ Confirmation dialog appears
- ✅ After confirming, item disappears
- ✅ Canvas redraws correctly

---

### Test 8: Load Existing Room
**Steps**:
1. Click "Load Existing Rooms"
2. Enter room ID (e.g., 1) if prompted
3. Or select from list if multiple rooms

**Verify**:
- ✅ Room loads with correct dimensions
- ✅ Previously placed furniture appears
- ✅ Canvas shows all saved placements
- ✅ Room name displays correctly

---

### Test 9: Browser Console Check
**Steps**:
1. Open browser DevTools (F12)
2. Go to Console tab
3. Interact with the application

**Verify**:
- ✅ No JavaScript errors
- ✅ API calls succeed (check Network tab)
- ✅ Responses are valid JSON

---

## 4. Integration Testing

### Test Scenario: Complete Workflow

**Scenario**: Design a typical dorm room layout

**Steps**:
1. Create room: "Student Dorm 101" (15' × 12')
2. Place Twin Bed at (1, 1)
3. Place Nightstand at (4.5, 1)
4. Place Desk at (8, 1)
5. Place Chair at (8, 3.5)
6. Place Dresser at (1, 8)
7. Place Bookshelf at (4.5, 8)
8. Refresh page
9. Load room again

**Verify**:
- ✅ All items placed successfully
- ✅ Layout looks reasonable
- ✅ After refresh, can reload room
- ✅ All placements persist
- ✅ Items in same positions

---

### Test Scenario: Error Handling

**Test 1: Place without selecting**
- Click canvas without selecting furniture
- **Expected**: Alert "Please select a furniture item first!"

**Test 2: Place without room**
- Don't create room, try to place furniture
- **Expected**: Alert "Please create or load a room first!"

**Test 3: Invalid room dimensions**
- Try to create room with 0 or negative dimensions
- **Expected**: Validation prevents creation

---

## 5. Performance Testing

### Test 1: Large Room
**Steps**:
1. Create room: 30' × 30'
2. Place 20+ furniture items

**Verify**:
- ✅ Canvas renders smoothly
- ✅ No lag when placing items
- ✅ All items visible and labeled

---

### Test 2: Multiple Rooms
**Steps**:
1. Create 5 different rooms via API or UI
2. Load each room

**Verify**:
- ✅ Switching between rooms works
- ✅ Each room loads correct placements
- ✅ No data mixing between rooms

---

## 6. Cross-Browser Testing

Test in multiple browsers:
- ✅ Chrome/Edge (Chromium)
- ✅ Firefox
- ✅ Safari (if on Mac)

**Verify for each**:
- Page loads correctly
- Canvas renders properly
- API calls work
- Interactions function

---

## 7. Responsive Design Testing

**Steps**:
1. Resize browser window
2. Test at different widths (1400px, 1024px, 768px)

**Verify**:
- ✅ Layout adjusts appropriately
- ✅ Canvas remains usable
- ✅ Controls accessible
- ✅ No horizontal scrolling

---

## 8. Data Persistence Testing

### Test 1: Application Restart
**Steps**:
1. Create room and place furniture
2. Stop application (Ctrl+C)
3. Restart: `./gradlew bootRun`
4. Try to load room

**Expected**: 
- ❌ Data lost (H2 in-memory database)
- ✅ Furniture catalog reloads

**Note**: This is expected behavior with H2. For persistence, switch to MySQL.

---

### Test 2: Browser Refresh
**Steps**:
1. Create room and place furniture
2. Refresh browser (F5)
3. Load room again

**Verify**:
- ✅ Room still exists in database
- ✅ Placements still exist
- ✅ Can reload successfully

---

## 9. API Error Handling

### Test 1: Invalid Room ID
```bash
curl http://localhost:8080/api/rooms/999
```

**Expected**: 404 Not Found

---

### Test 2: Invalid Placement Data
```bash
curl -X POST http://localhost:8080/api/placements \
  -H "Content-Type: application/json" \
  -d '{
    "roomId": 999,
    "objectId": 1,
    "x": 2.0,
    "y": 3.0
  }'
```

**Expected**: 400 Bad Request

---

### Test 3: Malformed JSON
```bash
curl -X POST http://localhost:8080/api/rooms \
  -H "Content-Type: application/json" \
  -d '{"name": "Test"'
```

**Expected**: 400 Bad Request

---

## 10. Checklist Summary

### Backend ✅
- [ ] Application starts successfully
- [ ] All REST endpoints respond
- [ ] CRUD operations work
- [ ] Database initializes
- [ ] Sample data loads
- [ ] Relationships work (JOINs)

### Frontend ✅
- [ ] Page loads without errors
- [ ] Furniture list displays
- [ ] Room creation works
- [ ] Furniture selection works
- [ ] Placement works
- [ ] Deletion works
- [ ] Canvas renders correctly
- [ ] Grid displays properly

### Integration ✅
- [ ] Frontend calls API successfully
- [ ] Data persists to database
- [ ] Placements load correctly
- [ ] CORS works
- [ ] Error handling works

### Documentation ✅
- [ ] README is clear
- [ ] QUICKSTART is helpful
- [ ] API endpoints documented
- [ ] Database schema explained

---

## 🎉 Success Criteria

Your application is working correctly if:
1. ✅ All backend API tests pass
2. ✅ H2 console shows correct data
3. ✅ UI loads and functions smoothly
4. ✅ Furniture can be placed and removed
5. ✅ Rooms persist and reload correctly
6. ✅ No console errors in browser
7. ✅ Canvas renders furniture properly

---

## 🐛 Common Issues & Solutions

### Issue: Port 8080 in use
**Solution**: 
```bash
lsof -ti:8080 | xargs kill -9
```

### Issue: Furniture not appearing
**Solution**: 
- Check browser console for errors
- Verify API returns data: `curl http://localhost:8080/api/objects`
- Check CORS settings

### Issue: Canvas not clickable
**Solution**:
- Ensure room is created first
- Check that furniture is selected
- Verify JavaScript loaded (check Network tab)

### Issue: Data not persisting
**Solution**:
- H2 is in-memory (expected behavior)
- For persistence, configure MySQL in application.properties

---

**Testing Complete! Your application is ready for demonstration.** ✅🎓
