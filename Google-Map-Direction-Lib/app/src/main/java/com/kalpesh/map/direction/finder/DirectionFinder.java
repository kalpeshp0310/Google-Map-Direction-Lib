package com.kalpesh.map.direction.finder;

import android.content.Context;
import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.kalpesh.map.direction.factory.DirectionUrlFactory;
import com.kalpesh.map.direction.utils.LogUtils;
import com.kalpesh.map.direction.utils.Utils;

/**
 * Created by Kalpesh Patel on 03-10-2014.
 */
public class DirectionFinder {

    private static final String TAG = DirectionFinder.class.getSimpleName();
    private String apiKey;
    private IDirectionListener directionListener;
    private Context context;


    public DirectionFinder(Context context, String apiKey, IDirectionListener directionListener) {
        if (TextUtils.isEmpty(apiKey))
            throw new IllegalArgumentException("API Key can not be null or empty. Please provide valid API Key. \n To get API Key reefer https://developers.google.com/maps/documentation/directions/#api_key");
        this.apiKey = apiKey;
        this.context = context;
        this.directionListener = directionListener;
    }


    /**
     * Finds Direction asynchronously for given <b>Origin</b> and <b>Destination</b>. For accurate result, We recommend you to use Origin and Destination value received from <a href="https://developers.google.com/maps/documentation/geocoding/">Geocoding API</a>
     * @param origin      Origin Address
     * @param destination Destination Address
     * @param directionListener Listener object to receive Callback.
     */

    public void findDirection(String origin, String destination) {
        findDirection(origin, destination, null);
    }

    /**
     * Finds Direction asynchronously for given <b>Origin</b> and <b>Destination</b>. For accurate result, We recommend you to use Origin and Destination value received from <a href="https://developers.google.com/maps/documentation/geocoding/">Geocoding API</a>
     * @param origin      Origin Address
     * @param destination Destination Address
     * @param wayPoints Way points of your journey as String array.
     * @param directionListener Listener object to receive Callback.
     */
    public void findDirection(String origin, String destination, String[] wayPoints) {
        if (TextUtils.isEmpty(origin) || TextUtils.isEmpty(destination))
            throw new IllegalArgumentException("Please provide non null origin and destination value");
        if (context != null && Utils.isNetworkAvailable(context)) {
            String url = DirectionUrlFactory.createDirectionUrl(origin, destination, wayPoints, apiKey);
            new DirectionFinderTask(directionListener).execute(url);
            LogUtils.d(TAG, url);
        } else {
            directionListener.onError(DirectionError.NO_INTERNET_CONNECTION);
        }
    }

    /**
     * Finds Direction asynchronously for given <b>Origin</b> and <b>Destination</b>.
     * @param originLatLng      Origin Address
     * @param destinationLatLng Destination Address
     * @param directionListener Listener object to receive Callback.
     */
    public void findDirection(LatLng originLatLng, LatLng destinationLatLng) {
        findDirection(originLatLng, destinationLatLng, null);
    }

    /**
     * Finds Direction asynchronously for given <b>Origin</b> and <b>Destination</b>.
     * @param originLatLng      Origin Address
     * @param destinationLatLng Destination Address
     * @param wayPoints Way points of your journey as LatLng array.
     * @param directionListener Listener object to receive Callback.
     */
    public void findDirection(LatLng originLatLng, LatLng destinationLatLng, LatLng[] wayPoints) {
        if (originLatLng == null || destinationLatLng == null)
            throw new IllegalArgumentException("Please provide non null origin and destination LatLng value");
        if (context != null && Utils.isNetworkAvailable(context)) {
            String url = DirectionUrlFactory.createDirectionUrl(originLatLng, destinationLatLng, wayPoints, apiKey);
            new DirectionFinderTask(directionListener).execute(url);
            LogUtils.d(TAG, url);
        } else {
            directionListener.onError(DirectionError.NO_INTERNET_CONNECTION);
        }
    }
}
