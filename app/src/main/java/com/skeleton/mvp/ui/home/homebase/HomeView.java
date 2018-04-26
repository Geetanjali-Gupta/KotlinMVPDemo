package com.skeleton.mvp.ui.home.homebase;

import android.support.v4.app.Fragment;

/**
 * Created by clicklabs on 23/04/18.
 */

public interface HomeView {

    /**
     * Used to change Fragment on tab click
     *
     * @param intendedFragment fragment to be start
     * @param tag              fragment tag
     */
    void changeFragment(final Fragment intendedFragment, final String tag);

    /**
     * Used to handle Notification bell Icon Click
     */
    void onNotificationBellClick();

}
