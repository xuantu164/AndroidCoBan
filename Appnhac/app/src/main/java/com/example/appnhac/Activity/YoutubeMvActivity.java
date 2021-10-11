package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appnhac.Adapter.YoutubeAdapter;
import com.example.appnhac.Model.Youtube;
import com.example.appnhac.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YoutubeMvActivity extends AppCompatActivity {
    String API_KEY = "AIzaSyD0hdNtikrk7PHx_VZH8VDDozoEeyq4_N8";
    public static String API_KEY_PLAYLIST = "AIzaSyC0y_cuegFANxwAM_hV9dHUhc6nmYPzEh0";
    String ID_PLAYLIST = "PLlu5F2hqUCHyhjrVyTO8yvgvu04mCaqF0";

    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_PLAYLIST +"&key="+API_KEY_PLAYLIST +"&maxResults=50";

    /*int REQUEST_VIDEO = 123;
    Toolbar toolbar;
    YouTubePlayerView youTubePlayerView;*/

    ListView lvVideo;
    Toolbar toolbar;
    Toolbar toolbar2;
    ArrayList<Youtube> arrayVideo;
    YoutubeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_mv);

        /*toolbar = findViewById(R.id.toolbardanhsachmv);
        youTubePlayerView = findViewById(R.id.myYoutube);
        youTubePlayerView.initialize(API_KEY,this);*/

        //((AppCompatActivity)).setSupportActionBar(toolbar);

        //youtubeAdapter = new YoutubeAdapter(this,R.layout.dong_youtube,arrayListVideo);

        /*
        allAlbumAdapter = new AllAlbumAdapter(DanhsachtatcaalbumActivity.this,mangalbum);
                recyclerViewalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this,2));
                recyclerViewalbum.setAdapter(allAlbumAdapter);
         */
        toolbar = findViewById(R.id.toolbardanhsachcacmv);
        lvVideo = findViewById(R.id.listviewdanhsachmv);
        toolbar2 = findViewById(R.id.toolbarplayvideo);
        arrayVideo = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("MV CÓ SỐ LƯỢT XEM KHỦNG");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new YoutubeAdapter(this,R.layout.dong_youtube,arrayVideo);
        lvVideo.setAdapter(adapter);

        GetJsonYoutube(urlGetJson);

        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(YoutubeMvActivity.this, PlayVideoActivity.class);
                intent.putExtra("idVideoYouTube",arrayVideo.get(i).getIdVideo());
                Toast.makeText(YoutubeMvActivity.this, arrayVideo.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                startActivity(intent);

                /*setSupportActionBar(toolbar2);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(arrayVideo.get(i).getTitle());*/
            }
        });
    }
/*
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("JGwWNGJdvx8");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(YoutubeMvActivity.this,REQUEST_VIDEO);
        }
        else
        {
            Toast.makeText(this, "Error !!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO){
            youTubePlayerView.initialize(API_KEY,YoutubeMvActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/

    private void GetJsonYoutube(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String title = ""; String url = ""; String idVideo = "";
                            for(int i = 0; i < jsonItems.length(); i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                title = jsonSnippet.getString("title");

                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");

                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");

                                url = jsonMedium.getString("url");

                                JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");

                                idVideo = jsonResourceID.getString("videoId");

                                /*Toast.makeText(YoutubeMvActivity.this, idVideo, Toast.LENGTH_SHORT).show();

                                Toast.makeText(YoutubeMvActivity.this, url, Toast.LENGTH_SHORT).show();

                                Toast.makeText(YoutubeMvActivity.this, title, Toast.LENGTH_SHORT).show();*/
                                arrayVideo.add(new Youtube(title,url,idVideo));
                            }
                            adapter.notifyDataSetChanged();
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(YoutubeMvActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(YoutubeMvActivity.this, "Lỗi !!!", Toast.LENGTH_SHORT).show();

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}