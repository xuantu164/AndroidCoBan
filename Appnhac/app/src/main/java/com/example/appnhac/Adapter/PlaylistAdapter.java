package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground,imgplaylist;
        RelativeLayout relativeLayout;
        CardView cardView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.dong_playlist,null);
                viewHolder = new ViewHolder();
                viewHolder.txttenplaylist = convertView.findViewById(R.id.textviewtenplaylist);
                viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
                viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
                viewHolder.relativeLayout = convertView.findViewById(R.id.relativelayoutcacplaylist);
                //viewHolder.cardView = convertView.findViewById(R.id.cardviewplaylist);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            viewHolder.imgplaylist.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fall_down));
            viewHolder.txttenplaylist.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.layout_animation_right));
            viewHolder.imgbackground.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));
            //viewHolder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));

            Playlist playlist = getItem(position);
            Picasso.with(getContext()).load(playlist.getHinhPlayList()).into(viewHolder.imgbackground);
            Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgplaylist);
            viewHolder.txttenplaylist.setText(playlist.getTen());
            return convertView;
    }
}
