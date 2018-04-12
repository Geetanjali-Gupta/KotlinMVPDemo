package com.skeleton.mvp.ui.base.permissionsbase;

import android.support.annotation.NonNull;

import com.skeleton.mvp.ui.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Click Labs.
 */

public abstract class BasePermissionActivity extends BaseActivity implements BasePermissionView,
        EasyPermissions.PermissionCallbacks {


    @Override
    public boolean hasPermissions(final @NonNull String[] perms) {
        return EasyPermissions.hasPermissions(this, perms);
    }

    @Override
    public void requestPermission(final int requestCode, final String rationalMessage, final @NonNull String[] perms) {
        if (hasPermissions(perms)) {
            onPermissionGranted(requestCode, Arrays.asList(perms));
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, rationalMessage,
                    requestCode, perms);
        }
    }


    @Override
    public void onRequestPermissionsResult(final int requestCode, final @NonNull String[] permissions, final @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, BasePermissionActivity.this);
    }

    @Override
    public void onPermissionsGranted(final int requestCode, final @NonNull List<String> perms) {
        onPermissionGranted(requestCode, perms);
    }

    @Override
    public void onPermissionsDenied(final int requestCode, final @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
            onPermissionPermanentlyDenied(requestCode, perms);
        } else {
            onPermissionDenied(requestCode, perms);
        }
    }

    /**
     * Method called when user have granted the permission
     *
     * @param requestCode the request code for permission
     * @param perms       list of permissions granted
     */
    public abstract void onPermissionDenied(final int requestCode, final List<String> perms);

    /**
     * Method called when user denied permission
     *
     * @param requestCode the request code for permission
     * @param perms       list of permissions denied
     */
    public abstract void onPermissionGranted(final int requestCode, final List<String> perms);

    /**
     * Mehod called when some permissions are permanentaly denied
     *
     * @param requestCode the request code for permission
     * @param perms       list of permissions denied
     */
    public abstract void onPermissionPermanentlyDenied(final int requestCode, final @NonNull List<String> perms);
}
