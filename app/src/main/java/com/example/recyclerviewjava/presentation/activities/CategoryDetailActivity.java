package com.example.recyclerviewjava.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.recyclerviewjava.R;

public class CategoryDetailActivity extends AppCompatActivity {


    String id, name, description, thumb;
    ImageView image;
    TextView name_text, description_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        Intent intent = getIntent();
        this.id = intent.getStringExtra("id");
        this.name = intent.getStringExtra("name");

        this.description = intent.getStringExtra("description");
        this.thumb = intent.getStringExtra("thumb");

        image = findViewById(R.id.detail_imageView);
        name_text = findViewById(R.id.detail_name);
        description_text = findViewById(R.id.detail_description);
        Glide.with(this).load(thumb).into(image);
        name_text.setText(name);
        description_text.setText(description);

    }
}