package com.example.recyclerviewjava;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<CategoryList> categories = new ArrayList<>(); //without initialization gives null exception
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); // padding like scaffold

        RecyclerView recyclerView = findViewById(R.id.vertical_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getCategories();

        categoryAdapter = new CategoryAdapter(categories, this);
        recyclerView.setAdapter(categoryAdapter);






    }

    private void getCategories() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(FoodApi.class).getCategories().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){
                    categories.addAll(response.body().getCategories()) ;
                    Log.d("ApiBody", response.body().getCategories().size()+"");
                    categoryAdapter.notifyDataSetChanged(); // notifies adapter about data update
                    Log.d("Api", categories.toString());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable throwable) {
                Log.d("Api", throwable.getMessage());

            }
        });









    }
}