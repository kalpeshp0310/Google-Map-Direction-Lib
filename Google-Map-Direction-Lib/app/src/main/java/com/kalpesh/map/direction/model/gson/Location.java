package com.kalpesh.map.direction.model.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abc on 03-10-2014.
 */
public class Location {

    @SerializedName("lat")
    private String lattitude;

    @SerializedName("lng")
    private String longitude;

    public String getLattitude() {
        return lattitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
