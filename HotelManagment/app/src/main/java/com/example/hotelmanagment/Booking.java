package com.example.hotelmanagment;


public class Booking {
    private String id;
    private String guestName;
    private String preferredType; // guest preference
    private String assignedRoom; // null until assigned

    public Booking(String id, String guestName, String preferredType) {
        this.id = id;
        this.guestName = guestName;
        this.preferredType = preferredType;
        this.assignedRoom = null;
    }

    public String getId() { return id; }
    public String getGuestName() { return guestName; }
    public String getPreferredType() { return preferredType; }
    public String getAssignedRoom() { return assignedRoom; }
    public void setAssignedRoom(String r) { assignedRoom = r; }

    @Override
    public String toString() {
        return "Booking " + id + " - " + guestName + (assignedRoom == null ? " (Waiting: " + preferredType + ")" : " [Room: " + assignedRoom + "]");
    }
}
