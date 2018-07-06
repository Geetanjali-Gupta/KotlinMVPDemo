package com.skeleton.mvp.ui.base.basedatamanager

import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel

/**
 * Created by clicklabs on 06/07/18.
 */
interface BaseDBHelper {

    /**
     * Gets access token.
     *
     * @return the access token
     */
    fun getAccessToken(): String

    /**
     * Gets fcm token.
     *
     * @return the fcm token
     */
    fun getFcmToken(): String

    /**
     * Save fcm token.
     *
     * @param token the token
     */
    fun saveFcmToken(token: String)

    /**
     * Clear session manager.
     */
    fun clearSessionManager()

    /**
     * Save user data.
     *
     * @param mUserData the m user data
     */
    fun saveUserData(mUserData: SignInResponseModel)


    /**
     * Gets user data.
     *
     * @return the user data
     */
    fun getUserData(): SignInResponseModel


    /**
     * Update user otp verification.
     *
     * @param isOtpVerificationSuccess the is otp verification success
     */
    fun updateUserOtpVerification(isOtpVerificationSuccess: Boolean)


    /**
     * Save access token.
     *
     * @param accessToken the access token
     */
    fun saveAccessToken(accessToken: String)
}