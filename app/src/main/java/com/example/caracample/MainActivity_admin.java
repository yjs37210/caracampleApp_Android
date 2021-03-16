package com.example.caracample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_admin extends AppCompatActivity {

    BottomNavigationView navi;
    Fragment fragment_map_admin;
    Fragment fragment_customer;
    Fragment fragment_qna_admin;
    Button btn_insert;
    TextView tv_qna_sum;

    private Intent intent;
    private Menu menu;
    static final int SMS_SEND_PERMISSON=1;

    @Override
    public void onBackPressed() {
        if(isServiceRunning(MyService.class.getName())){
            stopService(intent);
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        checkSmsPermission();

        fragment_map_admin = new MapFragment_Admin();
        fragment_customer = new CustomerFragment();
        fragment_qna_admin = new QnAFragment_Admin();
        navi = findViewById(R.id.bottomNavigationView2);
        btn_insert = findViewById(R.id.btn_insert);
        tv_qna_sum = findViewById(R.id.tv_qna_num);

        intent = new Intent(this, MyService.class);
        intent.putExtra("admin",1);
        startService(intent);
        android.util.Log.i("Start Service", "StartService()");

        getSupportFragmentManager().beginTransaction().replace(R.id.container_admin, fragment_map_admin).commit();
        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_map_admin:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_admin, fragment_map_admin).commit();
                        break;
                    case R.id.tab_customer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_admin, fragment_customer).commit();
                        break;
                    case R.id.tab_qna_admin:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_admin, fragment_qna_admin).commit();
                        break;
                }
                return true;
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_admin.this, InsertActivity.class);
                startActivity(intent);
            }
        });


    }

    void checkSmsPermission(){
        int permissonCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
                Toast.makeText(getApplicationContext(), "SMS권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSON);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSON);
            }
        }
    }

    public Boolean isServiceRunning(String class_name) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (class_name.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }

}