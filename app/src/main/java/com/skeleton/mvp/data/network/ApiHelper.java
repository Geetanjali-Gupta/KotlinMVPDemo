package com.skeleton.mvp.data.network;


import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.model.SignUpModel;
import com.skeleton.mvp.util.facebookutil.FbUserDetails;

import java.io.File;

/**
 * Developer: Saurabh Verma
 * Dated: 09/03/18.
 */
public interface ApiHelper {
    /**
     * Api call to get app version.
     *
     * @param mApiListener the m api listener
     */
    void apiCallToGetAppVersion(ApiListener mApiListener);

    /**
     * Api call for access token login.
     *
     * @param mApiListener the m api listener
     */
    void apiCallForAccessTokenLogin(ApiListener mApiListener);


    /**
     * Api call for login.
     *
     * @param phoneNumber  the phone number
     * @param mApiListener the m api listener
     */
    void apiCallForLogin(String phoneNumber, ApiListener mApiListener);


    /**
     * Api call for fb login.
     *
     * @param mFbUserDetails the m fb user details
     * @param mApiListener   the m api listener
     */
    void apiCallForFbLogin(FbUserDetails mFbUserDetails, ApiListener mApiListener);


    /**
     * Api call to register user.
     *
     * @param signUpModel  the sign up model
     * @param mApiListener the m api listener
     */
    //void apiCallToRegisterUser(SignUpModel signUpModel, ApiListener mApiListener);

    /**
     * Api call to verify otp.
     *
     * @param otpCode      the otp code
     * @param mApiListener the m api listener
     */
    void apiCallToVerifyOtp(String otpCode, ApiListener mApiListener);

    /**
     * Api call to resend otp.
     *
     * @param phone        the phone
     * @param mApiListener the m api listener
     */
    void apiCallToResendOtp(String phone, ApiListener mApiListener);

    /**
     * Api call for reset password.
     *
     * @param countryDialCode the country dial code
     * @param countryIsoCode  the country iso code
     * @param phoneNumber     the phone number
     * @param mApiListener    the m api listener
     */
    void apiCallForResetPassword(String countryDialCode, String countryIsoCode, String phoneNumber, ApiListener mApiListener);

    /**
     * Api call to verify forgot password otp.
     *
     * @param otp             the otp
     * @param countryDialCode the country dial code
     * @param countryIsoCode  the country iso code
     * @param phoneNumber     the phone number
     * @param mApiListener    the m api listener
     */
    void apiCallToVerifyForgotPasswordOtp(String otp, String countryDialCode,
                                          String countryIsoCode, String phoneNumber, ApiListener mApiListener);

    /**
     * Api call to reset password.
     *
     * @param token        the token
     * @param newPassword  the new password
     * @param mApiListener the m api listener
     */
    void apiCallToResetPassword(String token, String newPassword, ApiListener mApiListener);

    /**
     * Api call to upload licence.
     *
     * @param mFile        the m file
     * @param mApiListener the m api listener
     */
    void apiCallToUploadLicence(File mFile, ApiListener mApiListener);

    /**
     * Api call to get vehicles permitted list.
     *
     * @param mApiListener the m api listener
     */
    void apiCallToGetVehiclesPermittedList(ApiListener mApiListener);


    /**
     * Api call to change password.
     *
     * @param oldPassword  the old password
     * @param newPassword  the new password
     * @param mApiListener the m api listener
     */
    void apiCallToChangePassword(String oldPassword, String newPassword, ApiListener mApiListener);

    /**
     * Api call to register user.
     *
     * @param signUpModel  the sign up model
     * @param mApiListener the m api listener
     */
    void apiCallToRegisterUser(SignUpModel signUpModel, ApiListener mApiListener);

    /**
     * The interface Api listener.
     */
    interface ApiListener {
        /**
         * On success.
         *
         * @param commonResponse the common response
         */
        void onSuccess(CommonResponse commonResponse);

        /**
         * On failure.
         *
         * @param apiError  the api error
         * @param throwable the throwable
         */
        void onFailure(ApiError apiError, Throwable throwable);
    }
}
