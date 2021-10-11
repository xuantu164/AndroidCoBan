package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appnhac.Model.CountryData;
import com.example.appnhac.R;

public class DangNhapSdtActivity extends AppCompatActivity {
    ImageView imageView,imgView2;
    TextView txttenungdung,txtnhapsdt;
    Button button;
    AnimationDrawable mDrawable;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_sdt);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        relativeLayout = findViewById(R.id.relativeLayout);
        mDrawable = (AnimationDrawable) relativeLayout.getBackground();
        mDrawable.setEnterFadeDuration(3000);
        mDrawable.setExitFadeDuration(3000);
        mDrawable.start();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        button = findViewById(R.id.buttonContinue);
        mDrawable = (AnimationDrawable) button.getBackground();
        mDrawable.setEnterFadeDuration(3000);
        mDrawable.setExitFadeDuration(3000);
        mDrawable.start();

        imageView = findViewById(R.id.imageviewdangnhapsdt);
        txtnhapsdt = findViewById(R.id.textViewsdt);
        txttenungdung = findViewById(R.id.textviewdangnhapungdungnghenhac);
        //button = findViewById(R.id.buttonContinue);
        //imgView2 = findViewById(R.id.imageView);

        imageView.setAnimation(AnimationUtils.loadAnimation(DangNhapSdtActivity.this,R.anim.fall_down));
        txttenungdung.setAnimation(AnimationUtils.loadAnimation(DangNhapSdtActivity.this,R.anim.layout_animation_right));
        txtnhapsdt.setAnimation(AnimationUtils.loadAnimation(DangNhapSdtActivity.this,R.anim.fade_transition_animation));
        button.setAnimation(AnimationUtils.loadAnimation(DangNhapSdtActivity.this,R.anim.slide_bottom));

    }
}