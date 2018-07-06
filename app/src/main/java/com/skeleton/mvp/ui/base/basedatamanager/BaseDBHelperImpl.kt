package com.skeleton.mvp.ui.base.basedatamanager

import com.skeleton.mvp.data.db.CommonData
import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel

/**
 * Created by clicklabs on 06/07/18.
 */
class BaseDBHelperImpl : BaseDataManager {

    override fun getAccessToken(): String {
        return CommonData.getAccessToken()
    }

    override fun getFcmToken(): String {
        return CommonData.getFcmToken()
    }

    override fun saveFcmToken(token: String) {
        CommonData.updateFcmToken(token)
    }

    override fun clearSessionManager() {
        CommonData.clearData()
    }

    override fun saveUserData(mUserData: SignInResponseModel) {
        if (mUserData.token != null && !mUserData.token.isEmpty()) {
            saveAccessToken(mUserData.token)
        }
        CommonData.saveUserData(mUserData)
    }

    override fun getUserData(): SignInResponseModel {
        return CommonData.getUserData()
    }

    override fun updateUserOtpVerification(isOtpVerificationSuccess: Boolean) {

    }

    override fun saveAccessToken(accessToken: String) {
        CommonData.saveAccessToken(accessToken)
    }

}