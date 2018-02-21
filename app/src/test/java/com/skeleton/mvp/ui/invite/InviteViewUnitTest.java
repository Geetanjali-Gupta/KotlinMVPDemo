package com.skeleton.mvp.ui.invite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Geetanjali on 16/01/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class InviteViewUnitTest {
    @Mock
    private InviteView inviteView;


    /**
     * Used to Verify showNetworkError method is called
     */
    @Test
    public void onInviteClick_showNetWorkError() {
        inviteView.showNetworkError();
        verify(inviteView, times(1)).showNetworkError();
    }

    /**
     * Show loader
     */
    @Test
    public void onInviteClick_showLoading() {
        inviteView.showLoading();
        verify(inviteView, times(1)).showLoading();
    }

    /**
     * Hide loader
     */
    @Test
    public void onInviteClick_hideLoading() {
        inviteView.hideLoading();
        verify(inviteView, times(1)).hideLoading();
    }
}
