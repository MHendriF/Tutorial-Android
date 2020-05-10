package com.taimoorsikander.cityguideapp.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taimoorsikander.cityguideapp.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    private ArrayList<Featured> featuredLocations;

    public FeaturedAdapter(ArrayList<Featured> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card, parent, false);
        return new FeaturedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        Featured model = featuredLocations.get(position);
        holder.ivLogo.setImageResource(model.getImages());
        holder.tvTitle.setText(model.getTitle());
        holder.tvDescription.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ImageView ivLogo;
        TextView tvTitle, tvDescription;

        FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.iv_feature_image);
            tvTitle = itemView.findViewById(R.id.tv_featured_title);
            tvDescription = itemView.findViewById(R.id.tv_featured_desc);
        }
    }

}
