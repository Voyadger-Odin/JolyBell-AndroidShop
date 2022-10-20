package com.voyager.jolybell.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.voyager.jolybell.R;
import com.voyager.jolybell.model.CategoryChanged;
import com.voyager.jolybell.model.CategoryItems;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    public Context context;
    public TextView itemSelected;

    public ArrayList<CategoryChanged> CategoryChangedListeners;

    public CategoryAdapter(Context context) {
        this.context = context;
        this.CategoryChangedListeners = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(categoryItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.categoryTitle.setText(CategoryItems.categorys.get(position).category);
        holder.categoryTitle.setOnClickListener(view -> onCategoryClick(position, holder.categoryTitle));

        if(position == CategoryItems.categorySelected){
            holder.categoryTitle.setBackgroundResource(R.drawable.category_item_design);
            itemSelected = holder.categoryTitle;
        }else{
            holder.categoryTitle.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return CategoryItems.categorys.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.category_item_id);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void onCategoryClick(int position, TextView element){
        if(position != CategoryItems.categorySelected){
            if(itemSelected != null){
                itemSelected.setBackgroundResource(R.color.white);
            }
            CategoryItems.categorySelected = position;
            if(element != null){
                element.setBackgroundResource(R.drawable.category_item_design);
            }
            itemSelected = element;


            // Send about change listeners
            CategoryChangedListeners.forEach(categoryChanged -> {
                categoryChanged.categoryChanged();
            });
        }
    }
}
