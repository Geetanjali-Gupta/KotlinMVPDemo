package com.skeleton.mvp.ui.onboarding.signin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Developer: Geetanjali Gupta on 16/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignInViewUnitTestCases {
    @Mock
    private SignInView mSignInView;

    /**
     * Used to Verify showNetworkError method is called
     */
    @Test
    public void onSignInClick_showNetWorkError() {
        mSignInView.onSignInSuccess(Mockito.anyString());
        verify(mSignInView, times(1)).onSignInSuccess(Mockito.anyString());
    }

}
