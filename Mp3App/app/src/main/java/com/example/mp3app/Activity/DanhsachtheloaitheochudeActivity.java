package com.example.mp3app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.mp3app.Adapter.DanhsachtheloaitheochudeAdapter;
import com.example.mp3app.Model.ChuDe;
import com.example.mp3app.Model.TheLoai;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewTheLoaiTheoChuDe;
    Toolbar toolbarTheLoaiTheoChuDe;
    DanhsachtheloaitheochudeAdapter danhSachTheLoaiTheoChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        GetIntent();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataService = APIService.getService();
        Call<List<TheLoai>> callBack = dataService.Gettheloaitheochude(chuDe.getIdChuDe());
        callBack.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangTheLoai = (ArrayList<TheLoai>) response.body();
                danhSachTheLoaiTheoChuDeAdapter = new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this,mangTheLoai);
                recyclerViewTheLoaiTheoChuDe.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));
                recyclerViewTheLoaiTheoChuDe.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTheLoaiTheoChuDe = findViewById(R.id.recycleviewtheloaitheochude);
        toolbarTheLoaiTheoChuDe = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbarTheLoaiTheoChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarTheLoaiTheoChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this,chuDe.getTenChuDe(),Toast.LENGTH_SHORT).show();
        }
    }
}