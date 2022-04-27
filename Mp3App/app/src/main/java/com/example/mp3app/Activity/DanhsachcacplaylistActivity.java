package com.example.mp3app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mp3app.Adapter.DanhsachcacplaylistAdapter;
import com.example.mp3app.Model.Playlist;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacplaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachPlaylist;
    DanhsachcacplaylistAdapter DanhsachcacplaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        anhXa();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataService = APIService.getService();
        Call<List<Playlist>> callBack = dataService.getDanhsachcacPlaylist();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangPlaylist = (ArrayList<Playlist>) response.body();
                DanhsachcacplaylistAdapter = new DanhsachcacplaylistAdapter(DanhsachcacplaylistActivity.this,mangPlaylist);
                recyclerViewDanhSachPlaylist.setLayoutManager(new GridLayoutManager(DanhsachcacplaylistActivity.this,2));
                recyclerViewDanhSachPlaylist.setAdapter(DanhsachcacplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolbardanhsachcacplaylist);
        recyclerViewDanhSachPlaylist = findViewById(R.id.recycleviewdanhsachcacplaylist);

    }
}