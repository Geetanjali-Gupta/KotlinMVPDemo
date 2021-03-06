package com.skeleton.mvp.data.db;


/**
 * Developer: Saurabh Verma
 * Dated: 09/03/18.
 */
public interface DbHelper {
    /**
     * Gets access token.
     *
     * @return the access token
     */
    String getAccessToken();

    /**
     * Gets fcm token.
     *
     * @return the fcm token
     */
    String getFcmToken();

    /**
     * Save fcm token.
     *
     * @param token the token
     */
    void saveFcmToken(String token);

    /**
     * Clear session manager.
     */
    void clearSessionManager();

    /**
     * Save user data.
     *
     * @param mUserData the m user data
     */
    //  void saveUserData(UserData mUserData);


    /**
     * Gets user data.
     *
     * @return the user data
     */
    // UserData getUserData();


    /**
     * Update user otp verification.
     *
     * @param isOtpVerificationSuccess the is otp verification success
     */
    void updateUserOtpVerification(boolean isOtpVerificationSuccess);


    /**
     * Save access token.
     *
     * @param accessToken the access token
     */
    void saveAccessToken(String accessToken);
}
