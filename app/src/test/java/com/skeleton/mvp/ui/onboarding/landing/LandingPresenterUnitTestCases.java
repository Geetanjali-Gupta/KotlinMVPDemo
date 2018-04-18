package com.skeleton.mvp.ui.onboarding.landing;

import com.skeleton.mvp.data.DataManagerImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Developer: Geetanjali Gupta on 16/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LandingPresenterUnitTestCases {
    private LandingPresenterImpl landingPresenterImpl;
    @Mock
    private DataManagerImpl dataManagerImpl;
    @Mock
    private LandingView landingView;

    @Before
    public void initialisePresenter() throws Exception {
        landingPresenterImpl = new LandingPresenterImpl(landingView, dataManagerImpl);
        landingPresenterImpl.onAttach();
    }

    /**
     * Used to Verify navigateToSignInScreen method is called when onSignInClicked is connected
     */
    @Test
    public void onSignInClick_navigateToSignInScreen() {
        landingPresenterImpl.onSignInClicked();
        verify(landingView, times(1)).navigateToSignInScreen();
    }

    /**
     * Used to Verify navigateToSignUpScreen method is called when onSignUpClicked is connected
     */
    @Test
    public void onSignInClick_navigateToSignUpScreen() {
        landingPresenterImpl.onSignUpClicked();
        verify(landingView, times(1)).navigateToSignUpScreen();
    }
}
