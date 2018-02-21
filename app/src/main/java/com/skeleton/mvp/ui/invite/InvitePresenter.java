package com.skeleton.mvp.ui.invite;

import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Created by Geetanjali on 15/01/18.
 */

public interface InvitePresenter extends BasePresenter {

    /**
     * Used to create link on Invite Clicked
     *
     * @param key      google Api key
     * @param longLink longLink to be shorten
     */
    void onInviteClicked(final String key, final String longLink);
}
