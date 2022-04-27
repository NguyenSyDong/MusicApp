package com.example.mp3app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mp3app.R;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin, btnRegister, btnhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = findViewById(R.id.login_button);
        btnRegister = findViewById(R.id.register_button);
        btnhome = findViewById(R.id.home_button);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DangKiActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", "no");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}