package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.DanhsachbaihatMainActivity;
import com.example.appnhac.Activity.PlayVideoActivity;
import com.example.appnhac.Activity.YoutubeMvActivity;
import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.Youtube;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MVAdapter extends RecyclerView.Adapter<MVAdapter.ViewHolder>{
    Context context;
    ArrayList<Youtube> mangyoutube;

    public MVAdapter(Context context, ArrayList<Youtube> mangyoutube) {
        this.context = context;
        this.mangyoutube = mangyoutube;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_youtube,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txttenvideo.setAnimation(AnimationUtils.loadAnimation(context,R.anim.layout_animation_right));
        holder.imghinhvideo.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        //holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        Youtube youtube = mangyoutube.get(position);
        holder.txttenvideo.setText(youtube.getTitle());
        Picasso.with(context).load(youtube.getThumbnail()).into(holder.imghinhvideo);
    }

    @Override
    public int getItemCount() {
        return mangyoutube.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhvideo;
        TextView txttenvideo;
        Toolbar toolbar;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhvideo = itemView.findViewById(R.id.imageviewThumbnail);
            txttenvideo = itemView.findViewById(R.id.textviewTitleMV);
            relativeLayout = itemView.findViewById(R.id.relativelayuotcacvideo);
            toolbar = itemView.findViewById(R.id.toolbarplayvideo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayVideoActivity.class);
                    intent.putExtra("idVideoYouTube",mangyoutube.get(getPosition()).getIdVideo());
                    Toast.makeText(context, mangyoutube.get(getPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);

                    //((AppCompatActivity)context).setSupportActionBar(toolbar);
                    //((AppCompatActivity)context).getSupportActionBar().setTitle(mangyoutube.get(getPosition()).getTitle());

                }
            });
        }
    }
}

