package com.skeleton.mvp.ui.invite;

import com.skeleton.mvp.ui.base.BasePresenterImpl;

/**
 * Created by Geetanjali on 15/01/18.
 */

class InvitePresenterImpl extends BasePresenterImpl implements InvitePresenter, InviteInteractor.OnLinkCreatedListener {
    private InviteView inviteView;
    private InviteInteractor inviteInteractor;

    /**
     * Constructor
     *
     * @param inviteView           the associated Invite view
     * @param inviteInteractorImpl the associated invite Interactor
     */
    InvitePresenterImpl(final InviteView inviteView, final InviteInteractorImpl inviteInteractorImpl) {
        this.inviteView = inviteView;
        this.inviteInteractor = inviteInteractorImpl;
    }

    /**
     * Called when short link created successfully
     *
     * @param shortLink shortLink created from Long Link
     */
    @Override
    public void onSuccessful(final String shortLink) {
        if (isViewAttached()) {
            inviteView.hideLoading();
            inviteView.shareLink(shortLink);
        }
    }

    /**
     * Called when short Link not created
     *
     * @param errorMessage error Message why short Link not created
     */
    @Override
    public void onFailure(final String errorMessage) {
        if (isViewAttached()) {
            inviteView.hideLoading();
            inviteView.showErrorMessage(errorMessage);
        }
    }

    /**
     * Used to create link on Invite Clicked
     *
     * @param key      google Api key
     * @param longLink longLink to be shorten
     */
    @Override
    public void onInviteClicked(final String key, final String longLink) {
        if (inviteView.isNetworkConnected()) {
            inviteView.showLoading();
            inviteInteractor.createDynamicShortLink(key, longLink, this);
        } else {
            inviteView.showNetworkError();
        }
    }
}
