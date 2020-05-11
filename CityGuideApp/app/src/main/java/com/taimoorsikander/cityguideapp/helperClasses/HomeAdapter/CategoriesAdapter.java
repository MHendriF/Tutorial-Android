package com.taimoorsikander.cityguideapp.helperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taimoorsikander.cityguideapp.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private ArrayList<Categories> categoriesList;

    public CategoriesAdapter(ArrayList<Categories> categories) {
        this.categoriesList = categories;
    }


    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card, parent, false);
        return new CategoriesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Categories model = categoriesList.get(position);
        holder.ivLogo.setImageResource(model.getImages());
        holder.tvTitle.setText(model.getTitle());
        holder.rlGradient.setBackground(model.getGradient());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivLogo;
        RelativeLayout rlGradient;

        CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            rlGradient = itemView.findViewById(R.id.rl_background_gradient);
            ivLogo = itemView.findViewById(R.id.iv_categories_image);
            tvTitle = itemView.findViewById(R.id.tv_categories_title);
        }
    }
}
