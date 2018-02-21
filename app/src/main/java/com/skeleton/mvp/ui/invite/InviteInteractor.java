package com.skeleton.mvp.ui.invite;

import com.skeleton.mvp.ui.base.BaseInteractor;

/**
 * Created by Geetanjali on 15/01/18.
 */

public interface InviteInteractor extends BaseInteractor {

    /**
     * Used to create dynamic link
     *
     * @param key                   google api key
     * @param longLink              longLink to be shorten
     * @param onLinkCreatedListener listener to be called
     */
    void createDynamicShortLink(final String key, final String longLink, final OnLinkCreatedListener onLinkCreatedListener);

    /**
     * Interface to be called on Link Created
     */
    interface OnLinkCreatedListener {
        /**
         * Called when short link created successfully
         *
         * @param shortLink shortLink created from Long Link
         */
        void onSuccessful(final String shortLink);

        /**
         * Called when short Link not created
         *
         * @param errorMessage error Message why short Link not created
         */
        void onFailure(final String errorMessage);
    }
}
