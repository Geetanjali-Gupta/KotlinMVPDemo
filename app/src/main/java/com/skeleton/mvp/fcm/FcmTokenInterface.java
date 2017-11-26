package com.skeleton.mvp.fcm;

/**
 * Developer: Saurabh Verma
 * Dated: 19-02-2017.
 */
public interface FcmTokenInterface {
    /**
     * On token received.
     *
     * @param token the token
     */
    void onTokenReceived(String token);

    /**
     * On failure.
     */
    void onFailure();
}