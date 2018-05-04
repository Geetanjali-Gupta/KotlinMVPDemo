package com.skeleton.mvp.ui.splash;

import com.skeleton.mvp.BuildConfig;
import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel;
import com.skeleton.mvp.data.model.responsemodel.onboarding.splash.AppVersion;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.fcm.FcmTokenInterface;
import com.skeleton.mvp.fcm.MyFirebaseInstanceIdService;
import com.skeleton.mvp.ui.base.BasePresenterImpl;
import com.skeleton.mvp.ui.base.BaseView;
import com.skeleton.mvp.util.AppConstant;
import com.skeleton.mvp.util.Log;
import com.skeleton.mvp.util.RootUtil;

/**
 * Developer: Click Labs
 */

class SplashPresenterImpl extends BasePresenterImpl implements SplashPresenter, FcmTokenInterface {

    private SplashView mSplashView;
    private DataManager mDataManager;

    /**
     * Constructor
     *
     * @param splashView   the associated splash view
     * @param mDataManager the m data manager
     */
    SplashPresenterImpl(final SplashView splashView, final DataManagerImpl mDataManager) {
        this.mSplashView = splashView;
        this.mDataManager = mDataManager;
    }

    @Override
    public void init() {

        // check for root
        if (RootUtil.isDeviceRooted()) {
            Log.e("Device", " Rooted");
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
            Log.e("Device", "Not Rooted");
        }

    }

    @Override
    public void checkAppVersion() {
        if (isViewAttached()) {
            mSplashView.showProgressBar();
        }
        mDataManager.apiCallToGetAppVersion(new ApiHelper.ApiListener() {
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
                        mSplashView.hideProgressBar();
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
                    mSplashView.hideProgressBar();
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
        String accessToken = mDataManager.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            if (isViewAttached()) {
                mSplashView.hideProgressBar();
                mSplashView.navigateToWelcomeScreen();
            }
        } else {
            if (isViewAttached()) {
                mSplashView.showProgressBar();
            }

            mDataManager.apiCallForAccessTokenLogin(new ApiHelper.ApiListener() {
                @Override
                public void onSuccess(final CommonResponse commonResponse) {
                    if (isViewAttached()) {
                        mSplashView.hideProgressBar();
                        final SignInResponseModel signInResponseModel = commonResponse.toResponseModel(SignInResponseModel.class);
                       /* if (signInResponseModel.isPhoneVerified()) {
                            mSplashView.navigateToHomeScreen();
                        } else {*/
                        mSplashView.navigateToOTPVerificationScreen(signInResponseModel.getContacts().get(0).getMobile());
                        //  }
                    }
                }

                @Override
                public void onFailure(final ApiError apiError, final Throwable throwable) {
                    if (isViewAttached()) {
                        mSplashView.hideProgressBar();
                        if (apiError != null) {
                            if (apiError.getStatusCode() == AppConstant.SESSION_EXPIRED) {
                                mDataManager.clearSessionManager();
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
        mDataManager.saveFcmToken(token);
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


