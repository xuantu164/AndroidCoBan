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
import com.example.appnhac.Activity.DanhsachcacplaylistActivity;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.appnhac.Activity.PlayNhacActivity.mangbaihat;

public class DanhsachcacplaylistAdapter extends RecyclerView.Adapter<DanhsachcacplaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> mangplaylist;

    public DanhsachcacplaylistAdapter(Context context, ArrayList<Playlist> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.txttenplaylist.setAnimation(AnimationUtils.loadAnimation(context,R.anim.layout_animation_right));
        holder.imghinhnen.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fall_down));
        holder.txttentacgiaplaylist.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

        Playlist playlist = mangplaylist.get(position);
        Picasso.with(context).load(playlist.getIcon()).into(holder.imghinhnen);
        holder.txttenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView txttenplaylist,txttentacgiaplaylist;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            txttentacgiaplaylist = itemView.findViewById(R.id.textviewtentacgiaplaylist);
            cardView = itemView.findViewById(R.id.cardviewdanhsachplaylist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatMainActivity.class);
                    intent.putExtra("itemplaylist",mangplaylist.get(getPosition()));
                    Toast.makeText(context, "Playlist: "+mangplaylist.get(getPosition()).getTen(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }
}
