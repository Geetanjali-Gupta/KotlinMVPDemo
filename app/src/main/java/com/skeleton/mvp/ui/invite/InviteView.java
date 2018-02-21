package com.skeleton.mvp.ui.invite;

import com.skeleton.mvp.ui.base.BaseView;

/**
 * Created by Geetanjali on 15/01/18.
 */

public interface InviteView extends BaseView {
    /**
     * Used to send Intent for referral link
     *
     * @param shortLink link to be shared
     */
    void shareLink(final String shortLink);


    /**
     * Show network not available error
     */
    void showNetworkError();

}
