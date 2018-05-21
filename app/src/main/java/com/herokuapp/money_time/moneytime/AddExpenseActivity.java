package com.herokuapp.money_time.moneytime;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.herokuapp.money_time.moneytime.retrofit_api.App;
import com.herokuapp.money_time.moneytime.retrofit_api.JsonList;
import com.herokuapp.money_time.moneytime.retrofit_api.models.ExpenseCategoryModel;
import com.herokuapp.money_time.moneytime.retrofit_api.models.LocationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExpenseActivity extends FragmentActivity
implements  AdapterView.OnItemSelectedListener,
                    OnMapReadyCallback,
                    GoogleApiClient.OnConnectionFailedListener,
                    GoogleApiClient.ConnectionCallbacks
{

    Toast toast;
    Spinner spinnerExpenseCategory;
    Spinner spinnerLocation;
    ArrayAdapter<ExpenseCategoryModel> mExpenseCategoryAdapter;
    ArrayAdapter<LocationModel> mLocationAdapter;
//    ArrayAdapter<String> mExpenseCategoryAdapter;
//    ArrayAdapter<String> mLocationAdapter;
    TextView textView2;

    private GoogleMap mMap;
//    MapView mapView;
    Fragment map;
    private GoogleApiClient mGoogleApiClient;
    Location mLastKnownLocation;
    Boolean mLocationPermissionGranted = true;
    CameraPosition mCameraPosition;
    Location mCurrentLocation;
    LatLng mDefaultLocation = new LatLng(53.551, 9.993);

//    private void setUpMap() {
//        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapsActivity fragment = new MapsActivity();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fragmentTransaction.add(R.id.add_expemse_form, fragment);
        fragmentTransaction.commit();

//        setUpMap();


        spinnerExpenseCategory = (Spinner)findViewById(R.id.spinnerExpenseCategory);
        spinnerExpenseCategory.setOnItemSelectedListener(this);


        spinnerLocation = (Spinner)findViewById(R.id.spinnerLocation);
        spinnerLocation.setOnItemSelectedListener(this);

        mExpenseCategoryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        spinnerExpenseCategory.setAdapter(mExpenseCategoryAdapter);

        mLocationAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1
        );
        spinnerLocation.setAdapter(mLocationAdapter);
//        mExpenseCategoryAdapter.add("Category");
//        mLocationAdapter.add("Location");
        this.getExpenseCategories();
        this.getLocations();

//        textView2 = (TextView) findViewById(R.id.textView2);

//        if (savedInstanceState != null) {
//            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
//            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
//        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();
    }

    private void getExpenseCategories(){
        String authHeader = "Token " + App.getAuthToken();
        App.getApi().getExpenseCategoryData(authHeader).enqueue(new Callback<JsonList<ExpenseCategoryModel>>() {
            @Override
            public void onResponse(Call<JsonList<ExpenseCategoryModel>> call,
                                   Response<JsonList<ExpenseCategoryModel>> response) {
                try {
                    for (ExpenseCategoryModel item : response.body().getResults()) {
                        mExpenseCategoryAdapter.add(item);
                    }
                    mExpenseCategoryAdapter.notifyDataSetChanged();

                }
                catch (Exception e){
                    toast = Toast.makeText(
                            getApplicationContext(),
                            "\nERROR\n" + e.toString(),
                            Toast.LENGTH_LONG
                    );
                    toast.show();
                }
            }
            @Override
            public void onFailure(Call<JsonList<ExpenseCategoryModel>> call, Throwable t) {
                toast = Toast.makeText(
                        getApplicationContext(),
                        "\nFAIL\n" + t.toString(),
                        Toast.LENGTH_LONG
                );
                toast.show();
            }
        });
    }

    private void getLocations(){
        String authHeader = "Token " + App.getAuthToken();
        App.getApi().getLocationData(authHeader).enqueue(new Callback<JsonList<LocationModel>>() {
            @Override
            public void onResponse(Call<JsonList<LocationModel>> call,
                                   Response<JsonList<LocationModel>> response) {
                try {
                    for (LocationModel item : response.body().getResults()) {
                        mLocationAdapter.add(item);
                    }
                    mLocationAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    toast = Toast.makeText(
                            getApplicationContext(),
                            "\nERROR\n" + e.toString(),
                            Toast.LENGTH_LONG
                    );
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<JsonList<LocationModel>> call, Throwable t) {
                toast = Toast.makeText(
                        getApplicationContext(),
                        "\nFAIL\n" + t.toString(),
                        Toast.LENGTH_LONG
                );
                toast.show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Do other setup activities here too, as described elsewhere in this tutorial.

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
//                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        /*
         * Before getting the device location, you must check location
         * permission, as described earlier in the tutorial. Then:
         * Get the best and most recent location of the device, which may be
         * null in rare cases when a location is not available.
         */
        if (mLocationPermissionGranted) {
            mLastKnownLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
        }

        // Set the map's camera position to the current location of the device.
        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude()), 8));
        } else {
//            Log.d(TAG, "Current location is null. Using defaults.");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, 8));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast toast = Toast.makeText(getApplicationContext(), "onConnectionFailed", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast toast = Toast.makeText(getApplicationContext(), "onConnectionSuspended", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
