package com.skeleton.mvp.ui.invite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.skeleton.mvp.util.Log;


/**
 * Created by Geetanjali on 31/10/17.
 */

public class ReferralInstallBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.e("referrer intent>>>>>", "called........" + "");

        String referrer = intent.getStringExtra("referrer");
        Log.e("referrer>>>>>", referrer);
        //Use the referrer

    }
}