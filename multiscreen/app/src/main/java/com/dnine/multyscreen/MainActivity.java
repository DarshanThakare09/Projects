package com.dnine.multyscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String MSG = "com.dasrh-d9.multyscreen.msg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void placeorder(View view){
        Intent intent = new Intent(this,orderactivity.class);
        EditText edittext1 = findViewById(R.id.e1);
        EditText edittext2 = findViewById(R.id.e2);
        EditText edittext3 = findViewById(R.id.e3);
        String message = edittext1.getText().toString() +","+edittext2.getText().toString() +
                ","+edittext3.getText().toString() ;
        intent.putExtra(MSG,message);
        startActivity(intent);
    }
}