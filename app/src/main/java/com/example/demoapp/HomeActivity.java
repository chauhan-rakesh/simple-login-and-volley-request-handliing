package com.example.demoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<Car> carList;



    RequestQueue rq;

    String request_url = "http://www.mocky.io/v2/5d15fc1b0e00003311a1167a";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences = getSharedPreferences("Detail", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username",null);
//        getActionBar().setTitle(username);
        getSupportActionBar().setTitle(username);
        rq = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        carList = new ArrayList<>();

        sendRequest();
    }

    public void sendRequest(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    Car cr = new Car();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        cr.setProductName(jsonObject.getString("product_name"));
                        cr.setProductDesc(jsonObject.getString("product_desc"));
                        cr.setProductUrl(jsonObject.getString("product_image"));
//

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    carList.add(cr);

                }

                mAdapter = new CustomRecyclerAdapter(HomeActivity.this, carList);

                recyclerView.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                int i = Log.i("Volley Error: ", error);
            }
        });

        rq.add(jsonArrayRequest);

    }
}
