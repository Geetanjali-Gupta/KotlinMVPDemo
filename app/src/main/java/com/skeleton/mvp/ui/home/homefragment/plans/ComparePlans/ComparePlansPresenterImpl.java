package com.skeleton.mvp.ui.home.homefragment.plans.ComparePlans;

import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.ui.base.BasePresenterImpl;

/**
 * Developer:Geetanjali Gupta
 * Dated: 14/May/18.
 */
public class ComparePlansPresenterImpl extends BasePresenterImpl implements ComparePlansPresenter {
    private DataManager mDataManager;
    private ComparePlansView mComparePlansView;

    /**
     * Constructor
     *
     * @param mComparePlansView the associated Compare Plans View
     * @param mDataManager      Data Manager
     */
    ComparePlansPresenterImpl(final ComparePlansView mComparePlansView, final DataManager mDataManager) {
        this.mComparePlansView = mComparePlansView;
        this.mDataManager = mDataManager;
    }

    @Override
    public void comparePlans(final String planId1, final String planId2) {
        mComparePlansView.showLoading();
        mDataManager.apiCallToComparePlans(planId1, planId2, new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                if (isViewAttached()) {
                    mComparePlansView.hideLoading();
                }
            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {
                if (isViewAttached()) {
                    mComparePlansView.hideLoading();
                    if (apiError != null) {
                        mComparePlansView.showErrorMessage(apiError.getMessage());
                    } else {
                        mComparePlansView.showErrorMessage(parseThrowableMessage(throwable));
                    }
                }
            }
        });
    }
}
