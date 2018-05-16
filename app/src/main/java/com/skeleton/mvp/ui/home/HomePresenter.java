package com.skeleton.mvp.ui.home;

import android.support.v4.app.Fragment;

import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Created by clicklabs on 23/04/18.
 */

public interface HomePresenter extends BasePresenter {
    /**
     * Used to handle home tab click
     *
     * @param intendedFragment fragment to be start
     * @param tag              fragment tag
     */
    void onHomeTabClick(final Fragment intendedFragment, final String tag);

    /**
     * Used to handle Plans tab click
     *
     * @param intendedFragment fragment to be start
     * @param tag              fragment tag
     */
    void onPlansTabClick(final Fragment intendedFragment, final String tag);

    /**
     * Used to handle Cart tab click
     *
     * @param intendedFragment fragment to be start
     * @param tag              fragment tag
     */
    void onCartTabClick(final Fragment intendedFragment, final String tag);

    /**
     * Used to handle accounts tab click
     *
     * @param intendedFragment fragment to be start
     * @param tag              fragment tag
     */
    void onAccountsTabClick(final Fragment intendedFragment, final String tag);

    /**
     * Used to handle Notification Bell icon click
     */
    void onNotificationClick();
}
