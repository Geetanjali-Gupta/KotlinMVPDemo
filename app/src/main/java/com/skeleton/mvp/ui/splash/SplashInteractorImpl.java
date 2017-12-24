package com.skeleton.mvp.ui.splash;

import android.os.Handler;

import com.skeleton.mvp.data.db.CommonData;
import com.skeleton.mvp.ui.base.BaseInteractorImpl;
import com.skeleton.mvp.util.CommonUtil;

/**
 * Developer: Click Labs
 */
class SplashInteractorImpl extends BaseInteractorImpl implements SplashInteractor {

    private static final int TEMP_MOCK_RESPONSE_DELAY = 3000;

    @Override
    public void saveFcmToken(final String fcmToken) {
        CommonData.updateFcmToken(fcmToken);
    }

    @Override
    public void accessTokenLogin(final ApiListener listener) {

    }

    @Override
    public void getCurrentAppVersion(final ApiListener listener) {
        //todo replace it with app version api call
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess(CommonUtil.getMockResponse("app_version"));
            }
        }, TEMP_MOCK_RESPONSE_DELAY);
    }
}
