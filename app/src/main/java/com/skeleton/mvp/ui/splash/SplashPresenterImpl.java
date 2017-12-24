package com.skeleton.mvp.ui.splash;

import com.skeleton.mvp.BuildConfig;
import com.skeleton.mvp.data.model.AppVersion;
import com.skeleton.mvp.data.model.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.fcm.FcmTokenInterface;
import com.skeleton.mvp.fcm.MyFirebaseInstanceIdService;
import com.skeleton.mvp.ui.base.BaseInteractor;
import com.skeleton.mvp.ui.base.BasePresenterImpl;
import com.skeleton.mvp.ui.base.BaseView;
import com.skeleton.mvp.util.AppConstant;
import com.skeleton.mvp.util.RootUtil;

/**
 * Developer: Click Labs
 */

class SplashPresenterImpl extends BasePresenterImpl implements SplashPresenter, FcmTokenInterface {

    private SplashView mSplashView;
    private SplashInteractor mSplashInteractor;

    /**
     * Constructor
     *
     * @param splashView the associated splash view
     */
    SplashPresenterImpl(final SplashView splashView) {
        mSplashView = splashView;
        mSplashInteractor = new SplashInteractorImpl();
    }

    @Override
    public void init() {

        // check for root
        if (RootUtil.isDeviceRooted()) {
            mSplashView.showDeviceRootedAlert(new RootConfirmationListener() {
                @Override
                public void onProceed() {
                    registerForFcmToken();
                }

                @Override
                public void onExit() {
                    mSplashView.exit();
                }
            });
        } else {
            registerForFcmToken();
        }

    }

    @Override
    public void checkAppVersion() {
        if (isViewAttached()) {
            mSplashView.showProgress();
        }
        mSplashInteractor.getCurrentAppVersion(new BaseInteractor.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                if (isViewAttached()) {
                    final AppVersion mAppVersion = commonResponse.toResponseModel(AppVersion.class);
                    String updateTitle = mAppVersion.getUpdateTitleAtPopup();
                    String updateMessage = mAppVersion.getUpdateMessageAtPopup();
                    boolean isUpdateFound = false;
                    if (mAppVersion.getCriticalAndroidVersion() > BuildConfig.VERSION_CODE
                            || mAppVersion.getLatestAndroidVersion() > BuildConfig.VERSION_CODE) {
                        isUpdateFound = true;
                    }
                    if (isUpdateFound) {
                        mSplashView.hideProgress();
                        mSplashView.showAppUpdateDialog(updateTitle, updateMessage,
                                mAppVersion.getCriticalAndroidVersion() > BuildConfig.VERSION_CODE);
                    } else {
                        checkAccessToken();
                    }
                }
            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {
                if (isViewAttached()) {
                    mSplashView.hideProgress();
                    if (apiError != null) {
                        mSplashView.showErrorMessage(apiError.getMessage(), new BaseView.OnErrorHandleCallback() {
                            @Override
                            public void onErrorCallback() {
                                checkAppVersion();
                            }
                        });
                    } else {
                        mSplashView.showErrorMessage(parseThrowableMessage(throwable), new BaseView.OnErrorHandleCallback() {
                            @Override
                            public void onErrorCallback() {
                                checkAppVersion();
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void checkAccessToken() {
        String accessToken = mSplashInteractor.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            if (isViewAttached()) {
                mSplashView.hideProgress();
                mSplashView.navigateToWelcomeScreen();
            }
        } else {
            if (isViewAttached()) {
                mSplashView.showProgress();
            }
            mSplashInteractor.accessTokenLogin(new BaseInteractor.ApiListener() {
                @Override
                public void onSuccess(final CommonResponse commonResponse) {
                    if (isViewAttached()) {
                        mSplashView.hideProgress();
                        mSplashView.navigateToHomeScreen();
                    }
                }

                @Override
                public void onFailure(final ApiError apiError, final Throwable throwable) {
                    if (isViewAttached()) {
                        mSplashView.hideProgress();
                        if (apiError != null) {
                            if (apiError.getStatusCode() == AppConstant.SESSION_EXPIRED) {
                                mSplashInteractor.clearSessionManager();
                                mSplashView.navigateToWelcomeScreen();
                            } else {
                                mSplashView.showErrorMessage(apiError.getMessage(), new BaseView.OnErrorHandleCallback() {
                                    @Override
                                    public void onErrorCallback() {
                                        checkAccessToken();
                                    }
                                });
                            }
                        } else {
                            mSplashView.showErrorMessage(parseThrowableMessage(throwable), new BaseView.OnErrorHandleCallback() {
                                @Override
                                public void onErrorCallback() {
                                    checkAccessToken();
                                }
                            });
                        }

                    }
                }
            });
        }
    }

    @Override
    public void registerForFcmToken() {

        if (!mSplashView.isNetworkConnected()) {
            mSplashView.showNetworkError();
            return;
        }
        if (!mSplashView.isPlayServiceAvailable()) {
            return;
        }

        // register for push
        MyFirebaseInstanceIdService.setCallback(this);
    }

    @Override
    public void onTokenReceived(final String token) {
        mSplashInteractor.saveFcmToken(token);
        if (isViewAttached()) {
            mSplashView.onFcmTokenReceived();
        }
    }

    @Override
    public void onFailure() {

        if (isViewAttached()) {
            // retry
            MyFirebaseInstanceIdService.retry(this);
        }
    }
}


