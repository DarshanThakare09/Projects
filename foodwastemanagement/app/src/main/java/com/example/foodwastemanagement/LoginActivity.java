package com.example.foodwastemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.foodwastemanagement;
import com.example.foodwastemanagement.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etPassword;
    private RadioGroup rgRole;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = findViewById(R.id.etPassword);
        rgRole = findViewById(R.id.rgRole);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRole = rgRole.getCheckedRadioButtonId();

                if (selectedRole == -1) {
                    Toast.makeText(LoginActivity.this, "Please select a role", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton rbSelected = findViewById(selectedRole);
                String role = rbSelected.getText().toString();

                if (role.equals("Volunteer")) {
                    // Volunteer can directly see the food list
                    startActivity(new Intent(LoginActivity.this, com.example.hotelfoodwastemanagement.FoodListActivity.class));
                    finish();
                } else if (role.equals("Admin")) {
                    // Admin needs password
                    String password = etPassword.getText().toString().trim();
                    if (password.equals("123")) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
