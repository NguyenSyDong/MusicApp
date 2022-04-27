package com.example.mp3app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mp3app.R;

public class DanhSachBaiHatDaYeuThichActivity extends AppCompatActivity {

    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat_da_yeu_thich);
        imgBack =findViewById(R.id.backBlack);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(DanhSachBaiHatDaYeuThichActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}