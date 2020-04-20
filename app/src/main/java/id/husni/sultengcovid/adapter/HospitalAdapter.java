/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.husni.sultengcovid.R;
import id.husni.sultengcovid.model.Hospital;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Hospital> hospitals = new ArrayList<>();

    public HospitalAdapter(Context context) {
        this.context = context;
    }

    public void setHospitals(ArrayList<Hospital> items) {
        if (hospitals.size() > 0){
            hospitals.clear();
        }
        hospitals.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hospital_item_holder, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {
        holder.tvHospitalName.setText(hospitals.get(position).getHospitalName());
        holder.tvHospitalAddress.setText(hospitals.get(position).getHospitalAddress());
        holder.btnCall.setOnClickListener(v -> {
            String telp = hospitals.get(position).getHospitalPhone();
            Uri uriTelp = Uri.parse("tel:"+telp);
            Intent intentToCall = new Intent(Intent.ACTION_DIAL);
            intentToCall.setData(uriTelp);
            context.startActivity(intentToCall);
        });
        holder.btnEmail.setOnClickListener(v -> {
            String email = hospitals.get(position).getHospitalEmail();
            Intent intentToEmail = new Intent(Intent.ACTION_SEND);
            intentToEmail.setType("message/rfc822");
            intentToEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            context.startActivity(Intent.createChooser(intentToEmail,"Via"));

        });
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvHospitalName;
        final TextView tvHospitalAddress;
        final Button btnCall;
        final Button btnEmail;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospitalName = itemView.findViewById(R.id.tvHospitalName);
            tvHospitalAddress = itemView.findViewById(R.id.tvHospitalAddress);
            btnCall = itemView.findViewById(R.id.btnHospitalCall);
            btnEmail = itemView.findViewById(R.id.btnHospitalEmail);
        }
    }
}
