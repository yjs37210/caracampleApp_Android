package com.example.caracample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerFragment extends Fragment {

    String ip = "192.168.35.251";

    ListView lv;
    ArrayList<CustomerVO> list;
    CustomerAdapter adapter;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_customer, container, false);

        requestQueue = Volley.newRequestQueue(getActivity());
        lv = fragment.findViewById(R.id.lv_customer);

        String url = "http://"+ip+":8081/ThirdProject/customerSelect.do";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    list = new ArrayList<CustomerVO>();
                    for(int i = 0; i < array.length(); i++){
                        list.add(new CustomerVO(
                                Integer.parseInt(array.getJSONObject(i).getString("num")),
                                array.getJSONObject(i).getString("name"),
                                array.getJSONObject(i).getString("tel"),
                                array.getJSONObject(i).getString("car_name"),
                                Integer.parseInt(array.getJSONObject(i).getString("male")),
                                Integer.parseInt(array.getJSONObject(i).getString("female")),
                                array.getJSONObject(i).getString("check_in"),
                                array.getJSONObject(i).getString("check_out")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter = new CustomerAdapter(getActivity(),R.layout.infolist,list);
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