package com.example.mp3app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mp3app.Model.Account;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKiActivity extends AppCompatActivity {

    EditText username, password;
    String taikhoan, matkhau;
    private Button dangki;
    Context context;
    TextView txtLogin;
    ImageView imgBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        username = findViewById(R.id.edtUsernameRe);
        password = findViewById(R.id.edtPasswordRe);
        dangki = findViewById(R.id.btndangki);
        txtLogin = findViewById(R.id.txt_login);
        imgBackHome =findViewById(R.id.back);

        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKiActivity.this, DangNhapActivity.class));
            }
        });
        imgBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKiActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        taikhoan = username.getText().toString().trim();
        matkhau = password.getText().toString().trim();
        Dataservice dataService = APIService.getService();
        Call<String> callBack = dataService.Register(taikhoan, matkhau);
        callBack.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketQua = response.body();
                if (ketQua.equals("Success")) {
                    startActivity(new Intent(DangKiActivity.this, DangNhapActivity.class));
                    Toast.makeText(DangKiActivity.this, "Đã đăng kí thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Bị Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
