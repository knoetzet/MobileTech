package com.example.g13k0093.mobiletechproj;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Marker marker;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        tv = (TextView) findViewById(R.id.textView9);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng SA = new LatLng(-30.5595,  22.9375);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(SA));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                double roundOffLat = Math.round(point.latitude * 1000.0) / 1000.0;
                double roundOffLong = Math.round(point.longitude * 1000.0) / 1000.0;
                String roundCoOrd = String.valueOf(roundOffLat) + " , " + String.valueOf(roundOffLong);
                tv.setText(roundCoOrd);
                if(marker!=null){
                    marker.remove();
                }
                marker = mMap.addMarker(new MarkerOptions()
                        .position(point)
                        .draggable(true).visible(true));
            }
        });
    }
}
