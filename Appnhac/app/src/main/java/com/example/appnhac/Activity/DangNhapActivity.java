package com.example.appnhac.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appnhac.Adapter.PlaylistAdapter;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

public class DangNhapActivity extends AppCompatActivity {
    Button btnlogin,btnloginwithaccout;
    LinearLayout linearLayout;
    ImageView imageView;
    TextView txtungdungnghenhac,txtfrom,txttuchelsea;
    AnimationDrawable mDrawable;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        linearLayout = findViewById(R.id.linearlayoutdangnhap);
        mDrawable = (AnimationDrawable) linearLayout.getBackground();
        mDrawable.setEnterFadeDuration(3000);
        mDrawable.setExitFadeDuration(3000);
        mDrawable.start();

        btnlogin = findViewById(R.id.btnlogin);
        btnloginwithaccout = findViewById(R.id.btnloginwithaccout);
        linearLayout = findViewById(R.id.linearlayoutdangnhap);
        txtungdungnghenhac = findViewById(R.id.textviewungdungnghenhac);
        imageView = findViewById(R.id.imageviewicondangnhap);
        txtfrom = findViewById(R.id.textviewfromdangnhap);
        txttuchelsea = findViewById(R.id.textviewtuchelseadangnhap);
        progressBar = findViewById(R.id.progressbardangnhap);

        imageView.setAnimation(AnimationUtils.loadAnimation(DangNhapActivity.this,R.anim.fall_down));
        btnlogin.setAnimation(AnimationUtils.loadAnimation(DangNhapActivity.this,R.anim.fade_transition_animation));
        btnloginwithaccout.setAnimation(AnimationUtils.loadAnimation(DangNhapActivity.this,R.anim.layout_animation_right));

        txtungdungnghenhac.setAnimation(AnimationUtils.loadAnimation(DangNhapActivity.this,R.anim.layout_animation_right));
        txtfrom.setAnimation(AnimationUtils.loadAnimation(DangNhapActivity.this,R.anim.slide_bottom));
        txttuchelsea.setAnimation(AnimationUtils.loadAnimation(DangNhapActivity.this,R.anim.slide_bottom));

        //linearLayout.setAnimation(AnimationUtils.loadAnimation(DangNhapActivity.this,R.anim.fade_scale_animation));

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Thread hengio= new Thread(){
            public void run()
            {
                try {
                    sleep(3000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        hengio.start();
            }
        });

        btnloginwithaccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Thread hengio= new Thread(){
                    public void run()
                    {
                        try {
                            sleep(3000);
                        } catch (Exception e) {

                        }
                        finally
                        {
                            Intent intent = new Intent(DangNhapActivity.this, DangNhapSdtActivity.class);
                            startActivity(intent);
                        }
                    }
                };
                hengio.start();

            }
        });

    }
}