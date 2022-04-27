package com.example.mp3app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mp3app.Adapter.DanhSachBaiHatAdapter;
import com.example.mp3app.Model.Album;
import com.example.mp3app.Model.Baihat;
import com.example.mp3app.Model.Playlist;
import com.example.mp3app.Model.Quangcao;
import com.example.mp3app.Model.TheLoai;
import com.example.mp3app.R;
import com.example.mp3app.Service.APIService;
import com.example.mp3app.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    Quangcao quangcao;
    ImageView imageView;
    ArrayList<Baihat> baihatArrayList;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        DataIntent();
        anhxa();
        init();
        if(quangcao != null && !quangcao.getTenBaiHat().equals("")){
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getIdQuangCao());
        }
        if(playlist != null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(), playlist.getHinhPlaylist());
            getDataPlaylist(playlist.getIdPlaylist());
        }
        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIdTheLoai());
        }
        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            getDataAlbum(album.getIdAlbum());
        }
    }

    private void getDataAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, baihatArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataTheLoai(String idtheloai) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.Getdanhsachcacbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, baihatArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(String idPlaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.Getdanhsachbaihattheoplaylist(idPlaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, baihatArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.Getdanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, baihatArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerView.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imageView);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhxa(){
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerView = findViewById(R.id.relativelviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imageView = findViewById(R.id.imageviewdanhsachbaihat);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if (intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangcao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
                Toast.makeText(this, playlist.getTen(), Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this,PlayNhacActivity.class);
                intent.putExtra("cacbaihat",baihatArrayList);
                startActivity(intent);
            }
        });
    }
}