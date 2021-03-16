
package com.example.caracample;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerAdapter extends BaseAdapter {

    private Context co;
    private int layout;
    private ArrayList<CustomerVO> data;
    private LayoutInflater inflater;

    public CustomerAdapter(Context co, int layout, ArrayList<CustomerVO> data) {
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

        TextView num = convertView.findViewById(R.id.tv_info_num);
        TextView name = convertView.findViewById(R.id.tv_info_name);
        TextView tel = convertView.findViewById(R.id.tv_info_tel);
        TextView car_name = convertView.findViewById(R.id.tv_info_car_name);
        TextView male = convertView.findViewById(R.id.tv_info_male);
        TextView female = convertView.findViewById(R.id.tv_info_female);
        TextView check_in = convertView.findViewById(R.id.tv_info_check_in);
        TextView check_out = convertView.findViewById(R.id.tv_info_check_out);

        num.setText("번호 : "+data.get(position).getNum()+"");
        name.setText("이름 : "+data.get(position).getName());
        tel.setText("전화번호 : "+data.get(position).getTel());
        car_name.setText("카라반 이름 : "+data.get(position).getCar_name());
        male.setText("남성 수 : "+data.get(position).getMale()+"");
        female.setText("여성 수 : "+data.get(position).getFemale()+"");
        check_in.setText("입실 시간 : "+data.get(position).getCheck_in());
        check_out.setText("퇴실 시간 : "+data.get(position).getCheck_out());

        return convertView;
    }

}
