package com.skeleton.mvp.data.db;

import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel;

import io.paperdb.Paper;

/**
 * Developer: Click Labs
 */
public final class CommonData {

    private static final String PAPER_DEVICE_TOKEN = "paper_device_token";
    private static final String PAPER_ACCESS_TOKEN = "paper_access_token";
    private static final String PAPER_USER_DATA = "paper_user_data";

    /**
     * Prevent instantiation
     */
    private CommonData() {
    }

    //=================================== FCM Token ==================================

    /**
     * Update fcm token.
     *
     * @param token the fcm token
     */
    public static void updateFcmToken(final String token) {
        Paper.book().write(PAPER_DEVICE_TOKEN, token);
    }

    /**
     * Gets fcm token.
     *
     * @return the fcm token
     */
    public static String getFcmToken() {
        return Paper.book().read(PAPER_DEVICE_TOKEN);
    }


    //=================================== Access Token ===============================

    /**
     * Save access token.
     *
     * @param accessToken the access token
     */
    public static void saveAccessToken(final String accessToken) {
        Paper.book().write(PAPER_ACCESS_TOKEN, "bearer " + accessToken);
    }

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public static String getAccessToken() {
        return Paper.book().read(PAPER_ACCESS_TOKEN);
    }
    //=================================== UserData ===============================

    /**
     * Save access token.
     *
     * @param signInResponseModel the user data
     */
    public static void saveUserData(final SignInResponseModel signInResponseModel) {
        Paper.book().write(PAPER_USER_DATA, signInResponseModel);
    }

    /**
     * Gets access token.
     *
     * @return the user data
     */
    public static SignInResponseModel getUserData() {
        return Paper.book().read(PAPER_USER_DATA);
    }

    /**
     * Clear data.
     */
    public static void clearData() {
        String fcmToken = getFcmToken();
        Paper.book().destroy();
        updateFcmToken(fcmToken);
    }

}
