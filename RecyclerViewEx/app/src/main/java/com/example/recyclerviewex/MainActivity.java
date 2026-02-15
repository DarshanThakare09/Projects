package com.example.recyclerviewex;


import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<UserModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list.add(new UserModel(R.drawable.img,"DAsrh","111111111111"));
        list.add(new UserModel(R.drawable.img,"DAsrh","111111111111"));
        list.add(new UserModel(R.drawable.img,"DAsrh","111111111111"));
        list.add(new UserModel(R.drawable.img,"DAsrh","11118515611"));
        list.add(new UserModel(R.drawable.img,"DAsrh","111111111111"));
        list.add(new UserModel(R.drawable.img,"DAsrh","111113418512111"));
        list.add(new UserModel(R.drawable.img,"DAsrh","111111111111"));
        list.add(new UserModel(R.drawable.img,"DAsrh","111111111111"));
        list.add(new UserModel(R.drawable.img,"harrh","9999999911111"));

        RecyclerUserAdapter adapter = new RecyclerUserAdapter(this,list);
        recyclerView.setAdapter(adapter);


    }
}