package com.example.caracample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MapFragment_Admin extends Fragment {

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
                    Intent intent = new Intent(getActivity(), ControlActivity_Admin.class);
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