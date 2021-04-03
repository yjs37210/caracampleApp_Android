package com.example.caracample.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.caracample.R;
import com.example.caracample.model.QnAVO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QnAAdapter extends BaseAdapter {

    String ip = "192.168.35.251";

    private Context co;
    private int layout;
    private ArrayList<QnAVO> data;
    private LayoutInflater inflater;

    private int num;
    private String time;

    private BottomNavigationView navi;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    public QnAAdapter(Context co, int layout, ArrayList<QnAVO> data) {
        this.co = co;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) co.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(layout,parent,false);
        }
        
        requestQueue = Volley.newRequestQueue(co);
        String url = "http://"+ip+":8081/ThirdProject/qnAUpdate.do";

        TextView tv_qna_num = convertView.findViewById(R.id.tv_qna_num);
        TextView tv_qna_time = convertView.findViewById(R.id.tv_qna_time);
        TextView tv_qna_car_name = convertView.findViewById(R.id.tv_qna_car_name);
        TextView tv_qna_cmt = convertView.findViewById(R.id.tv_qna_cmt);
        Button btn_qna_complete = convertView.findViewById(R.id.btn_qna_complete);
        Button btn_qna_sms = convertView.findViewById(R.id.btn_qna_sms);
        Button btn_qna_tel = convertView.findViewById(R.id.btn_qna_tel);
        navi = convertView.findViewById(R.id.bottomNavigationView2);

        tv_qna_num.setText(data.get(position).getNum()+"번 손님");
        tv_qna_time.setText(data.get(position).getTime());
        tv_qna_car_name.setText(data.get(position).getCar_name());
        tv_qna_cmt.setText(data.get(position).getCmt());

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("num",num+"");
                data.put("time",time);
                return data;
            }
        };

        stringRequest.setTag("Update");
        
        
        btn_qna_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+data.get(position).getTel()));
                co.startActivity(intent);
            }
        });

        btn_qna_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:"+data.get(position).getTel()));
                co.startActivity(intent);
            }
        });
        
        btn_qna_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = data.get(position).getNum();
                time = data.get(position).getTime();
                AlertDialog.Builder msgBuilder = new AlertDialog.Builder(co).setTitle(
                        data.get(position).getCar_name()+"의 요청 : "+data.get(position).getCmt()).setMessage("처리를 완료하시겠습니까?"
                ).setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestQueue.add(stringRequest);
                        data.remove(position);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog msgDlg = msgBuilder.create();
                msgDlg.show();
            }
        });

        return convertView;
    }

}
