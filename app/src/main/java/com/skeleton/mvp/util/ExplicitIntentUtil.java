package com.skeleton.mvp.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.skeleton.mvp.ui.splash.SplashActivity;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by clicklabs on 11/04/18.
 */

public final class ExplicitIntentUtil {
    /**
     * Prevent instantiation
     */
    private ExplicitIntentUtil() {
    }

    /**
     * Transit forward to an Activity with some data,leaving current one alive
     *
     * @param fromContext current activity
     * @param toClass     the intended activity
     * @param reqCode     the request code to look up
     * @param extras      the data to be tunneled towards the intended activity
     */
    public static void startActivityForResult(final Activity fromContext, final Class<?> toClass,
                                              final int reqCode, final Bundle extras) {
        Intent intention = new Intent(fromContext, toClass);

        if (extras != null) {
            intention.putExtras(extras);
        }

        fromContext.startActivityForResult(intention, reqCode);
    }

    /**
     * Transit forward to an Activity with some data,leaving current one alive
     *
     * @param fromContext current activity
     * @param toClass     the intended activity
     * @param extras      the data to be tunneled towards the intended activity
     */
    public static void startActivity(final Activity fromContext, final Class<?> toClass, final Bundle extras) {
        Intent intention = new Intent(fromContext, toClass);

        if (extras != null) {
            intention.putExtras(extras);
        }

        fromContext.startActivity(intention);
    }

    /**
     * Exits the current activity
     *
     * @param fromContext the activity to be finished
     */
    public static void finishActivity(final Activity fromContext) {
        fromContext.finish();
    }

    /**
     * Exits the current activity
     *
     * @param fromContext the activity to be finished
     */
    public static void finishActivityForResultOk(final Activity fromContext) {
        fromContext.setResult(RESULT_OK, fromContext.getIntent());
        fromContext.finish();
    }

    /**
     * Exits the current activity
     *
     * @param fromContext the activity to be finished
     */
    public static void finishActivityForResultCancel(final Activity fromContext) {
        fromContext.setResult(RESULT_CANCELED, fromContext.getIntent());
        fromContext.finish();
    }

    /**
     * Restart app on session expire
     *
     * @param fromContext the activity to be finished
     */
    public static void restartAppOnSessionExpire(final Activity fromContext) {
        final Intent intent = new Intent(fromContext, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        fromContext.startActivity(intent);
    }
}
