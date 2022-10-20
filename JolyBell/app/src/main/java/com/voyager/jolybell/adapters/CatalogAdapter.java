package com.voyager.jolybell.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.voyager.jolybell.ItemDescriptionBy;
import com.voyager.jolybell.R;
import com.voyager.jolybell.model.CatalogItems;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    public Context context;

    public CatalogAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.catalog_item, parent, false);
        return new CatalogAdapter.CatalogViewHolder(categoryItem);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CatalogViewHolder holder, int position) {
        holder.catalog_item_name.setText(
                CatalogItems.itemsShow.get(position).name
        );
        holder.catalog_item_cost.setText(
                CatalogItems.itemsShow.get(position).costToString()
        );
        Picasso.with(context)
                .load(CatalogItems.itemsShow.get(position).img.get(0))
                .into(holder.catalog_item_img);

        holder.catalog_item.setOnClickListener(view -> {openDescriptionItem(position, holder);});
    }

    @Override
    public int getItemCount() {
        return CatalogItems.itemsShow.size();
    }

    private void openDescriptionItem(int position, CatalogAdapter.CatalogViewHolder holder){
        Intent intentDescription = new Intent(context, ItemDescriptionBy.class);
        // Из какой страницы переходим
        intentDescription.putExtra("fromePage", "catalog");
        // ID элемента
        intentDescription.putExtra("itemSelected", CatalogItems.itemsShow.get(position).itemId);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                (Activity)context,
                new Pair<View, String>(holder.catalog_item_img, "itemTransitionImg")
        );

        context.startActivity(intentDescription, options.toBundle());
    }

    public class CatalogViewHolder extends RecyclerView.ViewHolder{

        LinearLayout catalog_item;

        TextView catalog_item_name;
        TextView catalog_item_cost;
        ImageView catalog_item_img;

        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);

            catalog_item = itemView.findViewById(R.id.catalog_item);

            catalog_item_name = itemView.findViewById(R.id.catalog_item_name);
            catalog_item_cost = itemView.findViewById(R.id.catalog_item_cost);
            catalog_item_img = itemView.findViewById(R.id.catalog_item_img);
        }
    }
}