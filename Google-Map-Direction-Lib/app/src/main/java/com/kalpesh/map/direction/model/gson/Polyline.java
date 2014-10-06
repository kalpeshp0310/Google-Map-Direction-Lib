package com.kalpesh.map.direction.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abc on 03-10-2014.
 */
public class Polyline {

    @SerializedName("points")
    private String polylinePints;

    public String getPolylinePints() {
        return polylinePints;
    }
}
