package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.appnhac.Model.Youtube;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlayVideoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Youtube> videoYoutubeList;
    ArrayList<Youtube> mangyoutube;

    public PlayVideoAdapter(Context context, int layout, List<Youtube> videoYoutubeList) {
        this.context = context;
        this.layout = layout;
        this.videoYoutubeList = videoYoutubeList;
    }

    @Override
    public int getCount() {
        return videoYoutubeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgThumbnail;
        TextView txtTitle;
        //Toolbar toolbar;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_play_video,null);

            //holder.toolbar = view.findViewById(R.id.toolbarplayvideo);

            /*holder.txtTitle = view.findViewById(R.id.textviewtitlemv);
            holder.imgThumbnail = view.findViewById(R.id.imageviewhinhmv);*/
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        Youtube video = videoYoutubeList.get(i);

        holder.txtTitle.setText(mangyoutube.get(i).getTitle());
        //holder.txtTitle.setText(video.getTitle());
        Picasso.with(context).load(mangyoutube.get(i).getThumbnail()).into(holder.imgThumbnail);
        return view;
    }
}

