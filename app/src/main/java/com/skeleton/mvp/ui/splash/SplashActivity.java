package com.skeleton.mvp.ui.splash;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.skeleton.mvp.BuildConfig;
import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.ui.customview.ProgressWheel;
import com.skeleton.mvp.ui.onboarding.landing.LandingActivity;


/**
 * Developer: Click Labs
 */

public class SplashActivity extends BaseActivity implements SplashView {

    private static final int REQ_CODE_PLAY_SERVICES_RESOLUTION = 1000;
    private static final int REQ_CODE_PLAY_STORE = 1001;
    private SplashPresenter mSplashPresenter;
    private ProgressWheel progressWheel;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressWheel = findViewById(R.id.progressWheel);

        mSplashPresenter = new SplashPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mSplashPresenter.onAttach();
        mSplashPresenter.init();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashPresenter.onDetach();
    }

    @Override
    public void showNetworkError() {

        new AlertDialog.Builder(SplashActivity.this)
                .setMessage(R.string.error_internet_not_connected)
                .setCancelable(false)
                .setPositiveButton(R.string.text_retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        mSplashPresenter.registerForFcmToken();
                    }
                })
                .show();
    }


    @Override
    public boolean isPlayServiceAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                Dialog mErrorDialog = apiAvailability.getErrorDialog(this, resultCode, REQ_CODE_PLAY_SERVICES_RESOLUTION);
                mErrorDialog.setCanceledOnTouchOutside(false);
                mErrorDialog.setCancelable(false);
                mErrorDialog.show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void showDeviceRootedAlert(final SplashPresenter.RootConfirmationListener rootConfirmationListener) {

        new AlertDialog.Builder(SplashActivity.this)
                .setMessage(R.string.error_device_rooted)
                .setCancelable(false)
                .setPositiveButton(R.string.text_proceed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        rootConfirmationListener.onProceed();
                    }
                })
                .setNegativeButton(R.string.text_exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        rootConfirmationListener.onExit();
                    }
                })
                .show();

    }

    @Override
    public void navigateToHomeScreen() {
        //its called when access token login api success
        //navigate to dashboard/home screen of the application
    }

    @Override
    public void navigateToWelcomeScreen() {
        //its called when access token login fails or access token is null or empty
        //navigate to welcome screen of the application or show login or sign up options
        Intent landingActivity = new Intent(this, LandingActivity.class);
        startActivity(landingActivity);
        finish();
    }

    @Override
    public void showAppUpdateDialog(final String updateTitle, final String updateMessage, final boolean isCriticalUpdate) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SplashActivity.this);
        mBuilder.setTitle(updateTitle);
        mBuilder.setMessage(updateMessage);
        mBuilder.setPositiveButton(R.string.text_update, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, final int i) {
                openPlayStoreForAppUpdate();
            }
        });
        if (!isCriticalUpdate) {
            mBuilder.setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, final int i) {
                    mSplashPresenter.checkAccessToken();
                }
            });
        }
        mBuilder.setCancelable(false);
        mBuilder.show();
    }

    @Override
    public void showProgressBar() {
        progressWheel.setVisibility(View.VISIBLE);
        progressWheel.spin();
    }

    @Override
    public void hideProgressBar() {
        progressWheel.stopSpinning();
        progressWheel.setVisibility(View.GONE);
    }

    @Override
    public void onFcmTokenReceived() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                navigateToWelcomeScreen();
                //mSplashPresenter.checkAppVersion();
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_PLAY_SERVICES_RESOLUTION:
                mSplashPresenter.registerForFcmToken();
                break;
            case REQ_CODE_PLAY_STORE:
                mSplashPresenter.checkAppVersion();
                break;
            default:
                break;
        }
    }

    /**
     * Open play store.
     */
    private void openPlayStoreForAppUpdate() {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID));
        try {
            startActivityForResult(intent, REQ_CODE_PLAY_STORE);
        } catch (final Exception e) {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID));
            startActivityForResult(intent, REQ_CODE_PLAY_STORE);
        }
    }
}
