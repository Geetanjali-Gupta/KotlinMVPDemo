package com.skeleton.mvp.ui.home.homefragment.account;

import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Developer: Geetanjali Gupta
 * Dated on: 16/May/18.
 */
public interface AccountPresenter extends BasePresenter {

    /**
     * Used to set User Data
     */
    void setDataFromSavedUserData();

    /**
     * Used for api hit for logout
     */
    void onLogoutClick();

    /**
     * On logout success.
     *
     * @param commonResponse the common response
     */
    void onLogoutSuccess(CommonResponse commonResponse);
}
