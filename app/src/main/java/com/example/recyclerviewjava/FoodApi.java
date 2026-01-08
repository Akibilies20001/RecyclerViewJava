package com.example.recyclerviewjava;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodApi {

    @GET("categories.php")
    Call<Category>getCategories();
}
