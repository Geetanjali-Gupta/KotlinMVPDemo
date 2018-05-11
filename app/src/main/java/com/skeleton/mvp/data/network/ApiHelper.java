package com.skeleton.mvp.data.network;


import com.skeleton.mvp.data.model.requestmodel.SignUpModel;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;

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
     * Api call to verify otp.
     *
     * @param otpCode      the otp code
     * @param mobileNumber phone number to be verified
     * @param mApiListener the m api listener
     */
    void apiCallToVerifyOtp(final String mobileNumber, String otpCode, ApiListener mApiListener);

    /**
     * Api call to resend otp.
     *
     * @param phone        the phone
     * @param mApiListener the m api listener
     */
    void apiCallToResendOtp(String phone, ApiListener mApiListener);


    /**
     * Api call to register user.
     *
     * @param signUpModel  the sign up model
     * @param mApiListener the m api listener
     */
    void apiCallToRegisterUser(SignUpModel signUpModel, ApiListener mApiListener);

    /**
     * Api call to register user.
     *
     * @param skip         skip Count for pagination
     * @param mApiListener the m api listener
     */
    void apiCallToGetPlanCategories(int skip, ApiListener mApiListener);

    /**
     * Api call to register user.
     *
     * @param mApiListener the m api listener
     */
    void apiCallToGetAllOffers(ApiListener mApiListener);

    /**
     * Api call to register user.
     *
     * @param categoryId   category Id
     * @param skip         skip Count for pagination
     * @param mApiListener the m api listener
     */
    void apiCallToGetPlansOfCategory(String categoryId, int skip, ApiListener mApiListener);

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
