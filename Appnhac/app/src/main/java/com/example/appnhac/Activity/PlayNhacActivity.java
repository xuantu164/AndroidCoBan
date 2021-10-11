package com.example.appnhac.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.appnhac.Adapter.CreateNotificationAdapter;
import com.example.appnhac.Adapter.ViewPagerPlaylist;
import com.example.appnhac.Fragment.Fragment_Dia_Nhac;
import com.example.appnhac.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.appnhac.Fragment.Fragment_TrangChu;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.Model.Tracks;
import com.example.appnhac.R;
import com.example.appnhac.Service.OnClearFromRecentServive;
import com.example.appnhac.Service.Playable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.appnhac.Fragment.Fragment_Dia_Nhac.objectAnimator;

public class
PlayNhacActivity extends AppCompatActivity implements Playable {

    public static Toolbar toolbarplaynhac;
    public static TextView txtTimesong,txtTotaltimesong;
    public static SeekBar sktime;
    ImageButton imgplay,imgrepeat,imgnext,imgpre,imgrandom;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylist adapternhac;
    public static Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    public static MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    private ProgressBar progressBar;
    TextView txtchoxulyplaynhac;

    //moi them
    NotificationManager notificationManager;
    List<Tracks> tracks;
    boolean isPlaying = false;
    //moi them


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();

        popluateBaihat();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
            registerReceiver(broadcastReceiver,new IntentFilter("TRACKS_TRACKS"));
            startService(new Intent(getBaseContext(), OnClearFromRecentServive.class));
        }

        if (isPlaying) {
            onTrackPause();
        }
        else {
            onTrackPlay();
        }

        /*progressBar.setVisibility(View.VISIBLE);
        txtchoxulyplaynhac.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                txtchoxulyplaynhac.setVisibility(View.GONE);
            }
        },5000);*/

        evenClick();
    }

    private void createChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CreateNotificationAdapter.CHANNEL_ID,
                    "KOD Dev",NotificationManager.IMPORTANCE_LOW);

            notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
    private void popluateBaihat(){
        tracks = new ArrayList<>();

        tracks.add(new Tracks("Track1","Artist1",R.drawable.ic_haha));
        tracks.add(new Tracks("Track2","Artist2",R.drawable.ic_thuongthuong));
        tracks.add(new Tracks("Track3","Artist3",R.drawable.ic_hoa));
        tracks.add(new Tracks("Track4","Artist4",R.drawable.ic_wow));
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getExtras().getString("actionname");

            switch (action){
                case  CreateNotificationAdapter.ACTIONPREVIOUS:
                    onTrackPrevious();
                    break;
                case CreateNotificationAdapter.ACTIONPLAY:
                    if(isPlaying){
                        onTrackPause();
                    }
                    else {
                        onTrackPlay();
                    }
                    break;
                case  CreateNotificationAdapter.ACTIONNEXT:
                    onTrackNext();
                    break;
            }
        }
    };

    @Override
    public void onTrackPrevious() {
        position--;
        CreateNotificationAdapter.createNotification(PlayNhacActivity.this,tracks.get(position),R.drawable.iconpause,position,tracks.size() -1);
    }

    @Override
    public void onTrackPause() {
        CreateNotificationAdapter.createNotification(PlayNhacActivity.this,tracks.get(position),R.drawable.iconplay,position,tracks.size() -1);
        isPlaying = false;
    }

    @Override
    public void onTrackPlay() {
        CreateNotificationAdapter.createNotification(PlayNhacActivity.this,tracks.get(position),R.drawable.iconpause,position,tracks.size() -1);
        isPlaying = true;
    }

    @Override
    public void onTrackNext() {
        position++;
        CreateNotificationAdapter.createNotification(PlayNhacActivity.this,tracks.get(position),R.drawable.iconpause,position,tracks.size() -1);
    }

    protected void onDestroy(){
        super.onDestroy();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.cancelAll();
        }
        unregisterReceiver(broadcastReceiver);
    }

    private void evenClick() {
        final Handler handler = new Handler();
        mediaPlayer = new MediaPlayer();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(mangbaihat.size() > 0){
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }
                    else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);

        imgplay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    Toast.makeText(PlayNhacActivity.this, "Tạm dừng !", Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                    if (fragment_dia_nhac.objectAnimator!=null){
                        fragment_dia_nhac.objectAnimator.pause();
                    }
                }
                else
                {
                    Toast.makeText(PlayNhacActivity.this, "Tiếp tục !", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    if (fragment_dia_nhac.objectAnimator!=null){
                        fragment_dia_nhac.objectAnimator.resume();
                    }
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat == false){
                    if(checkrandom == true){
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                    }
                    Toast.makeText(PlayNhacActivity.this, "Bật phát lại ca khúc !", Toast.LENGTH_SHORT).show();
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }
                else {
                    Toast.makeText(PlayNhacActivity.this, "Tắt phát lại ca khúc !", Toast.LENGTH_SHORT).show();
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkrandom == false){
                    if(repeat == true){
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    Toast.makeText(PlayNhacActivity.this, "Bật phát ngẫu nhiên !", Toast.LENGTH_SHORT).show();
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }
                else {
                    Toast.makeText(PlayNhacActivity.this, "Tắt phát ngẫu nhiên !", Toast.LENGTH_SHORT).show();
                    imgrandom.setImageResource(R.drawable.ic_random);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlayNhacActivity.this, "Next !", Toast.LENGTH_SHORT).show();
                if(mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > (mangbaihat.size() - 1)){
                            position = 0;
                        }
                        Toast.makeText(PlayNhacActivity.this, "Đang phát " +mangbaihat.get(position).getTenbaihat()+ " - " +mangbaihat.get(position).getCasi(), Toast.LENGTH_SHORT).show();
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        objectAnimator.start();
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat()+ " - " +mangbaihat.get(position).getCasi());
                        UpdateTime();
                    }
                }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },5000);
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlayNhacActivity.this, "Preview !", Toast.LENGTH_SHORT).show();
                if(mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index + 1;
                            }
                            position = index;
                        }
                        if(position < (mangbaihat.size() - (mangbaihat.size() -1 ))){
                            position = 0;
                        }
                        Toast.makeText(PlayNhacActivity.this, "Đang phát " +mangbaihat.get(position).getTenbaihat()+ " - " +mangbaihat.get(position).getCasi(), Toast.LENGTH_SHORT).show();
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        objectAnimator.start();
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat()+  " - " +mangbaihat.get(position).getCasi());
                        UpdateTime();
                    }
                }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if(intent != null){
            if(intent.hasExtra("cakhuc")){
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);
                //Toast.makeText(this, "Đang phát: "+baihat.getTenbaihat() + " - "+baihat.getCasi(), Toast.LENGTH_SHORT).show();
            }
        if(intent.hasExtra("cacbaihat")){
            ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
            /*for(int i = 0; i < mangbaihat.size(); i++){
                Log.d("TTT",mangbaihat.get(i).getTenbaihat());
            }*/
            mangbaihat = baihatArrayList;
            }
        }

    }

    public void init(){
        progressBar = findViewById(R.id.progressbarchoxulyplaynhac);
        txtchoxulyplaynhac = findViewById(R.id.textviewchoxulyplaynhac);
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgpre = findViewById(R.id.imagebuttonpreview);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.BLACK);

        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewPagerPlaylist(getSupportFragmentManager());

        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        adapternhac.AddFragment(fragment_dia_nhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);

        if(mangbaihat.size() > 0){
            //Lấy chiều cao của ActionBar
            TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
            int actionBarSize = (int) styledAttributes.getDimension(0, 0);
            styledAttributes.recycle();

            //Drawable drawable1 = getResources().getDrawable(R.drawable.iconback);
            //Bitmap bitmap1 = ((BitmapDrawable) drawable1).getBitmap();
            //Drawable newdrawable1 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap1, actionBarSize,  actionBarSize, true));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(newdrawable1);

            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat()+ " - " + mangbaihat.get(0).getCasi());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    public static void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if(position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > (mangbaihat.size() - 1)){
                            position = 0;
                        }
                        Toast.makeText(PlayNhacActivity.this, "Đang phát " +mangbaihat.get(position).getTenbaihat()+  " - " +mangbaihat.get(position).getCasi(), Toast.LENGTH_SHORT).show();
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhbaihat());
                        objectAnimator.start();
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                    }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }
                else {
                    handler.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
