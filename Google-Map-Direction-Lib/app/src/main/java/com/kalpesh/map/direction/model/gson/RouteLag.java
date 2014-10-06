package com.kalpesh.map.direction.model.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abc on 03-10-2014.
 */
public class RouteLag {

    @SerializedName("start_address")
    private String startAddress;

    @SerializedName("end_address")
    private String endAddress;

    @SerializedName("start_location")
    private Location startLocation;

    @SerializedName("end_location")
    private Location endLocation;

    @SerializedName("steps")
    private List<Steps> stepsList;

    public String getStartAddress() {
        return startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public List<Steps> getStepsList() {
        return stepsList;
    }
}
