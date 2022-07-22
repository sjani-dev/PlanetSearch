package com.geektrust.planetsearch.model;

import java.util.ArrayList;
import java.util.List;

public class PlanetListResposne {

    public List<PlanetDetails> data;

    public List<PlanetDetails> getData() {
        return data;
    }

    public void setData(ArrayList<PlanetDetails> data) {
        this.data = data;
    }
}
