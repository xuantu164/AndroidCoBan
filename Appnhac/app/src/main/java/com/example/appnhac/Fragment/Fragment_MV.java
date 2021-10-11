package com.example.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appnhac.Activity.DanhsachtatcaalbumActivity;
import com.example.appnhac.Activity.PlayVideoActivity;
import com.example.appnhac.Activity.YoutubeMvActivity;
import com.example.appnhac.Adapter.MVAdapter;
import com.example.appnhac.Adapter.YoutubeAdapter;
import com.example.appnhac.Model.Youtube;
import com.example.appnhac.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_MV extends Fragment {

    View view;
    TextView txtxemthemmv;
    ListView lvVideo;
    RecyclerView recyclerViewdanhsachvideo;
    ArrayList<Youtube> arrayVideo;
    YoutubeAdapter adapter;
    MVAdapter mvAdapter;
    String API_KEY_PLAYLIST = "AIzaSyC0y_cuegFANxwAM_hV9dHUhc6nmYPzEh0";
    String ID_PLAYLIST = "PLlu5F2hqUCHyhjrVyTO8yvgvu04mCaqF0";

    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_PLAYLIST +"&key="+API_KEY_PLAYLIST +"&maxResults=50";

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mv,container,false);
        anhxa();
        txtxemthemmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YoutubeMvActivity.class);
                startActivity(intent);
            }
        });
        mvAdapter = new MVAdapter(getActivity(),arrayVideo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewdanhsachvideo.setLayoutManager(linearLayoutManager);
        recyclerViewdanhsachvideo.setAdapter(mvAdapter);

        //adapter = new YoutubeAdapter(getActivity(),R.layout.dong_youtube,arrayVideo);
        //lvVideo.setAdapter(adapter);

        GetJsonYoutube(urlGetJson);

        /*lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("idVideoYouTube",arrayVideo.get(i).getIdVideo());
                Toast.makeText(getActivity(), arrayVideo.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });*/

        /*
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat,container,false);
        recyclerViewplaynhac = view.findViewById(R.id.recyclerviewPlaybaihat);
        if(PlayNhacActivity.mangbaihat.size() > 0){
            playnhacAdapter = new PlaynhacAdapter(getActivity(),PlayNhacActivity.mangbaihat);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playnhacAdapter);
        }
        return view;
         */

        return view;
    }

    private void anhxa(){
        //lvVideo = view.findViewById(R.id.listviewdanhsachvideo);
        arrayVideo = new ArrayList<>();
        txtxemthemmv = view.findViewById(R.id.textviewxemthemmv);
        recyclerViewdanhsachvideo = view.findViewById(R.id.recyclerviewdanhsachvideo);
    }
    private void GetJsonYoutube(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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

                                /*Toast.makeText(getActivity(), idVideo, Toast.LENGTH_SHORT).show();

                                Toast.makeText(YoutubeMvActivity.this, url, Toast.LENGTH_SHORT).show();

                                Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();*/
                                arrayVideo.add(new Youtube(title,url,idVideo));
                            }
                            mvAdapter.notifyDataSetChanged();
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
                        Toast.makeText(getActivity(), "Lỗi !!!", Toast.LENGTH_SHORT).show();

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}
