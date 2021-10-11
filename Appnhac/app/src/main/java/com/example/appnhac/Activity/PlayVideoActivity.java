package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Adapter.PlayVideoAdapter;
import com.example.appnhac.Adapter.YoutubeAdapter;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.Model.Youtube;
import com.example.appnhac.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private List<Youtube> videoYoutubeList;
    public static ArrayList<Youtube> mangyoutube = new ArrayList<>();
    Toolbar toolbar;
    YouTubePlayerView youTubePlayerView;
    TextView txttitle;
    ImageView imghinhmv;
    String id = "";
    int REQUEST_VIDEO = 12;
    ArrayList<Youtube> arrayVideo;
    PlayVideoAdapter adapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        toolbar = findViewById(R.id.toolbarplayvideo);
        youTubePlayerView = findViewById(R.id.myYoutube);
        /*txttitle = findViewById(R.id.textviewtitlemv);
        imghinhmv = findViewById(R.id.imageviewhinhmv);*/

        Intent intent = getIntent();
        id = intent.getStringExtra("idVideoYouTube");

        /*((AppCompatActivity)getApplicationContext()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getApplicationContext()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getApplicationContext()).getSupportActionBar().setTitle("MV");*/
        //Toast.makeText(this, id , Toast.LENGTH_SHORT).show();

        //txttitle.setText("MV CÓ SỐ LƯỢT XEM KHỦNG");
        //txttitle.setTextColor(R.color.navigationBarColor);

        youTubePlayerView.initialize(YoutubeMvActivity.API_KEY_PLAYLIST,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(id);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(PlayVideoActivity.this,REQUEST_VIDEO);
        }
        else
        {
            Toast.makeText(this, "Error !!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO){
            youTubePlayerView.initialize(YoutubeMvActivity.API_KEY_PLAYLIST,PlayVideoActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);    }
}