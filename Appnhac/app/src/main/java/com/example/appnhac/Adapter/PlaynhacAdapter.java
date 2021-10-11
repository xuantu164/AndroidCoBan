package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.DanhsachbaihatMainActivity;
import com.example.appnhac.Activity.PlayNhacActivity;
import com.example.appnhac.Fragment.Fragment_Dia_Nhac;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.example.appnhac.Activity.PlayNhacActivity.TimeSong;
import static com.example.appnhac.Activity.PlayNhacActivity.fragment_dia_nhac;
import static com.example.appnhac.Activity.PlayNhacActivity.sktime;
import static com.example.appnhac.Activity.PlayNhacActivity.toolbarplaynhac;
import static com.example.appnhac.Activity.PlayNhacActivity.txtTimesong;
import static com.example.appnhac.Fragment.Fragment_Dia_Nhac.objectAnimator;

import static com.example.appnhac.Activity.PlayNhacActivity.mediaPlayer;

public class PlaynhacAdapter extends  RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;

    public PlaynhacAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttenbaihat.setAnimation(AnimationUtils.loadAnimation(context,R.anim.layout_animation_right));
        holder.txtcasi.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.txtindex.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.imghinh.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fall_down));

        //holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        Baihat baihat = mangbaihat.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txtindex.setText(position + 1 + "");
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imghinh);
        holder.txttenbaihat.setText(baihat.getTenbaihat());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txtcasi;
        ImageView imghinh;
        Toolbar toolbar;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex = itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
            imghinh = itemView.findViewById(R.id.imageviewplaynhac);
            toolbar = itemView.findViewById(R.id.toolbarplaynhac);
            cardView = itemView.findViewById(R.id.cardviewplaynhac);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Đang phát " + mangbaihat.get(getPosition()).getTenbaihat() + " - " + mangbaihat.get(getPosition()).getCasi(), Toast.LENGTH_SHORT).show();
                    if(mangbaihat.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        new PlayMp3().execute(mangbaihat.get(getPosition()).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(getPosition()).getHinhbaihat());
                        objectAnimator.start();

                        //((AppCompatActivity)context).setSupportActionBar(toolbar);
                        ((AppCompatActivity)context).getSupportActionBar().setTitle(mangbaihat.get(getPosition()).getTenbaihat()+ " - " +mangbaihat.get(getPosition()).getCasi());
                    }
                }
            });
        }
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

    private void UpdateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    /*mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });*/
                }
            }
        },300);
    }
}
