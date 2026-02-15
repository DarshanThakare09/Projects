package com.dnine.multyscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class orderactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderactivity);

        Intent intent =  getIntent();
        String message = intent.getStringExtra(MainActivity.MSG);

        TextView textview = findViewById(R.id.ordertext);
        textview.setText(message);
    }
}