package com.skeleton.mvp.ui.invite;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Used to unit test Invite Presenter
 */
@RunWith(MockitoJUnitRunner.class)
public class InvitePresenterUnitTest {

    private InvitePresenterImpl invitePresenter;
    @Mock
    private InviteInteractorImpl inviteInteractor;
    @Mock
    private InviteView inviteView;
    @Mock
    private InviteInteractor.OnLinkCreatedListener onLinkCreatedListener;

    @Before
    public void initialisePresenter() throws Exception {
        invitePresenter = new InvitePresenterImpl(inviteView, inviteInteractor);
        invitePresenter.onAttach();
    }

    /**
     * Used to Verify showNetworkError method is called when internet not connected
     */
    @Test
    public void onInviteClicked_showNoNetworkErrorIfNoInternet() {
        when(inviteView.isNetworkConnected()).thenReturn(false);
        invitePresenter.onInviteClicked("", "");
        verify(inviteView, times(1)).showNetworkError();
    }

    /**
     * Used to Verify showLoading method is called when internet is connected
     */
    @Test
    public void onInviteClick_ShowLoadingIfNetworkAvailable() {
        when(inviteView.isNetworkConnected()).thenReturn(true);
        invitePresenter.onInviteClicked("", "");
        verify(inviteView, times(1)).showLoading();
    }

    /**
     * Used to verify createDynamicShortLink method called
     */
    @Test
    public void onInviteClick_CreateDynamicLink() {
        inviteInteractor.createDynamicShortLink("", "", onLinkCreatedListener);
        verify(inviteInteractor, times(1)).createDynamicShortLink("", "", onLinkCreatedListener);
    }

    /**
     * Used to verify on onSuccessful method call , hideLoading() and shareLink() method called
     */
    @Test
    public void onDynamicLinkCreated_onSuccessful() {
        invitePresenter.onSuccessful("");
        verify(inviteView, times(1)).hideLoading();
        verify(inviteView, times(1)).shareLink("");
    }

    /**
     * Used to verify on onFailure method call , hideLoading() and showErrorMessage() method called
     */
    @Test
    public void onDynamicLinkCreated_onFailure() {
        invitePresenter.onFailure("");
        verify(inviteView, times(1)).hideLoading();
        verify(inviteView, times(1)).showErrorMessage("");
    }

    /**
     * Used to verify is view attached or not
     */
    @Test
    public void isViewAttached_ReturnTrueIfViewAttached() {
        assertTrue(invitePresenter.isViewAttached());
    }

    /**
     * Used to verify is view attached or not after onDetach() called
     */
    @Test
    public void isViewAttached_ReturnFalseIfViewNotAttached() {
        invitePresenter.onDetach();
        assertFalse(invitePresenter.isViewAttached());
    }
}
