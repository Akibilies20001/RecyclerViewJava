package com.example.recyclerviewjava.presentation.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerviewjava.domain.FilterData;
import com.example.recyclerviewjava.presentation.activities.CategoryDetailActivity;
import com.example.recyclerviewjava.R;
import com.example.recyclerviewjava.domain.models.CategoryList;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FilterData {



    List<CategoryList> categoryList;
    List<CategoryList> reserveCategoryList =new ArrayList<>();

    Context context;
    public CategoryAdapter(
            List<CategoryList>  categories,
            Context context
    ) {
        this.context = context;
        this.categoryList = categories;

    }

    public void setCategoryList(List<CategoryList> categoryList) {
        this.categoryList = categoryList;
        this.reserveCategoryList.addAll(categoryList);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CategoryList> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length()<=0){
                filteredList.addAll(reserveCategoryList);
            }else {
                String searchString = constraint.toString().trim().toLowerCase();
                for(CategoryList item : categoryList){
                    if(item.getStrCategory().toLowerCase().contains(searchString)){
                        filteredList.add(item);
                    }

                }
            }
            FilterResults results= new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            categoryList.clear();
            categoryList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    class CategoryYellowViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView id, name;
        public CategoryYellowViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image_yellow);
            id = itemView.findViewById(R.id.category_id_yellow);
            name = itemView.findViewById(R.id.category_name_yellow);
        }
    }

    class CategoryGreenViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView id, name;
        public CategoryGreenViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image_green);
            id = itemView.findViewById(R.id.category_id_green);
            name = itemView.findViewById(R.id.category_name_green);
        }
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView id, name;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image);
            id = itemView.findViewById(R.id.category_id);
            name = itemView.findViewById(R.id.category_name);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == 1){
            view = LayoutInflater.from(context).inflate(R.layout.category_item_green, parent, false);
            return new CategoryGreenViewHolder(view);
        }


        else if (viewType == 2){
            view = LayoutInflater.from(context).inflate(R.layout.category_item_yellow, parent, false);
            return new CategoryYellowViewHolder(view);
        }



        else  {
            view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
            return new CategoryViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         CategoryViewHolder categoryViewHolder;
         CategoryGreenViewHolder categoryGreenViewHolder;
         CategoryYellowViewHolder categoryYellowViewHolder;
        int pos = position;

        if (getItemViewType(pos)== 1){
            categoryGreenViewHolder =(CategoryGreenViewHolder)holder;
            Glide.with(context).load(categoryList.get(pos).getStrCategoryThumb()).into(categoryGreenViewHolder.imageView);
            categoryGreenViewHolder.id.setText(categoryList.get(pos).getIdCategory());
            categoryGreenViewHolder.name.setText(categoryList.get(pos).getStrCategory());
            categoryGreenViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CategoryDetailActivity.class);
                    intent.putExtra("id", categoryList.get(pos).getIdCategory());
                    intent.putExtra("name", categoryList.get(pos).getStrCategory());
                    intent.putExtra("description", categoryList.get(pos).getStrCategoryDescription());
                    intent.putExtra("thumb", categoryList.get(pos).getStrCategoryThumb());
                    context.startActivity(intent);


                }
            });
        }else if (getItemViewType(pos)== 2 ){
            categoryYellowViewHolder =(CategoryYellowViewHolder)holder;
            Glide.with(context).load(categoryList.get(pos).getStrCategoryThumb()).into(categoryYellowViewHolder.imageView);
            categoryYellowViewHolder.id.setText(categoryList.get(pos).getIdCategory());
            categoryYellowViewHolder.name.setText(categoryList.get(pos).getStrCategory());
            categoryYellowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,CategoryDetailActivity.class);
                    intent.putExtra("id", categoryList.get(pos).getIdCategory());
                    intent.putExtra("name", categoryList.get(pos).getStrCategory());
                    intent.putExtra("description", categoryList.get(pos).getStrCategoryDescription());
                    intent.putExtra("thumb", categoryList.get(pos).getStrCategoryThumb());
                    context.startActivity(intent);


                }
            });


        }else {
            categoryViewHolder = (CategoryViewHolder)holder;
            Glide.with(context).load(categoryList.get(pos).getStrCategoryThumb()).into(categoryViewHolder.imageView);
            categoryViewHolder.id.setText(categoryList.get(pos).getIdCategory());
            categoryViewHolder.name.setText(categoryList.get(pos).getStrCategory());
            categoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,CategoryDetailActivity.class);
                    intent.putExtra("id", categoryList.get(pos).getIdCategory());
                    intent.putExtra("name", categoryList.get(pos).getStrCategory());
                    intent.putExtra("description", categoryList.get(pos).getStrCategoryDescription());
                    intent.putExtra("thumb", categoryList.get(pos).getStrCategoryThumb());
                    context.startActivity(intent);


                }
            });
        }

//         // position number consistent for one view. as position change overtime
//        Glide.with(context).load(categoryList.get(pos).getStrCategoryThumb()).into(holder.imageView);
//        holder.id.setText(categoryList.get(pos).getIdCategory());
//        holder.name.setText(categoryList.get(pos).getStrCategory());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,CategoryDetailActivity.class);
//                intent.putExtra("id", categoryList.get(pos).getIdCategory());
//                intent.putExtra("name", categoryList.get(pos).getStrCategory());
//                intent.putExtra("description", categoryList.get(pos).getStrCategoryDescription());
//                intent.putExtra("thumb", categoryList.get(pos).getStrCategoryThumb());
//                context.startActivity(intent);
//
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (categoryList!= null && getItemCount()>0){
            int pos = position;
            String category_name = categoryList.get(pos).getStrCategory();
            if (category_name.trim().charAt(0)== 'B') return 2;
            else if (category_name.trim().charAt(0)== 'V') return 1;
            else return 0;


//        }
//        else return -1;



    }

}



