package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Adapter.BaihathotAdapter;
import com.example.appnhac.R;

public class Fragment_Lien_He extends Fragment {
    View view;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lien_he,container,false);
        anhxa();
        return view;
    }

    private void anhxa() {
        imageView = view.findViewById(R.id.imagelienhe);
    }

}
