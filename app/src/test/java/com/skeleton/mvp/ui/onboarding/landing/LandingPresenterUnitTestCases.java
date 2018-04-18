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
    private LandingPresenterImpl mLandingPresenterImpl;
    @Mock
    private DataManagerImpl mDataManager;
    @Mock
    private LandingView mLandingView;

    @Before
    public void initialisePresenter() throws Exception {
        mLandingPresenterImpl = new LandingPresenterImpl(mLandingView, mDataManager);
        mLandingPresenterImpl.onAttach();
    }

    /**
     * Used to Verify navigateToSignInScreen method is called when onSignInClicked is connected
     */
    @Test
    public void onSignInClick_navigateToSignInScreen() {
        mLandingPresenterImpl.onSignInClicked();
        verify(mLandingView, times(1)).navigateToSignInScreen();
    }

    /**
     * Used to Verify navigateToSignUpScreen method is called when onSignUpClicked is connected
     */
    @Test
    public void onSignInClick_navigateToSignUpScreen() {
        mLandingPresenterImpl.onSignUpClicked();
        verify(mLandingView, times(1)).navigateToSignUpScreen();
    }
}
