package com.kalpesh.map.direction.model.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abc on 03-10-2014.
 */
public class DirectionApiMapper {

    @SerializedName("routes")
    private List<Routes> routesList;
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public List<Routes> getRoutesList() {
        return routesList;
    }
}
