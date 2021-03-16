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

    String ip = "192.168.35.251";
    ImageView[] imgs = new ImageView[9];
    RequestQueue requestQueue; // 데이터가 전송되는 통로
    StringRequest stringRequest; // 내가 보낼 데이터
    Button btn_fire_admin;
    boolean check = false;

    static final int SMS_SEND_PERMISSON=1;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://getdata-from-rapa-default-rtdb.firebaseio.com/");
    DatabaseReference ref = database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(getActivity());
        View fragment = inflater.inflate(R.layout.fragment_map_admin, container, false);

        btn_fire_admin = fragment.findViewById(R.id.btn_fire_admin);
        for (int i = 0; i < imgs.length; i++) {
            final int temp = i;
            int imgID = getResources().getIdentifier("img_admin" + (i + 1), "id", getActivity().getPackageName());
            imgs[i] = fragment.findViewById(imgID);
            imgs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String car_name = "Caravan"+(char)(temp+65);
                    Intent intent = new Intent(getActivity(), ControlActivity_Admin.class);
                    intent.putExtra("car_name",car_name);
                    startActivity(intent);
                }
            });
        }

        String url = "http://"+ip+":8081/ThirdProject/ableSelect.do";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    if(array.length() != 0){
                        for(int i = 0; i < array.length(); i++){
                            String car_name = array.get(i).toString();
                            if(car_name.equals("CaravanA")){
                                check = true;
                            }
                            char c = car_name.charAt(7);
                            int num = (int) c - 64;
                            imgs[num - 1].setImageResource(R.drawable.car_name_on);
                            char c_mini = (char)(c+32);
                            GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imgs[num-1]); //gif를 가져오기 위한 소스
                            int myID = getResources().getIdentifier("caravan"+c_mini+"gif","drawable",getActivity().getPackageName());
                            Glide.with(getActivity()).load(myID).into(gifImage);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                return data;
            }
        };

        stringRequest.setTag("MAIN");
        requestQueue.add(stringRequest);

        btn_fire_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FireActivity.class);
                startActivity(intent);
            }
        });
        ref.child("cars_func").child("CaravanA").child("power").child("fire").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().equals("on")){
                    GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imgs[0]); //gif를 가져오기 위한 소스
                    Glide.with(getActivity()).load(R.drawable.fire_car_name).into(gifImage);
                }else{
                    if(check){
                        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imgs[0]); //gif를 가져오기 위한 소스
                        Glide.with(getActivity()).load(R.drawable.caravanagif).into(gifImage);
                    }else{

                        imgs[0].setImageResource(R.drawable.caravana);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return fragment;
    }
}