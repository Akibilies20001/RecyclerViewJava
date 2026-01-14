package com.example.recyclerviewjava.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewjava.domain.models.CategoryList;

import java.util.List;

public interface CategoryRepository
{

    MutableLiveData<List<CategoryList>> getCategories();
//    List<CategoryList> getCategories();

}
