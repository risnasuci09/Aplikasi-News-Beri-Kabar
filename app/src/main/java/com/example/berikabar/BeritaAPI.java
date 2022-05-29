package com.example.berikabar;

import com.example.berikabar.entity.BeritaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeritaAPI {

    @GET("top-headlines")
    Call<BeritaResponse> getHeadlinesBerita(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<BeritaResponse> getSearchBerita(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
}
