package com.stillsix.dinov3;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

/*import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;*/

public class MapsActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Tag", "Map activity loaded");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
    }
/*
*//* public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Tag", "Map activity loaded");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    *//**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *//*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng seattle = new LatLng(47.608887, -122.335502);
        LatLng dino1 = new LatLng(47.608887, -122.375502);
        LatLng dino2 = new LatLng(47.9, -122.335502);
        LatLng dino3 = new LatLng(47.508887, -122.7);
        mMap.addMarker(new MarkerOptions()
                .position(dino1)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.allosaurusicon))
                .title("Allosaurus Finding #1"));
        mMap.addMarker(new MarkerOptions()
                .position(dino2)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.allosaurusicon))
                .title("Allosaurus Finding #2"));
        mMap.addMarker(new MarkerOptions()
                .position(dino3)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.allosaurusicon))
                .title("Allosaurus Finding #3"));
        *//*CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(seattle)
                .zoom(15)
                .build();
        mMap.animateCamera(cameraPosition);*//*
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seattle,10));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Other supported types include: MAP_TYPE_NORMAL,
        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        //mMap.addMarker(new MarkerOptions()
        //        .icon(BitmapDescriptorFactory.fromResource(R.drawable.common_plus_signin_btn_icon_light))
        //        .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
        //        .position(sydney));

        // mMap.animateCamera(CameraUpdateFactory.zoomIn());
    }*/
}
