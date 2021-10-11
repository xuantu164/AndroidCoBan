package com.example.appnhac.Adapter;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appnhac.Model.Youtube;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class YoutubeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Youtube> videoYoutubeList;

    public YoutubeAdapter(Context context, int layout, List<Youtube> videoYoutubeList) {
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
        RelativeLayout relativeLayout;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_youtube,null);

            holder.txtTitle = view.findViewById(R.id.textviewTitleMV);
            holder.imgThumbnail = view.findViewById(R.id.imageviewThumbnail);
            holder.relativeLayout = view.findViewById(R.id.relativelayuotcacvideo);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        Youtube video = videoYoutubeList.get(i);

        holder.txtTitle.setAnimation(AnimationUtils.loadAnimation(context,R.anim.layout_animation_right));
        holder.imgThumbnail.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        //holder.relativeLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        holder.txtTitle.setText(video.getTitle());
        Picasso.with(context).load(video.getThumbnail()).into(holder.imgThumbnail);
        return view;
    }
}
