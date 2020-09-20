package com.taimoorsikander.cityguideapp.helperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taimoorsikander.cityguideapp.R;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder> {

    private ArrayList<MostViewed> mostViewedList;

    public MostViewedAdapter(ArrayList<MostViewed> mostViewedList) {
        this.mostViewedList = mostViewedList;
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card, parent, false);
        return new MostViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {
        MostViewed model = mostViewedList.get(position);
        holder.ivLogo.setImageResource(model.getImages());
        holder.tvTitle.setText(model.getTitle());
        holder.tvDescription.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return mostViewedList.size();
    }

    public class MostViewedViewHolder extends RecyclerView.ViewHolder {
        ImageView ivLogo;
        TextView tvTitle, tvDescription;

        MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.iv_mv_image);
            tvTitle = itemView.findViewById(R.id.tv_mv_title);
            tvDescription = itemView.findViewById(R.id.tv_mv_desc);
        }
    }
}
