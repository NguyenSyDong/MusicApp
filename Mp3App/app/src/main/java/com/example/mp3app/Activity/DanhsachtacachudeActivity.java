package com.example.mp3app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mp3app.Adapter.DanhsachtatcachudeAdapter;
import com.example.mp3app.Model.ChuDe;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtacachudeActivity extends AppCompatActivity {

    RecyclerView recyclerViewChuDe;
    Toolbar toolbarChuDe;
    DanhsachtatcachudeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtacachude);
        init();
        getData();
    }

    private void getData() {
        Dataservice dataService = APIService.getService();
        Call<List<ChuDe>> callBack = dataService.getAllChuDe();
        callBack.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangChuDe = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter = new DanhsachtatcachudeAdapter(DanhsachtacachudeActivity.this,mangChuDe);
                recyclerViewChuDe.setLayoutManager(new GridLayoutManager(DanhsachtacachudeActivity.this,1));
                recyclerViewChuDe.setAdapter(danhSachChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewChuDe = findViewById(R.id.recycleviewAllchude);
        toolbarChuDe = findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbarChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}