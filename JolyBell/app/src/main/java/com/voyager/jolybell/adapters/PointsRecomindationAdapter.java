package com.voyager.jolybell.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.voyager.jolybell.R;
import com.voyager.jolybell.model.CategoryChanged;
import com.voyager.jolybell.model.CategoryItems;
import com.voyager.jolybell.model.ItemFromShop;
import com.voyager.jolybell.user_settings.UserSettings;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PointsRecomindationAdapter extends RecyclerView.Adapter<PointsRecomindationAdapter.PointsViewHolder> {

    public Context context;
    public int count;
    int position = 0;
    ItemFromShop.Theme theme;

    public PointsRecomindationAdapter(Context context, int count, ItemFromShop.Theme theme) {
        this.context = context;
        this.count = count;
        this.theme = theme;
    }

    @Override
    public PointsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View categoryItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.point_item, parent, false);

        RelativeLayout point = categoryItem.findViewById(R.id.point);
        if(theme == ItemFromShop.Theme.Dark){
            if (position == 0){
                point.setBackgroundResource(R.drawable.point_fill);
            }else{
                point.setBackgroundResource(R.drawable.point_basic);
            }
        }else{
            if (position == 0){
                point.setBackgroundResource(R.drawable.point_fill_dark);
            }else{
                point.setBackgroundResource(R.drawable.point_basic_dark);
            }
        }
        position++;
        return new PointsRecomindationAdapter.PointsViewHolder(categoryItem);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PointsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class PointsViewHolder extends RecyclerView.ViewHolder{

        public PointsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
