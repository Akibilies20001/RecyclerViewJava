package com.example.recyclerviewjava.data;

import com.example.recyclerviewjava.domain.models.Category;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodApi {

    @GET("categories.php")
    Call<Category>getCategories();
}
