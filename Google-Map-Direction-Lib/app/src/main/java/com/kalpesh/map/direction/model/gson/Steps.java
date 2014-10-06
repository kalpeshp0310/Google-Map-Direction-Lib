package com.kalpesh.map.direction.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abc on 03-10-2014.
 */
public class Steps {

    @SerializedName("polyline")
    private Polyline polyline;

    public Polyline getPolyline() {
        return polyline;
    }
}
