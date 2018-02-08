package com.example.user.maptest;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 08-02-2018.
 */

public class GeocoderT extends AsyncTask <LatLng, Void, String>{
    private Context context;
    private LatLng latLng;
    private ReverseGeocoderListener callback;

    public GeocoderT(Context context) {

        this.context = context;
    }


    @Override
    protected String doInBackground(LatLng... latLngs) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        latLng = latLngs[0];
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (list!=null && list.size()>0){
                Address address = list.get(0);
                return address.getAddressLine(0)+ "," +address.getLocality();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    return null;
    }
    public  void setCallback(ReverseGeocoderListener callback){
        this.callback = callback;
    }
    public interface ReverseGeocoderListener {
        void onResult(String location, LatLng latLng);
    }
}


