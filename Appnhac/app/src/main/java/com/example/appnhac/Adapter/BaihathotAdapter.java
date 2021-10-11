package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.PlayNhacActivity;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.appnhac.Activity.PlayNhacActivity.mediaPlayer;

public class BaihathotAdapter extends  RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> mangbaihat;

    public BaihathotAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*holder.imghinh.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));*/

        holder.txttenbaihat.setAnimation(AnimationUtils.loadAnimation(context,R.anim.layout_animation_right));
        holder.txtcasi.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.txtindex.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

        holder.imghinh.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fall_down));
        holder.imgluotthich.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

        Baihat baihat = mangbaihat.get(position);

        holder.txtcasi.setText(baihat.getCasi());
        holder.txttenbaihat.setText(baihat.getTenbaihat());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imghinh);
        holder.txtindex.setText(position + 1 + "");
        holder.txtluotthich.setText(baihat.getLuotthich());
    }

    @Override
    public int getItemCount() {

        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtindex,txttenbaihat,txtcasi,txtluotthich;
        ImageView imgluotthich, imghinh;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewtencasibaihathot);
            txtindex = itemView.findViewById(R.id.textviewbaihathotindex);
            txttenbaihat = itemView.findViewById(R.id.textviewtenbaihathot);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthichbaihathot);
            imghinh = itemView.findViewById(R.id.imageviewbaihathot);
            cardView = itemView.findViewById(R.id.cardviewbaihathot);
            txtluotthich = itemView.findViewById(R.id.luotthichbaihathot);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    Toast.makeText(context, "Đang phát: "+mangbaihat.get(getPosition()).getTenbaihat() + " - "+mangbaihat.get(getPosition()).getCasi(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });

            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Toast.makeText(context, "Bạn đã thích ca khúc: " + mangbaihat.get(getPosition()).getTenbaihat() + " - " + mangbaihat.get(getPosition()).getCasi(), Toast.LENGTH_SHORT).show();
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    txtluotthich.setText(mangbaihat.get(getPosition()).getLuotthich() + 1 + "");*/

                    //Toast.makeText(context, mangbaihat.get(getPosition()).getTenbaihat(), Toast.LENGTH_SHORT).show();

                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.Updateluotthich("1",mangbaihat.get(getPosition()).getIdBaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
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
    }
}
