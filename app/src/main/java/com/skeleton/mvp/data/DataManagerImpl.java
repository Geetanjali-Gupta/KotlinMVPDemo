package com.skeleton.mvp.data;


import com.skeleton.mvp.data.db.DbHelper;
import com.skeleton.mvp.data.db.DbHelperImpl;
import com.skeleton.mvp.data.model.SignUpModel;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.data.network.ApiHelperImpl;
import com.skeleton.mvp.util.facebookutil.FbUserDetails;

import java.io.File;

import retrofit2.Retrofit;

/**
 * Developer: Saurabh Verma
 * Dated: 09/03/18.
 */
public class DataManagerImpl implements DataManager {
    private ApiHelper mApiHelper;
    private DbHelper mDbHelper;

    /**
     * Instantiates a new Data manager.
     *
     * @param mRetrofit the m retrofit
     */
    public DataManagerImpl(final Retrofit mRetrofit) {
        this.mDbHelper = new DbHelperImpl();
        this.mApiHelper = new ApiHelperImpl(mRetrofit, mDbHelper);
    }

    @Override
    public String getAccessToken() {
        return mDbHelper.getAccessToken();
    }

    @Override
    public String getFcmToken() {
        return mDbHelper.getFcmToken();
    }

    @Override
    public void saveFcmToken(final String token) {
        mDbHelper.saveFcmToken(token);
    }

    @Override
    public void clearSessionManager() {
        mDbHelper.clearSessionManager();
    }

    /*  @Override
      public void saveUserData(final UserData mUserData) {
          mDbHelper.saveUserData(mUserData);
      }

      @Override
      public UserData getUserData() {
          return mDbHelper.getUserData();
      }
  */
    @Override
    public void updateUserOtpVerification(final boolean isOtpVerificationSuccess) {
        mDbHelper.updateUserOtpVerification(isOtpVerificationSuccess);
    }

    @Override
    public void saveAccessToken(final String accessToken) {
        mDbHelper.saveAccessToken(accessToken);
    }

    @Override
    public void apiCallToGetAppVersion(final ApiListener mApiListener) {
        mApiHelper.apiCallToGetAppVersion(mApiListener);
    }

    @Override
    public void apiCallForAccessTokenLogin(final ApiListener mApiListener) {
        mApiHelper.apiCallForAccessTokenLogin(mApiListener);
    }

    @Override
    public void apiCallForLogin(final String phoneNumber, final ApiListener mApiListener) {
        mApiHelper.apiCallForLogin(phoneNumber, mApiListener);
    }

    @Override
    public void apiCallForFbLogin(final FbUserDetails mFbUserDetails, final ApiListener mApiListener) {
        mApiHelper.apiCallForFbLogin(mFbUserDetails, mApiListener);
    }

   /* @Override
    public void apiCallToRegisterUser(final SignUpModel signUpModel, final ApiListener mApiListener) {
        mApiHelper.apiCallToRegisterUser(signUpModel, mApiListener);
    }*/

    @Override
    public void apiCallToVerifyOtp(final String otpCode, final ApiListener mApiListener) {
        mApiHelper.apiCallToVerifyOtp(otpCode, mApiListener);
    }

    @Override
    public void apiCallToResendOtp(final String phone, final ApiListener mApiListener) {
        mApiHelper.apiCallToResendOtp(phone, mApiListener);
    }

    @Override
    public void apiCallForResetPassword(final String countryDialCode, final String countryIsoCode,
                                        final String phoneNumber, final ApiListener mApiListener) {
        mApiHelper.apiCallForResetPassword(countryDialCode, countryIsoCode,
                phoneNumber, mApiListener);
    }

    @Override
    public void apiCallToVerifyForgotPasswordOtp(final String otp, final String countryDialCode,
                                                 final String countryIsoCode, final String phoneNumber, final ApiListener mApiListener) {
        mApiHelper.apiCallToVerifyForgotPasswordOtp(otp, countryDialCode, countryIsoCode,
                phoneNumber, mApiListener);
    }

    @Override
    public void apiCallToResetPassword(final String token, final String newPassword, final ApiListener mApiListener) {
        mApiHelper.apiCallToResetPassword(token, newPassword, mApiListener);
    }

    @Override
    public void apiCallToUploadLicence(final File mFile, final ApiListener mApiListener) {
        mApiHelper.apiCallToUploadLicence(mFile, mApiListener);
    }

    @Override
    public void apiCallToGetVehiclesPermittedList(final ApiListener mApiListener) {
        mApiHelper.apiCallToGetVehiclesPermittedList(mApiListener);
    }

    @Override
    public void apiCallToChangePassword(final String oldPassword, final String newPassword, final ApiListener mApiListener) {
        mApiHelper.apiCallToChangePassword(oldPassword, newPassword, mApiListener);
    }

    @Override
    public void apiCallToRegisterUser(final SignUpModel signUpModel, final ApiListener mApiListener) {
        mApiHelper.apiCallToRegisterUser(signUpModel, mApiListener);
    }


}
