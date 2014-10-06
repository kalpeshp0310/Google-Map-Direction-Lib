package com.kalpesh.map.direction.model.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abc on 03-10-2014.
 */
public class Routes {
    @SerializedName("legs")
    private List<RouteLag> routeLags;


    public List<RouteLag> getRouteLags() {
        return routeLags;
    }
}
