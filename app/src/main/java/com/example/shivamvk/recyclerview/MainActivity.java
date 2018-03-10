package com.example.shivamvk.recyclerview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ListItem> listItems;
    private MyAdapter myAdapter;
    private static final String REQUEST_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=3d5998d023614120acefd255e7017c2a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        loadRecyclerViewData();

    }

    public void loadRecyclerViewData(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject baseobject = new JSONObject(response);
                            JSONArray articles = baseobject.getJSONArray("articles");

                            for (int i=0; i<articles.length(); i++){
                                JSONObject jsonObject = articles.getJSONObject(i);
                                ListItem listItem = new ListItem(jsonObject.getString("title"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("urlToImage"),
                                        jsonObject.getString("url"));

                                progressDialog.dismiss();
                                listItems.add(listItem);
                            }

                            MyAdapter myAdapter = new MyAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(myAdapter);

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
