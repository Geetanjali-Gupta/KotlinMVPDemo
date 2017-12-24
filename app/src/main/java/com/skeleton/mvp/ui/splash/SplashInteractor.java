package com.skeleton.mvp.ui.splash;

import com.skeleton.mvp.ui.base.BaseInteractor;

/**
 * Developer: Click Labs
 */
interface SplashInteractor extends BaseInteractor {

    /**
     * Save fcm token.
     *
     * @param fcmToken the fcm token
     */
    void saveFcmToken(String fcmToken);

    /**
     * Access token login.
     *
     * @param listener the listener
     */
    void accessTokenLogin(ApiListener listener);

    /**
     * Gets current app version.
     *
     * @param listener the listener
     */
    void getCurrentAppVersion(ApiListener listener);

}
