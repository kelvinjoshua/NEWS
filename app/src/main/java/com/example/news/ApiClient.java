package com.example.news;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //makes the request
    private static final String BASE_URL = "https://newsapi.org/v2/";//will be appended on the interface's endpoint in our @GET("baseurl/endpointurl);
    private static  ApiClient apiClient;
    private static Retrofit retrofit;
    private ApiClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized  ApiClient getInstance(){
        if(apiClient == null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
