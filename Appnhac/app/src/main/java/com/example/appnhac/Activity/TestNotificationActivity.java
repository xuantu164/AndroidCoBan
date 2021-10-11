package com.example.appnhac.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.appnhac.Adapter.CreateNotificationAdapter;
import com.example.appnhac.Adapter.DanhsachbaihatAdapter;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.Model.Tracks;
import com.example.appnhac.R;
import com.example.appnhac.Service.OnClearFromRecentServive;
import com.example.appnhac.Service.Playable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TestNotificationActivity extends AppCompatActivity implements Playable {
    Button button;
    NotificationManager notificationManager;
    List<Tracks> tracks;
    int position = 0;
    boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);

        popluateBaihat();

        button = findViewById(R.id.button2);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
            registerReceiver(broadcastReceiver,new IntentFilter("TRACKS_TRACKS"));
            startService(new Intent(getBaseContext(), OnClearFromRecentServive.class));
        }

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*CreateNotificationAdapter.createNotification(TestNotificationActivity.this,tracks.get(1),R.drawable.iconpause,
                        1,tracks.size() -1);*/
                if (isPlaying) {
                    onTrackPause();
                }
                else {
                    onTrackPlay();
                }
            }
        });
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
        CreateNotificationAdapter.createNotification(TestNotificationActivity.this,tracks.get(position),R.drawable.iconpause,position,tracks.size() -1);
    }

    @Override
    public void onTrackPause() {
        CreateNotificationAdapter.createNotification(TestNotificationActivity.this,tracks.get(position),R.drawable.iconplay,position,tracks.size() -1);
        isPlaying = false;
    }

    @Override
    public void onTrackPlay() {
        CreateNotificationAdapter.createNotification(TestNotificationActivity.this,tracks.get(position),R.drawable.iconpause,position,tracks.size() -1);
        isPlaying = true;
    }

    @Override
    public void onTrackNext() {
        position++;
        CreateNotificationAdapter.createNotification(TestNotificationActivity.this,tracks.get(position),R.drawable.iconpause,position,tracks.size() -1);
    }

    protected void onDestroy(){
        super.onDestroy();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.cancelAll();
        }
        unregisterReceiver(broadcastReceiver);
    }
}