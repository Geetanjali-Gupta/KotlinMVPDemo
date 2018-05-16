package com.skeleton.mvp.ui.home.homefragment.account;

import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel;
import com.skeleton.mvp.ui.base.BaseView;

/**
 * Developer: Geetanjali Gupta
 * Dated on: 16/May/18.
 */
public interface AccountView extends BaseView {

    /**
     * Used to set data with saved Info
     *
     * @param signInResponseModel data to be set
     */
    void setViewsWithSavedData(final SignInResponseModel signInResponseModel);

    /**
     * On logout success.
     *
     * @param message response message
     */
    void onLogoutSuccess(final String message);
}
