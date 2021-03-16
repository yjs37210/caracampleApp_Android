package com.example.caracample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QnAFragment_Admin extends Fragment {

    String ip = "192.168.35.251";
    ListView lv;
    ArrayList<QnAVO> list;
    QnAAdapter adapter;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(getActivity());
        View fragment = inflater.inflate(R.layout.fragment_qn_a__admin, container, false);
        lv = fragment.findViewById(R.id.lv);

        String url = "http://"+ip+":8081/ThirdProject/qnASelect.do";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    list = new ArrayList<QnAVO>();
                    for(int i = 0; i < array.length(); i++){
                        list.add(new QnAVO(Integer.parseInt(array.getJSONObject(i).getString("num")),
                                array.getJSONObject(i).getString("time"),
                                array.getJSONObject(i).getString("car_name"),
                                array.getJSONObject(i).getString("cmt"),
                                array.getJSONObject(i).getString("tel")));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new QnAAdapter(getActivity(),R.layout.qnalist,list);
                lv.setAdapter(adapter);

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

        stringRequest.setTag("Select");
        requestQueue.add(stringRequest);

        return fragment;
    }
}