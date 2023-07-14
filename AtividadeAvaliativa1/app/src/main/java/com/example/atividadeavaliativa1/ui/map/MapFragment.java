package com.example.atividadeavaliativa1.ui.map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.atividadeavaliativa1.MainActivity;
import com.example.atividadeavaliativa1.R;
import com.example.atividadeavaliativa1.databinding.FragmentMapBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapFragment extends Fragment/* implements OnMapReadyCallback*/{

    private FragmentMapBinding binding;
    private Button LocationButton;
    private LocationRequest locationRequest;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map_image);
        LocationButton = binding.buttonLocation;
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(2000);

        LocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCurrentLocation();
            }
        });
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // When map is loaded
                //Atualizar pin do mapa para onde clica
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        // When clicked on map
                        // Initialize marker options
                        MarkerOptions markerOptions=new MarkerOptions();
                        // Set position of marker
                        markerOptions.position(latLng);
                        // Set title of marker
                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                        // Remove all marker
                        googleMap.clear();
                        // Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                        // Add marker on map
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if (isGPSEnabled()) {
                    getCurrentLocation();
                }
                else {
                    turnOnGPS();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                getCurrentLocation();
            }
        }
    }

    private void getCurrentLocation() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(getActivity())
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(getActivity())
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        int index = locationResult.getLocations().size() - 1;
                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                        double longitude = locationResult.getLocations().get(index).getLongitude();

                                        /*AddressText.setText("Latitude: "+ latitude + "\n" + "Longitude: "+ longitude);*/
                                        SupportMapFragment supportMapFragment=(SupportMapFragment)
                                                getChildFragmentManager().findFragmentById(R.id.map_image);
                                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                            @Override
                                            public void onMapReady(GoogleMap googleMap) {
                                                // When map is loaded
                                                //Atualizar pin do mapa para onde clica
                                                        LatLng latLng = new LatLng(latitude, longitude);
                                                        // When clicked on map
                                                        // Initialize marker options
                                                        MarkerOptions markerOptions=new MarkerOptions();
                                                        // Set position of marker
                                                        markerOptions.position(latLng);
                                                        // Set title of marker
                                                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                                                        // Remove all marker
                                                        googleMap.clear();
                                                        // Animating to zoom the marker
                                                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                                                        // Add marker on map
                                                        googleMap.addMarker(markerOptions);

                                            }
                                        });
                                    }
                                }
                            }, Looper.getMainLooper());

                } else {
                    turnOnGPS();
                    LocationServices.getFusedLocationProviderClient(getActivity())
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(getActivity())
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        int index = locationResult.getLocations().size() - 1;
                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                        double longitude = locationResult.getLocations().get(index).getLongitude();

                                        /*AddressText.setText("Latitude: "+ latitude + "\n" + "Longitude: "+ longitude);*/
                                        SupportMapFragment supportMapFragment=(SupportMapFragment)
                                                getChildFragmentManager().findFragmentById(R.id.map_image);
                                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                            @Override
                                            public void onMapReady(GoogleMap googleMap) {
                                                // When map is loaded
                                                //Atualizar pin do mapa para onde clica
                                                LatLng latLng = new LatLng(latitude, longitude);
                                                // When clicked on map
                                                // Initialize marker options
                                                MarkerOptions markerOptions=new MarkerOptions();
                                                // Set position of marker
                                                markerOptions.position(latLng);
                                                // Set title of marker
                                                markerOptions.title(latLng.latitude+" : "+latLng.longitude);
                                                // Remove all marker
                                                googleMap.clear();
                                                // Animating to zoom the marker
                                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                                                // Add marker on map
                                                googleMap.addMarker(markerOptions);

                                            }
                                        });
                                    }
                                }
                            }, Looper.getMainLooper());

                }

            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    //Ativa o GPS
    private void turnOnGPS() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED: //Dispositivo com localização

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(getActivity(), 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE: //Dispositivo sem localização
                            break;
                    }
                }
            }
        });

    }

    //Verifica se o GSP está ativo
    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
