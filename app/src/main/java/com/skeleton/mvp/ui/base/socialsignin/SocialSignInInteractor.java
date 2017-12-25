package com.skeleton.mvp.ui.base.socialsignin;

import com.skeleton.mvp.ui.base.BaseInteractor;
import com.skeleton.mvp.util.facebookutil.FbUserDetails;

/**
 * Developer: Click Labs
 */
interface SocialSignInInteractor extends BaseInteractor {
    /**
     * Api call for fb login.
     *
     * @param fbUserDetails the fb user details
     * @param mApiListener  the m api listener
     */
    void apiCallForFbLogin(FbUserDetails fbUserDetails, ApiListener mApiListener);
}
