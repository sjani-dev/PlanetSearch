package com.geektrust.planetsearch.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektrust.planetsearch.R;
import com.geektrust.planetsearch.VehicleActivity;
import com.geektrust.planetsearch.model.PlanetDetails;
import com.geektrust.planetsearch.model.VehicleDetails;

import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder> {

    List<PlanetDetails> planetList;
    List<VehicleDetails> vehiclelist;
    Activity activity;
    Context context;

    private android.widget.RelativeLayout.LayoutParams layoutParams;

    public PlanetAdapter(Activity activity, List<PlanetDetails> planetList, Context context) {
        this.activity = activity;
        this.planetList = planetList;
        this.context = context;
    }

    public PlanetAdapter(Activity activity, List<VehicleDetails> vehiclelist) {
        this.activity = activity;
        this.vehiclelist = vehiclelist;
    }

    private OnItemClick itemClick;

    public PlanetAdapter(Activity activity, List<VehicleDetails> dataList, ArrayList<PlanetDetails> planetlist) {
        this.activity = activity;
        this.vehiclelist = vehiclelist;
        this.planetList = planetList;
    }

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.planet_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (null != planetList && planetList.size() != 0) {
            final PlanetDetails planetDetails = planetList.get(position);
            holder.tv_name.setText(planetList.get(position).getName());
            holder.lay_icon.setBackgroundColor(planetDetails.isSelected() ? Color.CYAN : Color.TRANSPARENT);
            holder.lay_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    planetDetails.setSelected(!planetDetails.isSelected());
                    holder.lay_icon.setBackgroundColor(planetDetails.isSelected() ? Color.BLUE : Color.TRANSPARENT);
                }
            });
        } else if (null != vehiclelist && vehiclelist.size() != 0) {
            final VehicleDetails vehicleDetails = vehiclelist.get(position);
            holder.tv_name.setText(vehiclelist.get(position).getName());
            holder.lay_icon.setBackgroundColor(vehicleDetails.isSelected() ? Color.CYAN : Color.TRANSPARENT);
            holder.lay_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClick == null) return;
                   // itemClick.onItemClick(v, vehiclelist.get(position), position);

                    vehicleDetails.setSelected(!vehicleDetails.isSelected());
                    holder.lay_icon.setBackgroundColor(vehicleDetails.isSelected() ? Color.BLUE : Color.TRANSPARENT);
                }
            });


            holder.iv_icon.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                    String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                    ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(holder.iv_icon);
                    v.startDrag(dragData,myShadow,null,0);
                    return true;
                }
            });
String msg = "something";
            holder.iv_icon.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");

                            // Do nothing
                            break;

                        case DragEvent.ACTION_DRAG_ENTERED:
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                            int x_cord = (int) event.getX(); int y_cord = (int) event.getY();
                            break;

                        case DragEvent.ACTION_DRAG_EXITED :
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                            x_cord = (int) event.getX();
                            y_cord = (int) event.getY();
                            layoutParams.leftMargin = x_cord;
                            layoutParams.topMargin = y_cord;
                            v.setLayoutParams(layoutParams);
                            break;

                        case DragEvent.ACTION_DRAG_LOCATION  :
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                            x_cord = (int) event.getX();
                            y_cord = (int) event.getY();
                            break;
                        case DragEvent.ACTION_DRAG_ENDED   :
                            Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");

                            // Do nothing
                            break;

                        case DragEvent.ACTION_DROP:
                            Log.d(msg, "ACTION_DROP event");


                            // Do nothing
                            break;
                        default: break;
                    }
                    return true;
                }
            });
            holder.iv_icon.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(holder.iv_icon);

                        holder.iv_icon.startDrag(data, shadowBuilder, holder.iv_icon, 0);
                        holder.iv_icon.setVisibility(View.INVISIBLE);
                        return true;
                    } else {
                        return false;
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        int size =0;
        if (null != planetList && planetList.size() != 0) {
            size= planetList.size();
        } else if (null != vehiclelist && vehiclelist.size() != 0) {
            size= vehiclelist.size();
        }
        return size;
    }

  /*  public ArrayList<VehicleDetails> getSelectedVeh() {
        ArrayList<VehicleDetails> selected = new ArrayList<>();
        for (int i = 0; i < planetList.size(); i++) {
            if (planetList.get(i).isSelected()) {
                selected.add(planetList.get(i));
            }
        }
        return selected;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_icon;
        RelativeLayout lay_icon;
        TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            lay_icon =itemView.findViewById(R.id.lay_icon);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }
    public interface OnItemClick {
        void onItemClick(View view, VehicleDetails planetname, int position);
    }
}