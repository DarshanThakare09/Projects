package com.example.foodwastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFoodActivity extends AppCompatActivity {

    EditText foodName, quantity, time, location;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodName = findViewById(R.id.foodName);
        quantity = findViewById(R.id.quantity);
        time = findViewById(R.id.time);
        location = findViewById(R.id.location);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(v -> {
            String name = foodName.getText().toString().trim();
            String qty = quantity.getText().toString().trim();
            String t = time.getText().toString().trim();
            String loc = location.getText().toString().trim();

            // Validation
            if (name.isEmpty() || qty.isEmpty() || t.isEmpty() || loc.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }


            FoodItem item = new FoodItem(name, qty, t, loc);


            if (MainActivity.foodList != null) {
                MainActivity.foodList.add(item);
                MainActivity.undoStack.push(item);
                Toast.makeText(this, "Food added successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error: Food list not found!", Toast.LENGTH_SHORT).show();
            }


            finish();
        });
    }
}
