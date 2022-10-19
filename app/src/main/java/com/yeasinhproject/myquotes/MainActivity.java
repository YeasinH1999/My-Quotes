package com.yeasinhproject.myquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CircularProgressIndicator progressBar_circular;
    String Url = "https://dummyjson.com/quotes";
    MyAdapter myAdapter;
    List<MyQuotes> myQuotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar_circular = findViewById(R.id.progressBar_circular);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myQuotesList = new ArrayList<>();

        loadServerData();


    } // OnCreate Method Ends Here -----------------------------------------------------------------


    public void loadServerData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar_circular.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray = response.getJSONArray("quotes");

                    for (int x=0; x<jsonArray.length(); x++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(x);

                        MyQuotes myQuotes = new MyQuotes(

                                jsonObject.getString("quote"),
                                jsonObject.getString("author")
                        );

                        myQuotesList.add(myQuotes);

                        myAdapter = new MyAdapter(myQuotesList, getApplicationContext());
                        recyclerView.setAdapter(myAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);

    }


    //**********************************************************************************************

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        builder.setTitle("Confirm Exit!")
                .setIcon(R.drawable.warning)
                .setMessage("Do you really want to exit?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        finishAndRemoveTask();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    //----------------------------------------------------------------------------------------------


} // MainActivity Ends Here ------------------------------------------------------------------------