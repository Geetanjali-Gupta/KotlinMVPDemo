package com.skeleton.mvp.ui.onboarding.landing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by clicklabs on 16/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LandingViewUnitTestCases {
    @Mock
    private LandingView mLandingView;

    /**
     * Used to Verify navigateToSignInScreen method is called
     */
    @Test
    public void onSignInClick_navigateToSignInScreen() {
        mLandingView.navigateToSignInScreen();
        verify(mLandingView, times(1)).navigateToSignInScreen();
    }

    /**
     * Used to Verify navigateToSignUpScreen method is called
     */
    @Test
    public void onSignInClick_navigateToSignUpScreen() {
        mLandingView.navigateToSignUpScreen();
        verify(mLandingView, times(1)).navigateToSignUpScreen();
    }
}
