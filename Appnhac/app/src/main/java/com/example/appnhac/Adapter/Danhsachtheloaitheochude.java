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
import com.example.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Danhsachtheloaitheochude extends RecyclerView.Adapter<Danhsachtheloaitheochude.ViewHolder> {
    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public Danhsachtheloaitheochude(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_the_loai_theo_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        TheLoai theLoai = theLoaiArrayList.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imghinhnen);
        holder.txttentheloai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView txttentheloai;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewtheloaitheochude);
            txttentheloai = itemView.findViewById(R.id.textviewtheloaitheochude);
            cardView = itemView.findViewById(R.id.cardviewtheloaitheoalbum);

            imghinhnen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatMainActivity.class);
                    intent.putExtra("idtheloai",theLoaiArrayList.get(getPosition()));
                    Toast.makeText(context, "Thể loại: "+theLoaiArrayList.get(getPosition()).getTenTheLoai(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });
        }
    }
}
