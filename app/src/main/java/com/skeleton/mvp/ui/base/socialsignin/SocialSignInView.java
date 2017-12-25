package com.skeleton.mvp.ui.base.socialsignin;

import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.ui.base.BaseView;
import com.skeleton.mvp.util.facebookutil.FbUserDetails;

/**
 * Developer: Click Labs
 */
interface SocialSignInView extends BaseView {
    /**
     * On fb login success.
     *
     * @param commonResponse the common response
     */
    void onFbLoginSuccess(CommonResponse commonResponse);

    /**
     * On fb user not registered.
     *
     * @param message        the message
     * @param mFbUserDetails the m fb user details
     */
    void onFbUserNotRegistered(String message, FbUserDetails mFbUserDetails);
}
