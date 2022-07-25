package com.geektrust.planetsearch.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanetDetails implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("distance")
    @Expose
    private Integer distance;

    private boolean isSelected = false;

    public PlanetDetails() {
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.distance);
        dest.writeBoolean(this.isSelected);
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected PlanetDetails(Parcel in) {
        //retrieve
        this.name = in.readString();
        this.distance = in.readInt();
        this.isSelected = in.readBoolean();
    }

    public static final Parcelable.Creator<PlanetDetails> CREATOR = new Parcelable.Creator<PlanetDetails>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public PlanetDetails createFromParcel(Parcel source) {
            return new PlanetDetails(source);
        }

        public PlanetDetails[] newArray(int size) {
            return new PlanetDetails[size];
        }
    };
}
