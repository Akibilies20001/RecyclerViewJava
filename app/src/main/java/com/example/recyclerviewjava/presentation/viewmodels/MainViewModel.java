package com.example.recyclerviewjava.presentation.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclerviewjava.data.remote.CategoryRepositoryImpl;
import com.example.recyclerviewjava.domain.CategoryRepository;
import com.example.recyclerviewjava.domain.models.CategoryList;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private CategoryRepository caller = new CategoryRepositoryImpl();;
    MutableLiveData<List<CategoryList>> categoryList = new MutableLiveData<>();
    public MutableLiveData<List<CategoryList>> getCategoryList() {
        return categoryList;
    }

    MainViewModel(){
//        caller =
//        categoryList = new MutableLiveData<List<CategoryList>>();

//        getCategoryListFromApi();
    }

    public void getCategoryListFromApi() {
//        MutableLiveData<List<Catego
//        ryList>> data = caller.getCategories();
        categoryList = caller.getCategories();
    }




}
