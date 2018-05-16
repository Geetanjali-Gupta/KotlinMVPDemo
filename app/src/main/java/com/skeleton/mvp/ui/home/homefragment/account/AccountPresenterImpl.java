package com.skeleton.mvp.ui.home.homefragment.account;

import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.ui.base.BasePresenterImpl;

/**
 * Developer: Geetanjali Gupta
 * Dated on: 16/May/18.
 */
public class AccountPresenterImpl extends BasePresenterImpl implements AccountPresenter {
    private AccountView mAccountView;
    private DataManager mDataManager;

    /**
     * Constructor
     *
     * @param mAccountView associated View
     * @param mDataManager Data Manager
     */
    AccountPresenterImpl(final AccountView mAccountView, final DataManager mDataManager) {
        this.mAccountView = mAccountView;
        this.mDataManager = mDataManager;
    }

    @Override
    public void setDataFromSavedUserData() {
        mAccountView.setViewsWithSavedData(mDataManager.getUserData());
    }

    @Override
    public void onLogoutClick() {
        mAccountView.showLoading();
        mDataManager.apiCallToLogoutUser(new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                onLogoutSuccess(commonResponse);
            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {
                if (isViewAttached()) {
                    mAccountView.hideLoading();
                    if (apiError != null) {
                        mAccountView.showErrorMessage(apiError.getMessage());
                    } else {
                        mAccountView.showErrorMessage(parseThrowableMessage(throwable));
                    }
                }
            }
        });
    }

    @Override
    public void onLogoutSuccess(final CommonResponse commonResponse) {
        if (isViewAttached()) {
            mAccountView.hideLoading();
            mDataManager.clearSessionManager();
            mAccountView.onLogoutSuccess(commonResponse.getMessage());
        }
    }
}
