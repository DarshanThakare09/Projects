package com.example.hotelmanagment;

import java.util.ArrayList;
import java.util.UUID;

public class RoomManager {
    private CustomLinkedList<Room> rooms;
    private CustomQueue<Booking> waitingQueue;
    private CustomStack<String> actionStack;

    public RoomManager() {
        rooms = new CustomLinkedList<>();
        waitingQueue = new CustomQueue<>();
        actionStack = new CustomStack<>();
    }

    // Rooms
    public void addRoom(String roomNumber, String type, double price) {
        rooms.add(new Room(roomNumber, type, price));
        actionStack.push("Added room " + roomNumber);
    }

    public ArrayList<Room> listRooms() {
        return rooms.toArrayList();
    }

    public Room findFreeRoomByType(String type) {
        return rooms.find(r -> !r.isOccupied() && (type == null || type.isEmpty() || r.getType().equalsIgnoreCase(type)));
    }

    // Bookings
    public Booking createBooking(String guestName, String preferredType) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Booking b = new Booking(id, guestName, preferredType);
        Room free = findFreeRoomByType(preferredType);
        if (free != null) {
            free.setOccupied(true);
            b.setAssignedRoom(free.getRoomNumber());
            actionStack.push("Booked " + free.getRoomNumber() + " for " + guestName);
        } else {
            waitingQueue.enqueue(b);
            actionStack.push("Enqueued booking " + id + " for " + guestName);
        }
        return b;
    }

    public boolean checkoutRoom(String roomNumber) {
        // mark room free
        Room r = rooms.find(room -> room.getRoomNumber().equals(roomNumber));
        if (r == null) return false;
        if (!r.isOccupied()) return false;
        r.setOccupied(false);
        actionStack.push("Checked out " + roomNumber);
        // assign to waiting if exists
        if (!waitingQueue.isEmpty()) {
            Booking next = waitingQueue.dequeue();
            r.setOccupied(true);
            next.setAssignedRoom(r.getRoomNumber());
            actionStack.push("Assigned waiting " + next.getGuestName() + " to " + r.getRoomNumber());
        }
        return true;
    }

    public java.util.List<Booking> getWaitingList() {
        return waitingQueue.toList();
    }

    public java.util.List<String> getRecentActions() {
        return actionStack.toList();
    }
}