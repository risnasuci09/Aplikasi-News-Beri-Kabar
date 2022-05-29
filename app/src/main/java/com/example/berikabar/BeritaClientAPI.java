package com.example.berikabar;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeritaClientAPI {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static BeritaClientAPI beritaClientAPI;
    private static Retrofit retrofit;

    private BeritaClientAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized BeritaClientAPI getInstance(){
        if (beritaClientAPI == null){
            beritaClientAPI = new BeritaClientAPI();
        }
        return beritaClientAPI;
    }

    public BeritaAPI getAPI(){
        return retrofit.create(BeritaAPI.class);
    }
}
