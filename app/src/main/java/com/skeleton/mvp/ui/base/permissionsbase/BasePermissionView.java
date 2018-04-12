package com.skeleton.mvp.ui.base.permissionsbase;

import android.support.annotation.NonNull;

import com.skeleton.mvp.ui.base.BaseView;

/**
 * Created by Click Labs.
 */

public interface BasePermissionView extends BaseView {

    /**
     * Method to check that activity has required permissions or not
     *
     * @param perms the array of permissions to check
     * @return true if activity has permissions
     */
    boolean hasPermissions(final @NonNull String[] perms);

    /**
     * Method to request the permission . it first check permisson is granted or not , if not then ask user for permission.
     *
     * @param requestCode     the permission request code
     * @param rationalMessage message to show the user why we required permission
     * @param perms           the array of permissions
     */
    void requestPermission(final int requestCode, final String rationalMessage, final @NonNull String[] perms);

}
