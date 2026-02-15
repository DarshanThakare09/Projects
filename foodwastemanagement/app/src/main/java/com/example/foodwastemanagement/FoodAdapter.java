package com.example.foodwastemanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    LinkedList<FoodItem> foodList;

    public FoodAdapter(LinkedList<FoodItem> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem item = foodList.get(position);
        holder.name.setText(item.getFoodName());
        holder.quantity.setText("Quantity: " + item.getQuantity());
        holder.time.setText("Pickup Time: " + item.getPickupTime());
        holder.location.setText("Location: " + item.getLocation());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, time, location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodNameText);
            quantity = itemView.findViewById(R.id.foodQuantityText);
            time = itemView.findViewById(R.id.foodTimeText);
            location = itemView.findViewById(R.id.foodLocationText);
        }
    }
}
