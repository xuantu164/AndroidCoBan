package com.example.appnhac.Adapter;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.PlayNhacActivity;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends  RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;
    NotificationManager notificationManager;


    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
        return new ViewHolder(view);
    }

    private void createChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CreateNotificationAdapter.CHANNEL_ID,"KOD DEV",NotificationManager.IMPORTANCE_LOW);
            //notificationManager = getSystemService
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.txttenbaihat.setAnimation(AnimationUtils.loadAnimation(context,R.anim.layout_animation_right));
        holder.txtcasi.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.txtindex.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

        holder.imghinhbaihat.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fall_down));
        holder.imgluotthich.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

        Baihat baihat = mangbaihat.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txttenbaihat.setText(baihat.getTenbaihat());
        holder.txtindex.setText(position + 1 + "");
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imghinhbaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txtcasi;
        ImageView imgluotthich,imghinhbaihat;
        CardView cardView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewtencasi);
            txtindex = itemView.findViewById(R.id.textviewdanhsachindex);
            txttenbaihat = itemView.findViewById(R.id.textviewtenbaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthichdanhsachbaihat);
            imghinhbaihat = itemView.findViewById(R.id.imageviewhinhanhbaihat);
            cardView = itemView.findViewById(R.id.cardviewdanhsachbaihat);

            /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                createChannel();
            }*/

            itemView.setOnClickListener(new View.OnClickListener() {
                //CreateNotification.createNotification(MainActivity.this,tracks.get(3),R.drawable.iconfloatingactionbutton,1,tracks.size()-1);
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    Toast.makeText(context, "Đang phát: " + mangbaihat.get(getPosition()).getTenbaihat() + " - " + mangbaihat.get(getPosition()).getCasi(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);

                    /*if(mangbaihat.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }*/
                    /*Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    Toast.makeText(context, "Đang phát: " + mangbaihat.get(getPosition()).getTenbaihat() + " - " + mangbaihat.get(getPosition()).getCasi(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);*/
                    //CreateNotificationAdapter.createNotification(context, mangbaihat.get(getPosition()), R.drawable.iconfloatingactionbutton, 1, mangbaihat.size() - 1);
                    //}
                }
            });

            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Toast.makeText(context, "Bạn đã thích ca khúc: " + mangbaihat.get(getPosition()).getTenbaihat() + " - " + mangbaihat.get(getPosition()).getCasi(), Toast.LENGTH_SHORT).show();
                    imgluotthich.setImageResource(R.drawable.iconloved);*/

                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.Updateluotthich("1",mangbaihat.get(getPosition()).getIdBaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Lỗi !", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }

        /*private void createChannel() {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel(CreateNotificationAdapter.CHANNEL_ID,
                        "KOD Dev",NotificationManager.IMPORTANCE_LOW);

                //notificationManager = getSystemService(NotificationManager.class);
                if(notificationManager != null){
                    notificationManager.createNotificationChannel(channel);
                }
            }
        }*/
    }

}
