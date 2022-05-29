package com.example.berikabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berikabar.entity.Berita;
import com.example.berikabar.entity.BeritaResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;
    EditText etSearch;
    AppCompatButton btnSearchBerita;
    String API_KEY = "c9eed5fb17234878b2bb48e51cd1a6db";
    String country = "id";
    BeritaAdapter adapter;
    List<Berita> beritaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);
        btnSearchBerita = findViewById(R.id.buttonSend);


        etSearch = findViewById(R.id.etSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBeritaAll("",country,API_KEY);
            }
        });
        getBeritaAll("",country,API_KEY);

        btnSearchBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etSearch.getText().toString().equals("")){
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            getBeritaAll(etSearch.getText().toString(),country,API_KEY);
                        }
                    });
                    getBeritaAll(etSearch.getText().toString(),country,API_KEY);
                }else{
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            getBeritaAll("",country,API_KEY);
                        }
                    });
                    getBeritaAll("",country,API_KEY);
                }
            }
        });

    }

    public void getBeritaAll(String query ,String country, String apiKey){
        swipeRefreshLayout.setRefreshing(true);
        Call<BeritaResponse> call;
        if (!etSearch.getText().toString().equals("")){
            call= BeritaClientAPI.getInstance().getAPI().getSearchBerita(query,apiKey);
        }else{
            call= BeritaClientAPI.getInstance().getAPI().getHeadlinesBerita(country,apiKey);
        }

        call.enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    swipeRefreshLayout.setRefreshing(false);
                    beritaList.clear();
                    beritaList = response.body().getArticles();
                    adapter = new BeritaAdapter(MainActivity.this,beritaList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
