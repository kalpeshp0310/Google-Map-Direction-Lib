package com.kalpesh.map.direction.factory;

import com.google.android.gms.maps.model.LatLng;
import com.kalpesh.map.direction.finder.DirectionApiReqConstants;

import java.net.URLEncoder;

/**
 * Created by Kalpesh Patel on 03-10-2014.
 */
public class DirectionUrlFactory {

    private static final String DIRECTION_BASE_URL = "https://maps.googleapis.com/maps/api/directions/json?";

    /**
     * Factory Method to create Direction Url using origin and destination as a {@link com.google.android.gms.maps.model.LatLng} value.
     *
     * @param originLatLng      Origin location LatLng object
     * @param destinationLatLng Destination location LatLng object
     * @param apiKey            API Key of your application to access Google Direction Service.
     * @return Direction Url
     */

    public static String createDirectionUrl(LatLng originLatLng, LatLng destinationLatLng, String apiKey) {
        return createDirectionUrl(originLatLng, destinationLatLng, null, apiKey);
    }

    /**
     * Factory Method to create Direction Url using origin and destination as a {@link com.google.android.gms.maps.model.LatLng} value.
     *
     * @param originLatLng      Origin location LatLng object
     * @param destinationLatLng Destination location LatLng object
     * @param wayPoints         Way points of your journey as array of LatLng value.
     * @param apiKey            API Key of your application to access Google Direction Service.
     * @return Direction Url
     */

    public static String createDirectionUrl(LatLng originLatLng, LatLng destinationLatLng, LatLng[] wayPoints, String apiKey) {
        StringBuilder urlBuilder = new StringBuilder(DIRECTION_BASE_URL);

        String origin = formatLatLng(originLatLng);
        String destination = formatLatLng(destinationLatLng);

        urlBuilder.append(getFormattedKeyValue(DirectionApiReqConstants.KEY_ORIGIN, origin));
        urlBuilder.append(getFormattedKeyValue(DirectionApiReqConstants.KEY_DESTINATION, destination));

        if (wayPoints != null && wayPoints.length > 0) {
            StringBuilder wayPointBuilder = new StringBuilder();
            wayPointBuilder.append(formatLatLng(wayPoints[0]));
            for (int i = 1; i < wayPoints.length - 1; i++) {
                wayPointBuilder.append("|" + formatLatLng(wayPoints[i]));
            }
            urlBuilder.append(getFormattedKeyValue(DirectionApiReqConstants.KEY_WAYPOINTS, wayPointBuilder.toString()));
        }

        urlBuilder.append(getFormattedKeyValue(DirectionApiReqConstants.KEY_API_KEY, URLEncoder.encode(apiKey)));
        return urlBuilder.toString();
    }

    /**
     * Factory Method to create Direction Url using origin and destination as a {@link java.lang.String} value.
     *
     * @param origin      Origin Address
     * @param destination Destination Address
     * @param apiKey      API Key of your application to access Google Direction Service.
     * @return Direction Url
     */
    public static String createDirectionUrl(String origin, String destination, String apiKey) {
        return createDirectionUrl(origin, destination, null, apiKey);
    }

    /**
     * Factory Method to create Direction Url using origin and destination as a {@link java.lang.String} value.
     *
     * @param origin      Origin Address
     * @param destination Destination Address
     * @param wayPoints   Way points of your journey as String array.
     * @param apiKey      API Key of your application to access Google Direction Service.
     * @return Direction Url
     */
    public static String createDirectionUrl(String origin, String destination, String[] wayPoints, String apiKey) {
        StringBuilder urlBuilder = new StringBuilder(DIRECTION_BASE_URL);
        urlBuilder.append(getFormattedKeyValue(DirectionApiReqConstants.KEY_ORIGIN, URLEncoder.encode(origin)));
        urlBuilder.append(getFormattedKeyValue(DirectionApiReqConstants.KEY_DESTINATION, URLEncoder.encode(destination)));

        if (wayPoints != null && wayPoints.length > 0) {
            StringBuilder wayPointBuilder = new StringBuilder();
            wayPointBuilder.append(URLEncoder.encode(wayPoints[0]));
            for (int i = 1; i < wayPoints.length - 1; i++) {
                wayPointBuilder.append("|" + URLEncoder.encode(wayPoints[i]));
            }
            urlBuilder.append(getFormattedKeyValue(DirectionApiReqConstants.KEY_WAYPOINTS, wayPointBuilder.toString()));
        }

        urlBuilder.append(getFormattedKeyValue(DirectionApiReqConstants.KEY_API_KEY, URLEncoder.encode(apiKey)));
        return urlBuilder.toString();
    }

    private static String formatLatLng(LatLng latLng) {
        return String.format("%f,%f", latLng.latitude, latLng.longitude);
    }

    private static String getFormattedKeyValue(String key, String value) {
        return String.format("&%s=%s", key, value);
    }
}
