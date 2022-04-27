package com.example.mp3app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
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
import com.example.mp3app.Model.Baihat;
import com.example.mp3app.Model.ResponseModel;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {

    EditText username, password;
    String taikhoan, matkhau;
    private Button dangnhap;
    ArrayList<Account> accountArrayList;
    ImageView imgBackHome;
    TextView txtDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        dangnhap = findViewById(R.id.btndangnhap);
        txtDangKi = findViewById(R.id.txtdangki);
        imgBackHome =findViewById(R.id.imgBackHome);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        imgBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        txtDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, DangKiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        final ProgressDialog progressDialog = new ProgressDialog(DangNhapActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        taikhoan = username.getText().toString().trim();
        matkhau = password.getText().toString().trim();
        Dataservice networkService = APIService.getService();
        Call<List<Account>> login = networkService.login(taikhoan, matkhau);
        login.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(@NonNull Call<List<Account>> call, @NonNull Response<List<Account>> response) {
                accountArrayList = (ArrayList<Account>) response.body();
                if (accountArrayList != null) {
                        progressDialog.dismiss();
                        Intent intent =  new Intent(DangNhapActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username", taikhoan);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu sai !", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }

            @Override
            public void onFailure(@NonNull Call<List<Account>> call, @NonNull Throwable t) {
                Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu sai !", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}