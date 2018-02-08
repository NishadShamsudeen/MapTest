package com.example.user.maptest;

import android.graphics.Color;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GeocoderT.ReverseGeocoderListener {
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        LatLng cochi = new LatLng(9.9312, 76.2673);
        googleMap.addMarker(new MarkerOptions().position(cochi).title("Cochin"));
        googleMap.addCircle(new CircleOptions().center(cochi).strokeColor(Color.BLUE).radius(1000));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cochi));
        googleMap.setOnMapClickListener(this);


    }

    @Override
    public void onMapClick(LatLng latLng) {
        GeocoderT geocoder = new GeocoderT(this);
        geocoder.setCallback(this);
        geocoder.execute(latLng);

    }

    @Override
    public void onResult(String location, LatLng latLng) {
        googleMap.clear();
        googleMap.addMarker(new MarkerOptions().position(latLng).title(location)).showInfoWindow();
    }
}
