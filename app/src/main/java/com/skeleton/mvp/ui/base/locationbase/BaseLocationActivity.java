package com.skeleton.mvp.ui.base.locationbase;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.skeleton.mvp.R;
import com.skeleton.mvp.ui.base.permissionsbase.BasePermissionActivity;
import com.skeleton.mvp.ui.dialog.CustomAlertDialog;

import java.util.List;

/**
 * Created by Click Labs on 31-12-2017.
 */

public abstract class BaseLocationActivity extends BasePermissionActivity implements BaseLocationView {


    private static final int REQUEST_CHECK_SETTINGS = 2002;
    private static final int PERMISSION_REQUEST_CODE = 2001;
    private static final int MAX_CANCEL_COUNT = 3;
    private final String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mIsRepeated;
    private boolean mForceGps;
    private boolean isLocationRequested;
    private LocationCallback mLocationCallback;
    private String mRationalMessage;
    private LocationSettingsRequest.Builder locationSettingsbuilder;
    private int userCancelCount;
    private CustomAlertDialog.Builder mAlertDialog;

    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onPermissionDenied(final int requestCode, final List<String> perms) {
    }

    @Override
    public void onPermissionGranted(final int requestCode, final List<String> perms) {
        if (isLocationRequested && requestCode == PERMISSION_REQUEST_CODE) {
            if (!isLocationProviderAvailable()) {
                getLocationProviders();
            } else {
                requestLocation();
            }
        }
    }

    @Override
    public void onPermissionPermanentlyDenied(final int requestCode, final @NonNull List<String> perms) {

    }

    private void requestLocation() {
        if (mIsRepeated) {
            requestContinuesLocation();
        } else {
            requestLastKnownLocation();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestLastKnownLocation() {
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    onLocationReceived(location);
                } else {
                    requestLocationUpdate();
                }

            }
        });
    }


    @Override
    public void resolveMyLocationRequest(final LocationRequest locationRequest, @NonNull final String rationalMessage,
                                         final boolean isRepeated, final boolean forceGps) {
        mLocationRequest = locationRequest;
        mIsRepeated = isRepeated;
        mForceGps = forceGps;
        mRationalMessage = rationalMessage;
        isLocationRequested = true;
        locationSettingsbuilder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        requestLocationPermission(rationalMessage);
    }

    @Override
    public boolean hasLocationPermission() {
        return hasPermissions(permissions);
    }

    @Override
    public void requestLocationPermission(final String rationalMessage) {
        requestPermission(PERMISSION_REQUEST_CODE, rationalMessage, permissions);
    }

    /**
     * called every time location is changed
     *
     * @param location the location recevied
     */
    protected abstract void onLocationReceived(final Location location);


    /**
     * Check weather location is enabled if not enable it from settings
     */
    private void getLocationProviders() {
        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(locationSettingsbuilder.build());
        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(final LocationSettingsResponse locationSettingsResponse) {
                if (isLocationRequested) {
                    requestLocation();
                }
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(final @NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        if (userCancelCount < MAX_CANCEL_COUNT) {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(BaseLocationActivity.this,
                                    REQUEST_CHECK_SETTINGS);
                        } else {
                            showDialogForEnablingGPS();
                        }
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });

    }

    /**
     * set listener on fused location
     */
    @SuppressLint("MissingPermission")
    private void requestContinuesLocation() {

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(final LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    onLocationReceived(location);
                }
            }
        };
        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
    }


    /**
     * If Location is turned off in the device settings.
     * The result could be null of  mFusedLocationProviderClient.getLastLocation()
     * even if the last location was previously retrieved because disabling location also clears the cache.
     * <p>
     * So calling this will retrieve the last known location
     */
    @SuppressLint("MissingPermission")
    private void requestLocationUpdate() {
        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, new LocationCallback(), null);
        requestLastKnownLocation();
    }

    private void stopLocationUpdates() {
        if (mLocationCallback != null && mIsRepeated) {
            mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
        }
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (isLocationRequested && requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    userCancelCount = 0;
                    requestLocation();
                    break;
                case Activity.RESULT_CANCELED:
                    userCancelCount++;
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLocationRequested) {
            if (!hasLocationPermission() || !isLocationProviderAvailable()) {
                requestLocationPermission(mRationalMessage);
            } else if (mIsRepeated) {
                requestContinuesLocation();
            } else {
                requestLastKnownLocation();
            }
        }
    }


    /**
     * Method to check any location provider like Network or gps is available or not
     *
     * @return true if location provider is available
     */
    //CHECKSTYLE:OFF
    private boolean isLocationProviderAvailable() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mForceGps && !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }
    //CHECKSTYLE:ON

    @Override
    public void showDialogForEnablingGPS() {
        if (mAlertDialog == null) {
            mAlertDialog = new CustomAlertDialog.Builder(this);
            if (!mForceGps) {
                mAlertDialog.setMessage(R.string.msg_allow_location_settings);
            } else {
                mAlertDialog.setMessage(R.string.msg_allow_location_settings_force_gps);
            }
            mAlertDialog.setTitle(R.string.heading_permission_required);
            mAlertDialog.setCancelable(false);
            mAlertDialog.setPositiveButton("Ok", new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                @Override
                public void onClick() {
                    mAlertDialog = null;
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).show();
        }
    }
}

