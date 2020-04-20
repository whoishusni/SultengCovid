/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.husni.sultengcovid.activity.DistrictDetailActivity;
import id.husni.sultengcovid.R;
import id.husni.sultengcovid.model.District;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder> {
    private final ArrayList<District> districts = new ArrayList<>();
    private final Context context;
    public DistrictAdapter(Context context) {
        this.context = context;
    }

    public void setDistricts(ArrayList<District> items) {
        if (districts.size() > 0) {
            districts.clear();
        }
        districts.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DistrictAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.district_item_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictAdapter.ViewHolder holder, int position) {
        holder.tvDistrictName.setText(districts.get(position).getDistrictName());
        holder.tvDistrictPositive.setText(String.valueOf(districts.get(position).getDistrictPositive()));
        holder.tvDistrictNegative.setText(String.valueOf(districts.get(position).getDistrictNegative()));
        holder.tvDistrictDeaths.setText(String.valueOf(districts.get(position).getDistrictDeaths()));
        holder.btnDetail.setOnClickListener(v -> {
            Intent intentDetail = new Intent(context, DistrictDetailActivity.class);
            intentDetail.putExtra(DistrictDetailActivity.EXTRA_PARSING_DATA,districts.get(position));
            context.startActivity(intentDetail);
        });
    }

    @Override
    public int getItemCount() {
        return districts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvDistrictName;
        final TextView tvDistrictPositive;
        final TextView tvDistrictNegative;
        final TextView tvDistrictDeaths;
        final Button btnDetail;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDistrictName = itemView.findViewById(R.id.tvDistrictName);
            tvDistrictPositive = itemView.findViewById(R.id.tvDistrictPositive);
            tvDistrictNegative = itemView.findViewById(R.id.tvDistrictNegative);
            tvDistrictDeaths = itemView.findViewById(R.id.tvDistrictDeaths);
            btnDetail = itemView.findViewById(R.id.btnDistrictDetail);
        }
    }
}
