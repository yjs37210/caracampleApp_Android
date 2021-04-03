package com.example.caracample.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.caracample.activity.ControlActivity;
import com.example.caracample.activity.FireActivity;
import com.example.caracample.R;

public class MapFragment extends Fragment {

    ImageView[] imgs = new ImageView[9];
    Button btn_fire_admin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_map_admin, container, false);

        btn_fire_admin = fragment.findViewById(R.id.btn_fire_admin);

        for (int i = 0; i < imgs.length; i++) {

            final int temp = i;
            int imgID = getResources().getIdentifier("img_admin" + (i + 1), "id", getActivity().getPackageName());

            imgs[i] = fragment.findViewById(imgID);
            imgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String car_name = "Caravan" + (char) (temp + 65);
                    Intent intent = new Intent(getActivity(), ControlActivity.class);
                    intent.putExtra("car_name", car_name);
                    startActivity(intent);
                }
            });

        }

        btn_fire_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), FireActivity.class);
                startActivity(intent);

            }
        });

        return fragment;
    }
}