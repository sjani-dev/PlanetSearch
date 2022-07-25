package com.geektrust.planetsearch.util;

import android.view.View;

import com.geektrust.planetsearch.model.PlanetDetails;

public interface OnItemClick {
    void onItemClick(View view, PlanetDetails planetname, int position);
}
