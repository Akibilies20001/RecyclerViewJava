package com.example.recyclerviewjava.domain.models;


import androidx.annotation.NonNull;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Category {

    @SerializedName("categories")
    @Expose
    private List<CategoryList> categories;

    public List<CategoryList> getCategories() {
        return categories;
    }

    @NonNull
    @Override
    public String toString() {
        return ""+categories;
    }

    public void setCategories(List<CategoryList> categories) {
        this.categories = categories;
    }

}