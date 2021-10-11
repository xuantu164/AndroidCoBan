package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.DanhsachbaihatMainActivity;
import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> mangallbum;

    public AllAlbumAdapter(Context context, ArrayList<Album> mangallbum) {
        this.context = context;
        this.mangallbum = mangallbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.txttenalbum.setAnimation(AnimationUtils.loadAnimation(context,R.anim.layout_animation_right));
        holder.imgallalbum.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fall_down));
        holder.txttentacgia.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

        Album album = mangallbum.get(position);
        Picasso.with(context).load(album.getHinhanhAlbum()).into(holder.imgallalbum);
        holder.txttenalbum.setText(album.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return mangallbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgallalbum;
        TextView txttenalbum,txttentacgia;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgallalbum = itemView.findViewById(R.id.imageviewdanhsachcacalbum);
            txttenalbum = itemView.findViewById(R.id.textviewtendanhsachcacalbum);
            txttentacgia = itemView.findViewById(R.id.textviewtentacgia);
            cardView = itemView.findViewById(R.id.cardviewallalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatMainActivity.class);
                    intent.putExtra("idalbum",mangallbum.get(getPosition()));
                    //Toast.makeText(context, "Album: "+mangallbum.get(getPosition()).getTenAlbum(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }
}
