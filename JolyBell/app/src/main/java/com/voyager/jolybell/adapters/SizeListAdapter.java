package com.voyager.jolybell.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.voyager.jolybell.R;
import com.voyager.jolybell.model.CategoryItems;
import com.voyager.jolybell.model.SelectSizeListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SizeListAdapter extends RecyclerView.Adapter<SizeListAdapter.SizeListViewHolder> {

    private Context context;
    private ArrayList<String> sizes;
    private ArrayList<SelectSizeListener> SelectSizeListeners;
    private ArrayList<SizeListViewHolder> items;

    private int idSelected;

    public SizeListAdapter(Context context, ArrayList<String> sizes, SelectSizeListener selectSizeListener, int idSelected) {
        this.context = context;
        this.sizes = sizes;
        SelectSizeListeners = new ArrayList<>();
        SelectSizeListeners.add(selectSizeListener);

        this.idSelected = idSelected;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public SizeListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View sizeItem = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.size_item, parent, false);
        SizeListAdapter.SizeListViewHolder holder = new SizeListAdapter.SizeListViewHolder(sizeItem);
        items.add(holder);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull SizeListViewHolder holder, int position) {
        holder.text_size_item.setText(sizes.get(position));

        if(position == idSelected){
            holder.setLight();
        }else{
            holder.setDark();
        }

        holder.itemView.setOnClickListener(view -> {
            items.get(idSelected).setDark();
            idSelected = position;
            items.get(idSelected).setLight();

            SelectSizeListeners.forEach(selectSizeListener -> {
                selectSizeListener.SelectSize(position);
            });
        });
    }

    @Override
    public int getItemCount() {
        return sizes.size();
    }

    public class SizeListViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        TextView text_size_item;

        public SizeListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            text_size_item = itemView.findViewById(R.id.text_size_item);
        }

        @SuppressLint("ResourceAsColor")
        public void setLight(){
            itemView.findViewById(R.id.size_item_fon).setBackgroundResource(R.drawable.btn_size_light);
            text_size_item.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        @SuppressLint("ResourceAsColor")
        public void setDark(){
            itemView.findViewById(R.id.size_item_fon).setBackgroundResource(R.drawable.btn_size_dark);
            text_size_item.setTextColor(ContextCompat.getColor(context, R.color.text_gray_light));
        }

    }
}
