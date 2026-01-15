package com.example.recyclerviewjava.presentation.activities;

import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewjava.R;
import com.example.recyclerviewjava.data.FoodApi;
import com.example.recyclerviewjava.domain.models.Category;
import com.example.recyclerviewjava.domain.models.CategoryList;
import com.example.recyclerviewjava.presentation.adapters.CategoryAdapter;
import com.example.recyclerviewjava.presentation.viewmodels.MainViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    MainViewModel vm;
    //Toolbar toolbar;
    RecyclerView recyclerView;


    TextInputEditText textInputEditText;
    ImageButton imageButton;


   // ProgressBar progressBar;
    List<CategoryList> categories = new ArrayList<>(); //without initialization gives null exception
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //progressBar = findViewById(R.id.progress_bar);

        vm = new ViewModelProvider(this).get(MainViewModel.class);

        recyclerView = findViewById(R.id.vertical_View);
       // toolbar = findViewById(R.id.main_activity_toolbar);
        // setSupportActionBar(toolbar); //??

        textInputEditText = findViewById(R.id.main_text_input);
        imageButton = findViewById(R.id.search_btn);



        categoryAdapter = new CategoryAdapter(categories, MainActivity.this);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vm.getCategoryListFromApi();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputEditText.getVisibility() == View.VISIBLE) {
                    imageButton.setImageResource(R.drawable.search_icon);
                    textInputEditText.setVisibility(View.INVISIBLE);
                    textInputEditText.setText("");
                    categoryAdapter.getFilter().filter("");
                }else {
                    imageButton.setImageResource(R.drawable.close_icon);
                    textInputEditText.setVisibility(View.VISIBLE);
                }

            }
        });


        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                categoryAdapter.getFilter().filter(s.toString());


            }
        });



        vm.getCategoryList().observe(this, new Observer<List<CategoryList>>() {
            @Override
            public void onChanged(List<CategoryList> categoryLists) {
                if(!categoryLists.isEmpty()){
//                    categoryAdapter = new CategoryAdapter(categoryLists, MainActivity.this);
//                    recyclerView.setAdapter(categoryAdapter);
                    categories.addAll(categoryLists);
                    categoryAdapter.setCategoryList(categories);
                    categoryAdapter.notifyDataSetChanged();
                    //progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Log.d("Category in livedata", ""+categoryLists);
                }

            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {//??
//
//        MenuInflater inflater = getMenuInflater() ;
//        inflater.inflate(R.menu.search_menu,menu);
//        MenuItem item = menu.findItem(R.id.search_action);
//        SearchView searchView = (SearchView) item.getActionView();//??
//        searchView.setOnQueryTextListener(
//                new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String query) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String newText) {
//
//                        filterCategories(newText);
//
//                        return false;
//                    }
//                }
//        );
//
//
//        return true;
//    }

//    private void filterCategories(String text) {
//        if (text.isBlank()){
//
//            categoryAdapter.setCategoryList(categories);
//
//        }else {
//
//            List<CategoryList> data = new ArrayList<>();
//
//            for(int position = 0; position<categories.size(); position++){
//
//                CategoryList category = categories.get(position);
//
//                if (category.getStrCategory().toLowerCase().contains(text.toLowerCase())){
//                    data.add(category);
//                }
//
//            }
//            categoryAdapter.setCategoryList(data);
//
//        }
//
//
//
//
//    }

    private void getCategories() {

//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        retrofit.create(FoodApi.class).getCategories().enqueue(new Callback<Category>() {
//            @Override
//            public void onResponse(Call<Category> call, Response<Category> response) {
//                if(response.isSuccessful()){
//                    categories.addAll(response.body().getCategories()) ;
//                    Log.d("ApiBody", response.body().getCategories().size()+"");
//                    categoryAdapter.notifyDataSetChanged(); // notifies adapter about data update
//                    Log.d("Api", categories.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Category> call, Throwable throwable) {
//                Log.d("Api", throwable.getMessage());
//
//            }
//        });









    }
}