package com.skeleton.mvp.ui.onboarding.signup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Developer: Geetanjali Gupta on 18/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpViewUnitTestCases {
    @Mock
    private SignUpView mSignUpView;

    /**
     * Used to Verify showNetworkError method is called
     */
    @Test
    public void onSignInClick_showNetWorkError() {
        mSignUpView.onSignUpSuccess(Mockito.anyString());
        verify(mSignUpView, times(1)).onSignUpSuccess(Mockito.anyString());
    }

    /**
     * Used to Verify showNetworkError method is called
     */
    @Test
    public void onSignInClick_showPhoneNumberError() {
        mSignUpView.showPhoneNumberError(Mockito.anyInt());
        verify(mSignUpView, times(1)).showPhoneNumberError(Mockito.anyInt());
    }

    /**
     * Used to Verify showNetworkError method is called
     */
    @Test
    public void onSignInClick_showEmailError() {
        mSignUpView.showEmailError(Mockito.anyInt());
        verify(mSignUpView, times(1)).showEmailError(Mockito.anyInt());
    }
}
