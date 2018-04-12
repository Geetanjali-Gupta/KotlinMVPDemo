package com.skeleton.mvp.ui.base.locationbase;

import android.support.annotation.NonNull;

import com.google.android.gms.location.LocationRequest;
import com.skeleton.mvp.ui.base.permissionsbase.BasePermissionView;

/**
 * Created by Click Labs on 31-12-2017.
 */

public interface BaseLocationView extends BasePermissionView {

    /**
     * Method to retrieve last known location on activity
     * Location permission are automatically handled by this method
     *
     * @param locationRequest the Location Request
     * @param rationalMessage the message to show user
     * @param isRepeated      true if you want continues location update
     * @param forceGps        true if you want to get location from Gps
     */
    void resolveMyLocationRequest(final LocationRequest locationRequest, @NonNull final String rationalMessage,
                                  final boolean isRepeated, final boolean forceGps);

    /**
     * Check location permission
     *
     * @return true if granted
     */
    boolean hasLocationPermission();

    /**
     * Request Only Location Permission FINE_LOCATION and COARSE_LOCATION
     *
     * @param rationalPermission the message to show the user
     */
    void requestLocationPermission(String rationalPermission);


    /**
     * Show Dialog to Open Location setings
     */
    void showDialogForEnablingGPS();


}
