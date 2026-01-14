package com.example.recyclerviewjava.presentation.activities;

import static androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL;
import static androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
import androidx.viewpager2.widget.ViewPager2;

import com.example.recyclerviewjava.R;
import com.example.recyclerviewjava.data.FoodApi;
import com.example.recyclerviewjava.domain.models.Category;
import com.example.recyclerviewjava.domain.models.CategoryList;
import com.example.recyclerviewjava.presentation.adapters.CategoryAdapter;
import com.example.recyclerviewjava.presentation.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    MainViewModel vm;
    Toolbar toolbar;
    RecyclerView recyclerView;

    ViewPager2 viewPager;
   // ImageButton imageButton;


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
        //imageButton = findViewById(R.id.orientation_btn);


        viewPager = findViewById(R.id.view_pager);
        //recyclerView = findViewById(R.id.vertical_View);
        toolbar = findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar); //??
        categoryAdapter = new CategoryAdapter(categories, MainActivity.this);

        viewPager.setAdapter(categoryAdapter);
        viewPager.setOrientation(ORIENTATION_VERTICAL);

//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(viewPager.getOrientation() == ORIENTATION_VERTICAL ){
//                    viewPager.setOrientation(ORIENTATION_HORIZONTAL);
//
//                }else {
//                    viewPager.setOrientation(ORIENTATION_VERTICAL);
//                }
//
//
//            }
//        });

//        recyclerView.setAdapter(categoryAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vm.getCategoryListFromApi();




        vm.getCategoryList().observe(this, new Observer<List<CategoryList>>() {
            @Override
            public void onChanged(List<CategoryList> categoryLists) {
                if(!categoryLists.isEmpty()){
//                    categoryAdapter = new CategoryAdapter(categoryLists, MainActivity.this);
//                    recyclerView.setAdapter(categoryAdapter);
                    categories.addAll(categoryLists);
                    categoryAdapter.notifyDataSetChanged();
                    //progressBar.setVisibility(View.GONE);
                    //recyclerView.setVisibility(View.VISIBLE);
                    Log.d("Category in livedata", ""+categoryLists);
                }

            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//??

        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_action);
        MenuItem item2 = menu.findItem(R.id.orientation_action);

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                if(viewPager.getOrientation() == ORIENTATION_VERTICAL ){
                    viewPager.setOrientation(ORIENTATION_HORIZONTAL);
                    item2.setIcon(R.drawable.swap_horiz);

                }else {
                    viewPager.setOrientation(ORIENTATION_VERTICAL);
                    item2.setIcon(R.drawable.swap_vert);
                }
                return false;
            }
        });


        SearchView searchView = (SearchView) item.getActionView();//??
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        filterCategories(newText);

                        return false;
                    }
                }
        );


        return true;
    }



    private void filterCategories(String text) {
        if (text.isBlank()){

            categoryAdapter.setCategoryList(categories);

        }else {

            List<CategoryList> data = new ArrayList<>();

            for(int position = 0; position<categories.size(); position++){

                CategoryList category = categories.get(position);

                if (category.getStrCategory().toLowerCase().contains(text.toLowerCase())){
                    data.add(category);
                }

            }
            categoryAdapter.setCategoryList(data);

        }




    }

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