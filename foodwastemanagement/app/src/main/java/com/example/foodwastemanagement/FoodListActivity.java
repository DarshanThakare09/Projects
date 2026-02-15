package com.example.hotelfoodwastemanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodwastemanagement.R;

import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class FoodListActivity extends AppCompatActivity {

    private ListView listView;
    private LinkedList<String> availableFoodList;
    private static final String PREFS_NAME = "FoodData";
    private static final String FOOD_LIST_KEY = "FoodList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        listView = findViewById(R.id.listViewFood);
        availableFoodList = loadFoodList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, availableFoodList);
        listView.setAdapter(adapter);
    }

    private LinkedList<String> loadFoodList() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet(FOOD_LIST_KEY, new HashSet<>());
        return new LinkedList<>(set);
    }
}
