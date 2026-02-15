package com.example.foodwastemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FoodAdapter adapter;
    Button addFoodBtn, undoBtn, refreshBtn, volunteerBtn;

    // Data Structures
    static LinkedList<FoodItem> foodList = new LinkedList<>();
    static Stack<FoodItem> undoStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addFoodBtn = findViewById(R.id.addFoodBtn);
        undoBtn = findViewById(R.id.undoBtn);
        refreshBtn = findViewById(R.id.refreshBtn);


        adapter = new FoodAdapter(foodList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addFoodBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddFoodActivity.class));
        });

        undoBtn.setOnClickListener(v -> {
            if (!undoStack.isEmpty()) {
                FoodItem last = undoStack.pop();
                foodList.remove(last);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Undo last added food", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Nothing to undo", Toast.LENGTH_SHORT).show();
            }
        });

        refreshBtn.setOnClickListener(v -> {
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "List refreshed!", Toast.LENGTH_SHORT).show();
        });


    }
}
