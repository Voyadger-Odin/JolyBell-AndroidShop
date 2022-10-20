package com.voyager.jolybell.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;
import com.voyager.jolybell.ItemDescriptionBy;
import com.voyager.jolybell.R;
import com.voyager.jolybell.model.CatalogItems;
import com.voyager.jolybell.model.ItemFromShop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecommendationsAdapter extends PagerAdapter {

    Context context;
    ArrayList<Integer> items;
    ItemFromShop.Theme theme;

    public RecommendationsAdapter(Context context,
                                  ArrayList<Integer> items,
                                  ItemFromShop.Theme theme) {
        this.context = context;
        this.items = items;
        this.theme = theme;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view.equals(object);
    }

    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.recommendations_item, container, false);



        TextView v_name = view.findViewById(R.id.recommendation_name);
        TextView v_cost = view.findViewById(R.id.recommendation_cost);
        ImageView v_img = view.findViewById(R.id.recommendation_img);

        // Set theme
        if(theme == ItemFromShop.Theme.Light){
            // Gradient
            view.findViewById(R.id.item_gradient).setVisibility(View.GONE);
            // Name
            v_name.setTextColor(ContextCompat.getColor(context, R.color.black));
            // Cost
            v_cost.setTextColor(ContextCompat.getColor(context, R.color.white));
            v_cost.setBackgroundResource(R.drawable.item_design_dark);
        }

        // Set data
        ItemFromShop model = CatalogItems.items.get(items.get(position));
        v_name.setText(model.name);
        v_cost.setText(ItemFromShop.costToString(model.getCost()));
        Picasso.with(context)
                .load(model.img.get(0))
                .into(v_img);

        // Click
        v_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDescription = new Intent(context, ItemDescriptionBy.class);
                intentDescription.putExtra("itemSelected", model.itemId);

                context.startActivity(intentDescription);
            }
        });

        // Add view to container
        container.addView(view, position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        //container.removeView((View)object);
    }
}
