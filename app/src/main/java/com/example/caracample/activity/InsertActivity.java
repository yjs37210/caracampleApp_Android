package com.example.caracample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.caracample.R;
import com.example.caracample.service.MyService;

import java.util.HashMap;
import java.util.Map;

public class InsertActivity extends AppCompatActivity {

    String ip = "192.168.35.251";
    Spinner spinner_car_name;
    EditText input_name;
    EditText input_tel;
    EditText input_male;
    EditText input_female;
    Button btn_customer;

    RequestQueue requestQueue; // 데이터가 전송되는 통로
    StringRequest stringRequest; // 내가 보낼 데이터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        requestQueue = Volley.newRequestQueue(this);

        spinner_car_name = findViewById(R.id.spinner_car_name);
        input_name = findViewById(R.id.input_name);
        input_tel = findViewById(R.id.input_tel);
        input_male = findViewById(R.id.input_male);
        input_female = findViewById(R.id.input_female);
        btn_customer = findViewById(R.id.btn_customer);

        ArrayAdapter car_name_adapter = ArrayAdapter.createFromResource(this, R.array.car_name_array,android.R.layout.simple_spinner_dropdown_item);
        car_name_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_car_name.setAdapter(car_name_adapter);

        String url = "http://"+ip+":8081/ThirdProject/customerInsert.do";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("")){
                    Toast.makeText(InsertActivity.this, "이미 사용중인 카라반입니다. 다른 카라반을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    String msg = "카라캠플 어플을 다운로드하시면, 카라반을 이용하실 수 있습니다! (아이디 : "+spinner_car_name.getSelectedItem().toString()+" / 비밀번호 : "+response.toString()+")";

                    try{
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(input_tel.getText().toString(),null,msg,null,null);
                        Toast.makeText(InsertActivity.this, "문자가 전송되었습니다.", Toast.LENGTH_SHORT).show();
                        if (isServiceRunning(MyService.class.getName())) {
                            Intent intent = new Intent(getApplicationContext(), MyService.class);
                            stopService(intent);
                        }
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }catch(Exception e){
                        Toast.makeText(InsertActivity.this, "전송실패", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InsertActivity.this, "오류 발생", Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("name",input_name.getText().toString());
                data.put("tel",input_tel.getText().toString());
                data.put("car_name",spinner_car_name.getSelectedItem().toString());
                data.put("male",input_male.getText().toString());
                data.put("female",input_female.getText().toString());
                return data;
            }
        };

        stringRequest.setTag("MAIN");

        btn_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!input_name.getText().toString().equals("")&&!input_tel.getText().toString().equals("")&&!input_male.getText().toString().equals("")&&!input_female.getText().toString().equals("")){
                    requestQueue.add(stringRequest);
                }else{
                    Toast.makeText(InsertActivity.this, "입력이 안된 문항이 존재합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}