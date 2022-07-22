package com.geektrust.planetsearch.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektrust.planetsearch.R;
import com.geektrust.planetsearch.model.PlanetDetails;

import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder> {

    List<PlanetDetails> planetList;
    Activity activity;

    public PlanetAdapter(Activity activity, List<PlanetDetails> planetList) {
        this.activity =activity;
        this.planetList = planetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.planet_item_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_name.setText(planetList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_icon;
        TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}