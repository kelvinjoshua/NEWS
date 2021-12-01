package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.news.Models.Articles;
import com.example.news.Models.Everything;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    final String API_KEY = "1d204698fd294d218c1307d02334598d";
    final String q = "tesla";
    final String from = "2021-10-01";
    final String sortBy = "publishedAt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrieveJson(q,from,sortBy,API_KEY);
    }
    //String q,
    //            @Query("from")String from,
    //            @Query("sortBy")String sortBy,
    //            @Query("apiKey")String apiKey
    public void retrieveJson(String q,String from,String sortBy,String apiKey){
        Call<Everything> call = ApiClient.getInstance().getApi().getEverything(q,from,sortBy,apiKey);
        call.enqueue(new Callback<Everything>() {
            @Override
            public void onResponse(Call<Everything> call, Response<Everything> response) {
                if(response.isSuccessful() && response.body().getArticlesList() != null){
                    articles.clear();
                    articles = response.body().getArticlesList();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Everything> call, Throwable t) {

            }
        });
    }

    /*
    public String  getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return  country.toLowerCase();
    }
    */

}