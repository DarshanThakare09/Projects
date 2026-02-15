package com.example.foodwastemanagement;

public class FoodItem {
    String foodName;
    String quantity;
    String pickupTime;
    String location;

    public FoodItem(String foodName, String quantity, String pickupTime, String location) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.pickupTime = pickupTime;
        this.location = location;
    }

    public String getFoodName() { return foodName; }
    public String getQuantity() { return quantity; }
    public String getPickupTime() { return pickupTime; }
    public String getLocation() { return location; }
}
