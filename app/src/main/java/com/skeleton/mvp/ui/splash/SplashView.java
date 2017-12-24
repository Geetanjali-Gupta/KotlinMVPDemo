package com.skeleton.mvp.ui.splash;


import com.skeleton.mvp.ui.base.BaseView;

/**
 * Developer: Click Labs
 */
public interface SplashView extends BaseView {

    /**
     * Show network not available error
     */
    void showNetworkError();

    /**
     * Checks for play-service availability
     *
     * @return true if play services available else false
     */
    boolean isPlayServiceAvailable();

    /**
     * Exit splash
     */
    void exit();

    /**
     * Show device rooted alert and allows the user to either proceed or exit
     *
     * @param rootConfirmationListener the root confirmation listener
     */
    void showDeviceRootedAlert(final SplashPresenter.RootConfirmationListener rootConfirmationListener);

    /**
     * Navigate to home screen.
     */
    void navigateToHomeScreen();

    /**
     * Navigate to login screen.
     */
    void navigateToWelcomeScreen();

    /**
     * Show app update dialog.
     *
     * @param updateTitle      the update title
     * @param updateMessage    the update message
     * @param isCriticalUpdate the is critical update
     */
    void showAppUpdateDialog(final String updateTitle, final String updateMessage, final boolean isCriticalUpdate);

    /**
     * Show progress.
     */
    void showProgress();

    /**
     * Hide progress.
     */
    void hideProgress();


    /**
     * On fcm token received.
     */
    void onFcmTokenReceived();
}
