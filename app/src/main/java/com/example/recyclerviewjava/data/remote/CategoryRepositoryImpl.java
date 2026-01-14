package com.example.recyclerviewjava.data.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewjava.data.FoodApi;
import com.example.recyclerviewjava.domain.CategoryRepository;
import com.example.recyclerviewjava.domain.models.Category;
import com.example.recyclerviewjava.domain.models.CategoryList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public MutableLiveData<List<CategoryList>> getCategories() {
//        public List<CategoryList> getCategories() {
        MutableLiveData<List<CategoryList>> data = new MutableLiveData<>();
//        List<CategoryList> data = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(FoodApi.class).getCategories().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful() && response.body()!= null){

                        data.postValue(response.body().getCategories());
                        Log.d("ApiBody", response.body().getCategories()+"");

                    //categoryAdapter.notifyDataSetChanged(); // notifies adapter about data update
                    //Log.d("Api", categories.toString());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable throwable) {
                Log.d("Api", throwable.getMessage());

            }
        });




        return data;
    }
}
