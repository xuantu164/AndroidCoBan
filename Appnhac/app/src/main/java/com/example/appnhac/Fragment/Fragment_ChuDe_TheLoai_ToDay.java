package com.example.appnhac.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appnhac.Activity.DanhsachbaihatMainActivity;
import com.example.appnhac.Activity.DanhsachtatcachudeActivity;
import com.example.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.ChuDeVaTheLoai;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);
        anhxa();
        GetData();
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void anhxa() {
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthem);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChuDeVaTheLoai> callback = dataservice.GetDataChuDeVaTheLoai();
        callback.enqueue(new Callback<ChuDeVaTheLoai>() {
            @Override
            public void onResponse(Call<ChuDeVaTheLoai> call, Response<ChuDeVaTheLoai> response) {
                ChuDeVaTheLoai chuDeVaTheLoai = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chuDeVaTheLoai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chuDeVaTheLoai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(30,20,20,30);

                for(int i = 0; i < (chuDeArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    //TextView textView = new TextView(getActivity());

                    if(chuDeArrayList.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                        //textView.setText(chuDeArrayList.get(i).getTenChuDe());
                        //textView.setTextColor(Color.WHITE);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    //cardView.addView(textView);
                    linearLayout.addView(cardView);

                    imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.slide_bottom));

                    cardView.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.layout_animation_right));

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                /*for(int j = 0; j < (theLoaiArrayList.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    TextView textView = new TextView(getActivity());

                    if(theLoaiArrayList.get(j).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                        textView.setText(chuDeArrayList.get(j).getTenChuDe());
                        textView.setTextColor(Color.WHITE);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }*/
                horizontalScrollView.addView(linearLayout);

                Log.d("TEST",chuDeVaTheLoai.getTheLoai().get(0).getTenTheLoai());
            }

            @Override
            public void onFailure(Call<ChuDeVaTheLoai> call, Throwable t) {
                Log.d("chudevatheloai",t.getMessage());
            }
        });
    }


}
