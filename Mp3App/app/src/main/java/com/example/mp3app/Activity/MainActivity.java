package com.example.mp3app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.mp3app.Adapter.MainViewPagerAdapter;
import com.example.mp3app.Adapter.MenuAdapter;
import com.example.mp3app.Fragment.Fragment_Tim_Kiem;
import com.example.mp3app.Fragment.Fragment_Trang_Chu;
import com.example.mp3app.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<ItemMenu> itemMenuArrayList;
    MenuAdapter menuAdapter;
    String username = "";
    ImageView imageView;
    TextView txtname, txtgmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getString("username");
        anhxa();
        init();
        actionToolBar();
        actionMenu();
    }

    private void actionMenu() {
        if(!username.equals("no")) {
            txtname.setText(username);
            txtgmail.setText(username+"@gmail.com");
            imageView.setImageResource(R.drawable.avatar);
            itemMenuArrayList = new ArrayList<>();
            itemMenuArrayList.add(new ItemMenu("Hello "+ username, R.drawable.ic_action_user));
            itemMenuArrayList.add(new ItemMenu("Bài hát đã thích", R.drawable.ic_action_love));
            itemMenuArrayList.add(new ItemMenu("Đăng xuất", R.drawable.ic_action_logout));
            menuAdapter = new MenuAdapter(this, R.layout.dong_item, itemMenuArrayList);
            listView.setAdapter(menuAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 2) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    if (i == 1) {
                        Intent intent = new Intent(MainActivity.this, DanhSachBaiHatDaYeuThichActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            });
        } else {
            imageView.setImageResource(R.drawable.user2);
            itemMenuArrayList = new ArrayList<>();
            itemMenuArrayList.add(new ItemMenu("Đăng nhập", R.drawable.ic_action_logout));
            menuAdapter = new MenuAdapter(this, R.layout.dong_item, itemMenuArrayList);
            listView.setAdapter(menuAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void anhxa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_header_container);
        listView = findViewById(R.id.listview);
        drawerLayout = findViewById(R.id.drawerlayout);
        imageView = findViewById(R.id.imgview);
        txtname = findViewById(R.id.txtten);
        txtgmail = findViewById(R.id.txtgmail);
    }
}
