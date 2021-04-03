package com.example.caracample.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caracample.R;
import com.example.caracample.model.LedVO;
import com.example.caracample.model.PowerVO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControlActivity extends AppCompatActivity {

    TextView tv_car_name_admin;
    ImageView img_led_liv_admin;
    ImageView img_led_kit_admin;
    ImageView img_led_bath_admin;
    ImageView img_led_outdoor_admin;
    ImageView img_air_power_admin;
    ImageView img_sunroof_power_admin;

    String led_liv_status;
    String led_kit_status;
    String led_bath_status;
    String led_outdoor_status;
    String air_power_status;
    String sunroof_power_status;

    Button btn_all_on_admin;
    Button btn_all_off_admin;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://getdata-from-rapa-default-rtdb.firebaseio.com/");
    DatabaseReference ref = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_admin);

        btn_all_on_admin = findViewById(R.id.btn_all_on_admin);
        btn_all_off_admin = findViewById(R.id.btn_all_off_admin);

        String car_name = getIntent().getStringExtra("car_name");
        img_led_liv_admin = findViewById(R.id.img_led_liv_admin);
        img_led_kit_admin = findViewById(R.id.img_led_kit_admin);
        img_led_bath_admin = findViewById(R.id.img_led_bath_admin);
        img_led_outdoor_admin = findViewById(R.id.img_led_outdoor_admin);
        img_air_power_admin = findViewById(R.id.img_air_power_admin);
        img_sunroof_power_admin = findViewById(R.id.img_sunroof_power_admin);
        tv_car_name_admin = findViewById(R.id.tv_car_name_admin);
        tv_car_name_admin.setText(car_name);

        ref.child("cars_func").child("car_name").setValue(car_name.substring(0,8));

        ref.child("cars_func").child(car_name).child("led").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(LedVO.class) != null) {
                    LedVO ledVO_post = snapshot.getValue(LedVO.class);
                    led_liv_status = ledVO_post.getLiv();
                    if(led_liv_status.equals("off")){
                        img_led_liv_admin.setImageResource(R.drawable.led_liv_off);
                    }else if(led_liv_status.equals("on")){
                        img_led_liv_admin.setImageResource(R.drawable.led_liv_on);
                    }
                    led_kit_status = ledVO_post.getKit();
                    if(led_kit_status.equals("off")){
                        img_led_kit_admin.setImageResource(R.drawable.led_kit_off);
                    }else if(led_kit_status.equals("on")){
                        img_led_kit_admin.setImageResource(R.drawable.led_kit_on);
                    }
                    led_bath_status = ledVO_post.getBath();
                    if(led_bath_status.equals("off")){
                        img_led_bath_admin.setImageResource(R.drawable.led_bath_off);
                    }else if(led_bath_status.equals("on")){
                        img_led_bath_admin.setImageResource(R.drawable.led_bath_on);
                    }
                    led_outdoor_status = ledVO_post.getOutdoor();
                    if(led_outdoor_status.equals("off")){
                        img_led_outdoor_admin.setImageResource(R.drawable.led_light_off);
                    }else if(led_outdoor_status.equals("on")){
                        img_led_outdoor_admin.setImageResource(R.drawable.led_light_on);
                    }
                } else {
                    Toast.makeText(ControlActivity.this, "데이터 없음", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref.child("cars_func").child(car_name).child("power").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(PowerVO.class) != null){
                    PowerVO powerVO_post = snapshot.getValue(PowerVO.class);
                    air_power_status = powerVO_post.getAir();
                    if(air_power_status.equals("off")){
                        img_air_power_admin.setImageResource(R.drawable.air_power_off);
                    }else if(air_power_status.equals("on")){
                        img_air_power_admin.setImageResource(R.drawable.air_power_on);
                    }
                    sunroof_power_status = powerVO_post.getSunroof();
                    if(sunroof_power_status.equals("off")){
                        img_sunroof_power_admin.setImageResource(R.drawable.led_sunroof_off);
                    }else if(sunroof_power_status.equals("on")){
                        img_sunroof_power_admin.setImageResource(R.drawable.led_sunroof_on);
                    }
                }else{
                    Toast.makeText(ControlActivity.this, "데이터 없음", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        img_led_liv_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(led_liv_status.equals("off")){
                    led_liv_status = "on";
                }else if(led_liv_status.equals("on")){
                    led_liv_status = "off";
                }
                ref.child("cars_func").child(car_name).child("led").child("liv").setValue(led_liv_status);
            }
        });
        img_led_kit_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(led_kit_status.equals("off")){
                    led_kit_status = "on";
                }else if(led_kit_status.equals("on")){
                    led_kit_status = "off";
                }
                ref.child("cars_func").child(car_name).child("led").child("kit").setValue(led_kit_status);
            }
        });
        img_led_bath_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(led_bath_status.equals("off")){
                    led_bath_status = "on";
                }else if(led_bath_status.equals("on")){
                    led_bath_status = "off";
                }
                ref.child("cars_func").child(car_name).child("led").child("bath").setValue(led_bath_status);
            }
        });
        img_led_outdoor_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(led_outdoor_status.equals("off")){
                    led_outdoor_status = "on";
                }else if(led_outdoor_status.equals("on")){
                    led_outdoor_status = "off";
                }
                ref.child("cars_func").child(car_name).child("led").child("outdoor").setValue(led_outdoor_status);
            }
        });
        img_air_power_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(air_power_status.equals("off")){
                    air_power_status = "on";
                }else if(air_power_status.equals("on")){
                    air_power_status = "off";
                }
                ref.child("cars_func").child(car_name).child("power").child("air").setValue(air_power_status);
            }
        });
        img_sunroof_power_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sunroof_power_status.equals("off")){
                    sunroof_power_status = "on";
                }else if(sunroof_power_status.equals("on")){
                    sunroof_power_status = "off";
                }
                ref.child("cars_func").child(car_name).child("power").child("sunroof").setValue(sunroof_power_status);
            }
        });

        btn_all_off_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("cars_func").child(car_name).child("led").child("bath").setValue("off");
                ref.child("cars_func").child(car_name).child("led").child("kit").setValue("off");
                ref.child("cars_func").child(car_name).child("led").child("liv").setValue("off");
                ref.child("cars_func").child(car_name).child("led").child("outdoor").setValue("off");
                ref.child("cars_func").child(car_name).child("power").child("air").setValue("off");
                ref.child("cars_func").child(car_name).child("power").child("sunroof").setValue("off");

            }
        });
        btn_all_on_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("cars_func").child(car_name).child("led").child("bath").setValue("on");
                ref.child("cars_func").child(car_name).child("led").child("kit").setValue("on");
                ref.child("cars_func").child(car_name).child("led").child("liv").setValue("on");
                ref.child("cars_func").child(car_name).child("led").child("outdoor").setValue("on");
                ref.child("cars_func").child(car_name).child("power").child("air").setValue("on");
                ref.child("cars_func").child(car_name).child("power").child("sunroof").setValue("on");

            }
        });

    }
}