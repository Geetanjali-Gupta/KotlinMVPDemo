package com.skeleton.mvp.data.db;


import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel;

/**
 * Developer: Saurabh Verma
 * Dated: 09/03/18.
 */
public class DbHelperImpl implements DbHelper {
    @Override
    public String getAccessToken() {
        return CommonData.getAccessToken();
    }

    @Override
    public String getFcmToken() {
        return CommonData.getFcmToken();
    }

    @Override
    public void saveFcmToken(final String token) {
        CommonData.updateFcmToken(token);
    }

    @Override
    public void clearSessionManager() {
        CommonData.clearData();
    }

    @Override
    public void saveUserData(final SignInResponseModel mUserData) {
        if (mUserData != null) {
            if (mUserData.getToken() != null
                    && !mUserData.getToken().isEmpty()) {
                saveAccessToken(mUserData.getToken());
            }
            CommonData.saveUserData(mUserData);
        }
    }

    @Override
    public SignInResponseModel getUserData() {
        return CommonData.getUserData();
    }

    @Override
    public void updateUserOtpVerification(final boolean isOtpVerificationSuccess) {

    }

    @Override
    public void saveAccessToken(final String accessToken) {
        CommonData.saveAccessToken(accessToken);
    }

/*    @Override
    public void saveUserData(final UserData mUserData) {
        if (mUserData != null) {
            if (mUserData.getToken() != null
                    && !mUserData.getToken().isEmpty()) {
                saveAccessToken(mUserData.getToken());
            }
            CommonData.saveUserData(mUserData);
        }
    }

    @Override
    public UserData getUserData() {
        return CommonData.getUserData();
    }

    @Override
    public void updateUserOtpVerification(final boolean isOtpVerificationSuccess) {
        final UserData mUserData = getUserData();
        if (mUserData != null) {
            mUserData.setPhoneVerified(isOtpVerificationSuccess);
            saveUserData(mUserData);
        }
    }

    @Override
    public void saveAccessToken(final String accessToken) {
        CommonData.updateAccessToken(accessToken);
    }*/
}
