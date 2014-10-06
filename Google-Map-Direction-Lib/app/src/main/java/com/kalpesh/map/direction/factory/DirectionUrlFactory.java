package com.kalpesh.map.direction.factory;

import com.google.android.gms.maps.model.LatLng;
import com.kalpesh.map.direction.finder.DirectionApiReqConstants;

/**
 * Created by abc on 03-10-2014.
 */
public class DirectionUrlFactory {

    private static final String DIRECTION_BASE_URL = "https://maps.googleapis.com/maps/api/directions/json?";

    public static String createDirectionUrl(LatLng originLatLng, LatLng destinationLatLng, String apiKey) {
        StringBuilder urlBuilder = new StringBuilder(DIRECTION_BASE_URL);
        String origin = String.format("%f,%f", originLatLng.latitude, originLatLng.longitude);
        String destination = String.format("%f,%f", destinationLatLng.latitude, destinationLatLng.longitude);
        urlBuilder.append(String.format("&%s=%s", DirectionApiReqConstants.KEY_ORIGIN, origin));
        urlBuilder.append(String.format("&%s=%s", DirectionApiReqConstants.KEY_DESTINATION, destination));
        urlBuilder.append(String.format("&%s=%s", DirectionApiReqConstants.KEY_API_KEY, apiKey));
        return urlBuilder.toString();
    }
}
