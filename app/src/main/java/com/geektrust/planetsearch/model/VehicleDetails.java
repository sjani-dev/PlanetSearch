package com.geektrust.planetsearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDetails {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("total_no")
    @Expose
    private Integer total_no;
    @SerializedName("max_distance")
    @Expose
    private Integer max_distance;
    @SerializedName("speed")
    @Expose
    private Integer speed;

    private boolean isSelected = false;

    public VehicleDetails() {
    }

    public Integer getTotal_no() {
        return total_no;
    }

    public void setTotal_no(Integer total_no) {
        this.total_no = total_no;
    }

    public Integer getMax_distance() {
        return max_distance;
    }

    public void setMax_distance(Integer max_distance) {
        this.max_distance = max_distance;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
