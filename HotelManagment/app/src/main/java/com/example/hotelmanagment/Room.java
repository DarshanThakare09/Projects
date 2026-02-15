package com.example.hotelmanagment;

public class Room {
    private String roomNumber;
    private String type; // e.g., Single/Double
    private double price;
    private boolean occupied;

    public Room(String roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.occupied = false;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occ) { this.occupied = occ; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - ₹" + price + (occupied ? " [Occupied]" : " [Free]");
    }
}
