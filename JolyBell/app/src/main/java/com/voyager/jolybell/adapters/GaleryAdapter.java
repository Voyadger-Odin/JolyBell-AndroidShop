package com.voyager.jolybell.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;
import com.voyager.jolybell.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GaleryAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> items;

    public GaleryAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
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
                .inflate(R.layout.galery_images_item, container, false);


        Picasso.with(context)
                .load(items.get(position))
                .into((ImageView) view.findViewById(R.id.item_description_img));



        // Add view to container
        container.addView(view, position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        //container.removeView((View)object);
    }
}
