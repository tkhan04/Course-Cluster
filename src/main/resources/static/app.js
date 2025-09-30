// API Base URL
const API_BASE = 'http://localhost:8080/api';

// Global state
let currentRoom = null;
let selectedObject = null;
let availableObjects = [];
let placements = [];

// Canvas setup
const canvas = document.getElementById('roomCanvas');
const ctx = canvas.getContext('2d');
const PIXELS_PER_FOOT = 40; // Scale: 40 pixels = 1 foot

// Initialize app
document.addEventListener('DOMContentLoaded', () => {
    loadAvailableObjects();
    setupCanvas();
});

// Load available furniture objects
async function loadAvailableObjects() {
    try {
        const response = await fetch(`${API_BASE}/objects`);
        availableObjects = await response.json();
        displayObjects();
    } catch (error) {
        console.error('Error loading objects:', error);
        document.getElementById('objectsList').innerHTML = 
            '<p class="loading" style="color: red;">Error loading furniture</p>';
    }
}

// Display objects in the sidebar
function displayObjects() {
    const objectsList = document.getElementById('objectsList');
    objectsList.innerHTML = '';
    
    availableObjects.forEach(obj => {
        const div = document.createElement('div');
        div.className = 'object-item';
        div.onclick = () => selectObject(obj);
        
        div.innerHTML = `
            <div class="object-info">
                <div class="object-name">${obj.name}</div>
                <div class="object-dimensions">${obj.width}' × ${obj.height}'</div>
            </div>
            <div class="object-color" style="background-color: ${obj.color}"></div>
        `;
        
        objectsList.appendChild(div);
    });
}

// Select an object
function selectObject(obj) {
    selectedObject = obj;
    document.getElementById('selectedObject').textContent = obj.name;
    
    // Update UI
    document.querySelectorAll('.object-item').forEach(item => {
        item.classList.remove('selected');
    });
    event.currentTarget.classList.add('selected');
}

// Create a new room
async function createRoom() {
    const name = document.getElementById('roomName').value || 'My Room';
    const length = parseFloat(document.getElementById('roomLength').value);
    const width = parseFloat(document.getElementById('roomWidth').value);
    
    if (!length || !width) {
        alert('Please enter valid room dimensions');
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE}/rooms`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, length, width })
        });
        
        currentRoom = await response.json();
        placements = [];
        updateRoomDisplay();
        drawRoom();
        alert('Room created successfully!');
    } catch (error) {
        console.error('Error creating room:', error);
        alert('Error creating room');
    }
}

// Load existing rooms
async function loadRooms() {
    try {
        const response = await fetch(`${API_BASE}/rooms`);
        const rooms = await response.json();
        
        if (rooms.length === 0) {
            alert('No rooms found. Create a new room first!');
            return;
        }
        
        // Simple selection - use first room or prompt user
        let roomId;
        if (rooms.length === 1) {
            roomId = rooms[0].roomId;
        } else {
            const roomList = rooms.map(r => `${r.roomId}: ${r.name} (${r.length}' × ${r.width}')`).join('\n');
            const input = prompt(`Enter room ID to load:\n\n${roomList}`);
            roomId = parseInt(input);
        }
        
        if (roomId) {
            await loadRoom(roomId);
        }
    } catch (error) {
        console.error('Error loading rooms:', error);
        alert('Error loading rooms');
    }
}

// Load a specific room
async function loadRoom(roomId) {
    try {
        const roomResponse = await fetch(`${API_BASE}/rooms/${roomId}`);
        currentRoom = await roomResponse.json();
        
        const placementsResponse = await fetch(`${API_BASE}/placements/room/${roomId}`);
        placements = await placementsResponse.json();
        
        updateRoomDisplay();
        drawRoom();
    } catch (error) {
        console.error('Error loading room:', error);
        alert('Error loading room');
    }
}

// Update room display info
function updateRoomDisplay() {
    if (currentRoom) {
        document.getElementById('currentRoomTitle').textContent = currentRoom.name;
        document.getElementById('roomInfo').textContent = 
            `${currentRoom.length}' × ${currentRoom.width}'`;
    }
}

// Setup canvas click handler
function setupCanvas() {
    canvas.addEventListener('click', handleCanvasClick);
}

// Handle canvas clicks
async function handleCanvasClick(event) {
    if (!currentRoom) {
        alert('Please create or load a room first!');
        return;
    }
    
    const rect = canvas.getBoundingClientRect();
    const clickX = event.clientX - rect.left;
    const clickY = event.clientY - rect.top;
    
    // Check if clicking on existing placement (to delete)
    const clickedPlacement = findPlacementAt(clickX, clickY);
    if (clickedPlacement) {
        if (confirm(`Remove ${clickedPlacement.objectName}?`)) {
            await deletePlacement(clickedPlacement.placementId);
        }
        return;
    }
    
    // Place new object
    if (selectedObject) {
        const x = clickX / PIXELS_PER_FOOT;
        const y = clickY / PIXELS_PER_FOOT;
        
        // Check if placement is within room bounds
        if (x + selectedObject.width > currentRoom.length || 
            y + selectedObject.height > currentRoom.width) {
            alert('Object doesn\'t fit at this position!');
            return;
        }
        
        await createPlacement(x, y);
    } else {
        alert('Please select a furniture item first!');
    }
}

// Find placement at coordinates
function findPlacementAt(pixelX, pixelY) {
    const x = pixelX / PIXELS_PER_FOOT;
    const y = pixelY / PIXELS_PER_FOOT;
    
    return placements.find(p => {
        return x >= p.x && x <= p.x + p.objectWidth &&
               y >= p.y && y <= p.y + p.objectHeight;
    });
}

// Create a placement
async function createPlacement(x, y) {
    try {
        const response = await fetch(`${API_BASE}/placements`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                roomId: currentRoom.roomId,
                objectId: selectedObject.objectId,
                x: x,
                y: y,
                rotation: 0
            })
        });
        
        const newPlacement = await response.json();
        placements.push(newPlacement);
        drawRoom();
    } catch (error) {
        console.error('Error creating placement:', error);
        alert('Error placing object');
    }
}

// Delete a placement
async function deletePlacement(placementId) {
    try {
        await fetch(`${API_BASE}/placements/${placementId}`, {
            method: 'DELETE'
        });
        
        placements = placements.filter(p => p.placementId !== placementId);
        drawRoom();
    } catch (error) {
        console.error('Error deleting placement:', error);
        alert('Error removing object');
    }
}

// Draw the room and all placements
function drawRoom() {
    // Clear canvas
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    
    if (!currentRoom) {
        ctx.fillStyle = '#ccc';
        ctx.font = '20px Arial';
        ctx.textAlign = 'center';
        ctx.fillText('Create or load a room to begin', canvas.width / 2, canvas.height / 2);
        return;
    }
    
    const roomPixelWidth = currentRoom.length * PIXELS_PER_FOOT;
    const roomPixelHeight = currentRoom.width * PIXELS_PER_FOOT;
    
    // Draw grid
    ctx.strokeStyle = '#e0e0e0';
    ctx.lineWidth = 1;
    for (let i = 0; i <= currentRoom.length; i++) {
        const x = i * PIXELS_PER_FOOT;
        ctx.beginPath();
        ctx.moveTo(x, 0);
        ctx.lineTo(x, roomPixelHeight);
        ctx.stroke();
    }
    for (let i = 0; i <= currentRoom.width; i++) {
        const y = i * PIXELS_PER_FOOT;
        ctx.beginPath();
        ctx.moveTo(0, y);
        ctx.lineTo(roomPixelWidth, y);
        ctx.stroke();
    }
    
    // Draw room boundary
    ctx.strokeStyle = '#333';
    ctx.lineWidth = 3;
    ctx.strokeRect(0, 0, roomPixelWidth, roomPixelHeight);
    
    // Draw placements
    placements.forEach(placement => {
        const x = placement.x * PIXELS_PER_FOOT;
        const y = placement.y * PIXELS_PER_FOOT;
        const width = placement.objectWidth * PIXELS_PER_FOOT;
        const height = placement.objectHeight * PIXELS_PER_FOOT;
        
        // Draw object rectangle
        ctx.fillStyle = placement.objectColor;
        ctx.fillRect(x, y, width, height);
        
        // Draw border
        ctx.strokeStyle = '#000';
        ctx.lineWidth = 2;
        ctx.strokeRect(x, y, width, height);
        
        // Draw label
        ctx.fillStyle = '#fff';
        ctx.font = 'bold 12px Arial';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(placement.objectName, x + width / 2, y + height / 2);
    });
}

// Redraw on window resize
window.addEventListener('resize', () => {
    if (currentRoom) {
        drawRoom();
    }
});
