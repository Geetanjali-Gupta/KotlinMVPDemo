package com.skeleton.mvp.ui.base.socialsignin;

import com.skeleton.mvp.ui.base.BasePresenter;
import com.skeleton.mvp.util.facebookutil.FbUserDetails;

/**
 * Developer: Click Labs
 */
interface SocialSignInPresenter extends BasePresenter {
    /**
     * On fb login.
     *
     * @param fbUserDetails the fb user details
     */
    void onFbLogin(FbUserDetails fbUserDetails);
}
