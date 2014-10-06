package com.kalpesh.map.direction.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by abc on 03-10-2014.
 */
public class Direction {

    private String startAddress;
    private String endAddress;
    private LatLng startLocation;
    private LatLng endLocation;
    private PolylineOptions route;

    public Direction(String startAddress, String endAddress, LatLng startLocation, LatLng endLocation, PolylineOptions route) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.route = route;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public LatLng getStartLocation() {
        return startLocation;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public PolylineOptions getRoute() {
        return route;
    }
}
