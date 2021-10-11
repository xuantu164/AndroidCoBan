package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appnhac.R;

public class LoadDisplayActivity extends AppCompatActivity {
    ImageView imageView;
    TextView txtfrom,txttuchelsea,txttenungdung;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_display);

        imageView = findViewById(R.id.imageviewloaddisplay);
        linearLayout = findViewById(R.id.linearlayoutloaddisplay);
        txttuchelsea = findViewById(R.id.textviewtuchelsealoaddisplay);
        txtfrom = findViewById(R.id.textviewfromloaddisplay);
        txttenungdung = findViewById(R.id.textviewiconloaddisplay);

        //linearLayout.setAnimation(AnimationUtils.loadAnimation(LoadDisplayActivity.this,R.anim.fade_scale_animation));
        imageView.setAnimation(AnimationUtils.loadAnimation(LoadDisplayActivity.this,R.anim.fall_down));
        txtfrom.setAnimation(AnimationUtils.loadAnimation(LoadDisplayActivity.this,R.anim.slide_bottom));
        txttuchelsea.setAnimation(AnimationUtils.loadAnimation(LoadDisplayActivity.this,R.anim.slide_bottom));
        txttenungdung.setAnimation(AnimationUtils.loadAnimation(LoadDisplayActivity.this,R.anim.layout_animation_right));

        //Dùng cài đặt sau 5 giây màn hình tự chuyển
        Thread hengio= new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent intent = new Intent(LoadDisplayActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                }
            }
        };
        hengio.start();
    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }
}