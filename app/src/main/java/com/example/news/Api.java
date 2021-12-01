package com.example.news;

import com.example.news.Models.Everything;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    /*HTTP OPERATIONS*/

    // everything is the endpoint we are getting the data from
     @GET("everything")
    Call<Everything> getEverything(
            @Query("q")String q,
            @Query("from")String from,
            @Query("sortBy")String sortBy,
            @Query("apiKey")String apiKey
     );
}
