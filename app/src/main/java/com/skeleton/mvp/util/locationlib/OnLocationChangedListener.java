package com.skeleton.mvp.util.locationlib;

import android.location.Location;

/**
 * Created by Aman Singh on 31-12-2017.
 */

public interface OnLocationChangedListener {

    /**
     * Method called when ever location is updated or changed
     *
     * @param location the location
     */
    void onLocationChanged(Location location);
}
