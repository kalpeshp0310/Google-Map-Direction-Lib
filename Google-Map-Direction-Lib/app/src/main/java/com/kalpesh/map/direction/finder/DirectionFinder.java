package com.kalpesh.map.direction.finder;

import android.content.Context;
import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.kalpesh.map.direction.factory.DirectionUrlFactory;
import com.kalpesh.map.direction.utils.LogUtils;
import com.kalpesh.map.direction.utils.Utils;

/**
 * Created by abc on 03-10-2014.
 */
public class DirectionFinder {

    private static final String TAG = DirectionFinder.class.getSimpleName();
    private String apiKey;
    private IDirectionListener directionListener;
    private Context context;


    public DirectionFinder(Context context, String apiKey) {
        if(TextUtils.isEmpty(apiKey))
            throw new IllegalArgumentException("API Key can not be null or empty. Please provide valid API Key. \n To get API Key reefer https://developers.google.com/maps/documentation/directions/#api_key");
        this.apiKey = apiKey;
        this.context = context;
    }

    public void findDirection(LatLng originLatLng, LatLng destinationLatLng, IDirectionListener directionListener) {
        if(originLatLng == null || destinationLatLng == null)
            throw new IllegalArgumentException("Please provide non null original and destination LatLng value");
        this.directionListener = directionListener;
        if (context != null && Utils.isNetworkAvailable(context)) {
            String url = DirectionUrlFactory.createDirectionUrl(originLatLng, destinationLatLng, apiKey);
            new DirectionFinderTask(directionListener).execute(url);
            LogUtils.d(TAG, url);
        } else {
            directionListener.onError(DirectionError.NO_INTERNET_CONNECTION);
        }
    }


}
