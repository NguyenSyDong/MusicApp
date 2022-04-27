package com.example.mp3app.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.mp3app.R;

public class KhoiDongActivity extends AppCompatActivity {

    View view;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoi_dong);
        if (getIntent().getBooleanExtra("EXIT", false)){
            finish();
        }else {
            view = findViewById(R.id.logomain);
            overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
            animation = AnimationUtils.loadAnimation(this, R.anim.anim_intent_in_main);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.VISIBLE);
                    view.startAnimation(animation);
                }
            }, 2500);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }, 7000);
        }
    }
}