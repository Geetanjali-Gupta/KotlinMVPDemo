package com.skeleton.mvp.ui.splash;

import com.skeleton.mvp.database.CommonData;
import com.skeleton.mvp.fcm.FcmTokenInterface;
import com.skeleton.mvp.fcm.MyFirebaseInstanceIdService;
import com.skeleton.mvp.ui.base.BasePresenterImpl;
import com.skeleton.mvp.util.RootUtils;

/**
 * Created by cl-macmini-01 on 9/19/17.
 */

class SplashPresenterImpl extends BasePresenterImpl implements SplashPresenter, FcmTokenInterface {

    private SplashView mSplashView;

    /**
     * Constructor
     *
     * @param splashView the associated splash view
     */
    SplashPresenterImpl(final SplashView splashView) {
        mSplashView = splashView;
    }

    @Override
    public void init() {

        // check for root
        if (RootUtils.isDeviceRooted()) {
            mSplashView.showDeviceRootedAlert(new RootConfirmationListener() {
                @Override
                public void onProceed() {
                    registerForFcmToken();
                }

                @Override
                public void onExit() {
                    mSplashView.exit();
                }
            });
        } else {
            registerForFcmToken();
        }

    }

    @Override
    public void registerForFcmToken() {

        if (!mSplashView.isNetworkConnected()) {
            mSplashView.showNetworkError();
            return;
        }
        if (!mSplashView.isPlayServiceAvailable()) {
            return;
        }

        // register for push
        MyFirebaseInstanceIdService.setCallback(this);
    }

    @Override
    public void onTokenReceived(final String token) {

        CommonData.updateFcmToken(token);
        //todo decide what to launch based on token
    }

    @Override
    public void onFailure() {

        if (isViewAttached()) {
            // retry
            MyFirebaseInstanceIdService.retry(this);
        }
    }
}

