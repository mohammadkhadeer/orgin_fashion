package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;


public class FragmentLocation extends Fragment {
    static final LatLng Your_Location = new LatLng(25.2616938, 55.3818661); //Your LatLong
    private GoogleMap mMap;

    SupportMapFragment supportMapFragment;
    RelativeLayout fragment_location_see_in_google_maps;

    public FragmentLocation() {
    }

    String lat, lng;
    View view;

//
    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            lat = getArguments().getString("lat");
            lng = getArguments().getString("lng");
        }
        super.onAttach(context);
        //set userName in followID just as init value well need it to insert in
        //fireBase as object after added well updated
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_location, container, false);
        init();
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        potLoaction();
//        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(final GoogleMap map) {
//                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                    @Override
//                    public void onMapClick(LatLng latLng) {
////                        MarkerOptions markerOptions = new MarkerOptions();
////                        markerOptions.position(latLng);
////                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);
////                        map.clear();
////                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
////                        map.addMarker(markerOptions);
//
//                    }
//                });
//            }
//        });
        actionListenerToSendUserToGoogleMaps();
        return view;
    }

    private void actionListenerToSendUserToGoogleMaps() {
        fragment_location_see_in_google_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:25.2616938,55.3818661");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    private void init() {
        fragment_location_see_in_google_maps = (RelativeLayout) view.findViewById(R.id.fragment_location_see_in_google_maps);
    }

    private void potLoaction() {
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Your_Location, 15));  //move camera to location
                if (mMap != null) {
                    Marker hamburg = mMap.addMarker(new MarkerOptions().position(Your_Location).title("test"));
                }
                // Rest of the stuff you need to do with the map
            }
        });
    }

}
