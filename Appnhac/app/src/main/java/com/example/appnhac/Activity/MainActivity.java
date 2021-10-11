package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appnhac.Adapter.MainViewPagerAdapter;
import com.example.appnhac.Fragment.Fragment_Bai_Hat_Hot;
import com.example.appnhac.Fragment.Fragment_Login;
import com.example.appnhac.Fragment.Fragment_MV;
import com.example.appnhac.Fragment.Fragment_Tim_Kiem;
import com.example.appnhac.Fragment.Fragment_TrangChu;
import com.example.appnhac.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import static com.example.appnhac.Activity.DanhsachbaihatMainActivity.toolbar;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout linearLayout;
    FloatingActionButton floatingActionButtonnightlight;
    boolean Dark = false;
    AnimationDrawable mDrawable;

    String API_KEY = "AIzaSyD0hdNtikrk7PHx_VZH8VDDozoEeyq4_N8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();

        floatingActionButtonnightlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dark = !Dark;
                if(Dark) {
                    Toast.makeText(MainActivity.this, "Bật chế độ tối !", Toast.LENGTH_SHORT).show();
                    viewPager.setBackgroundColor(getResources().getColor(R.color.navigationBarColor));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.navigationBarColor));
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.navigationBarColor));
                }
                else {
                    Toast.makeText(MainActivity.this, "Tắt chế độ tối !", Toast.LENGTH_SHORT).show();
                    viewPager.setBackgroundColor(getResources().getColor(R.color.windowBackground));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.windowBackground));
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.windowBackground));
                }
            }
        });

    }

    private void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Bai_Hat_Hot(),"");
        mainViewPagerAdapter.addFragment(new Fragment_MV(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Login(),"");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconloved);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_yt);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_select);
    }

    private void anhxa(){
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPaper);
        floatingActionButtonnightlight = findViewById(R.id.nightligt);
        linearLayout = findViewById(R.id.linearlayoutmain);
    }
}