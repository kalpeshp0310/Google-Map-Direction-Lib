package com.kalpesh.map.direction.finder;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.kalpesh.map.direction.model.Direction;
import com.kalpesh.map.direction.model.gson.DirectionApiMapper;
import com.kalpesh.map.direction.model.gson.Location;
import com.kalpesh.map.direction.model.gson.RouteLag;
import com.kalpesh.map.direction.model.gson.Routes;
import com.kalpesh.map.direction.model.gson.Steps;
import com.kalpesh.map.direction.utils.Utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by user on 10/6/2014.
 */
class DirectionFinderTask extends AsyncTask<String, Void, String> {

    private IDirectionListener directionListener;

    DirectionFinderTask(IDirectionListener directionListener) {
        this.directionListener = directionListener;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String response = null;
        try {
            response = Utils.HttpGetMethod(url);
        } catch (IOException e) {
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        if(!TextUtils.isEmpty(response))
            parseDirectionResponse(response);
        else
            directionListener.onError(DirectionError.INVALID_RESPONSE); // Either google didn't responded or connection redirected to some unknown host. see HttpGetMethod body.
    }

    private void parseDirectionResponse(String directionJson) {
        Gson gson = new Gson();
        DirectionApiMapper directionApiMapper = gson.fromJson(new StringReader(directionJson), DirectionApiMapper.class);
        String status = directionApiMapper.getStatus();
        if (status.equals(DirectionApiReqConstants.RESPONSE_VALUE_OK)) {
            PolylineOptions routePolylineOptions = new PolylineOptions();
            String startAddress;
            String endAddress;
            LatLng startLocation;
            LatLng endLocation;
            List<Routes> routesList = directionApiMapper.getRoutesList();

            startAddress = routesList.get(0).getRouteLags().get(0).getStartAddress();
            Location location = routesList.get(0).getRouteLags().get(0).getStartLocation();
            startLocation = new LatLng(Double.parseDouble(location.getLattitude()),
                    Double.parseDouble(location.getLongitude()));

            endAddress = routesList.get(routesList.size() - 1).getRouteLags().
                    get(routesList.get(routesList.size() - 1).
                            getRouteLags().size() - 1).getEndAddress();
            location = routesList.get(routesList.size() - 1).
                    getRouteLags().get(routesList.get(routesList.size() - 1).getRouteLags().size() - 1).getEndLocation();
            endLocation = new LatLng(Double.parseDouble(location.getLattitude()),
                    Double.parseDouble(location.getLongitude()));

            for (Routes routes : routesList) {
                List<RouteLag> routeLagList = routes.getRouteLags();
                for (RouteLag routeLag : routeLagList) {
                    List<Steps> stepsList = routeLag.getStepsList();
                    for (Steps steps : stepsList) {
                        String polyLinePoints = steps.getPolyline().getPolylinePints();
                        List<LatLng> decodedPolyLineLatLng = Utils.decodePoly(polyLinePoints);
                        routePolylineOptions.addAll(decodedPolyLineLatLng);
                    }
                }
            }
            Direction direction = new Direction(startAddress, endAddress, startLocation, endLocation, routePolylineOptions);
            directionListener.onDirectionFound(direction);

        } else if (status.equals(DirectionApiReqConstants.RESPONSE_VALUE_INVALID_REQUEST)) {
            directionListener.onError(DirectionError.INVALID_REQUEST);
        } else if (status.equals(DirectionApiReqConstants.RESPONSE_VALUE_NOT_FOUND)) {
            directionListener.onError(DirectionError.NOT_FOUND);
        } else if (status.equals(DirectionApiReqConstants.RESPONSE_VALUE_REQUEST_DENIED)) {
            directionListener.onError(DirectionError.REQUEST_DENIED);
        } else if (status.equals(DirectionApiReqConstants.RESPONSE_VALUE_ZERO_RESULTS)) {
            directionListener.onError(DirectionError.ZERO_RESULTS);
        } else if (status.equals(DirectionApiReqConstants.RESPONSE_VALUE_UNKNOWN_ERROR)) {
            directionListener.onError(DirectionError.UNKNOWN_ERROR);
        }
    }
}
