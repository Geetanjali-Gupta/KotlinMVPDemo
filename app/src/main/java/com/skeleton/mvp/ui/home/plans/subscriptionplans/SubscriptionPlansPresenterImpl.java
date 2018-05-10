package com.skeleton.mvp.ui.home.plans.subscriptionplans;

import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.model.responsemodel.home.plans.PlansListModel;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.ui.base.BasePresenterImpl;

/**
 * Created by clicklabs on 10/05/18.
 */

class SubscriptionPlansPresenterImpl extends BasePresenterImpl implements SubscriptionPlansPresenter {
    private SubscriptionPlansView mSubscriptionPlansView;
    private DataManager mDataManager;

    /**
     * Constructor
     *
     * @param mSubscriptionPlansView the associated Plan view
     * @param mDataManager           the m data manager
     */
    SubscriptionPlansPresenterImpl(final SubscriptionPlansView mSubscriptionPlansView, final DataManager mDataManager) {
        this.mSubscriptionPlansView = mSubscriptionPlansView;
        this.mDataManager = mDataManager;
    }

    @Override
    public void getAllPlansOfCategory(final String categoryId, final int skip) {
        if (skip == 0) {
            mSubscriptionPlansView.showLoading();
        }
        mDataManager.apiCallToGetPlansOfCategory(categoryId, skip, new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                if (isViewAttached()) {
                    if (skip == 0) {
                        mSubscriptionPlansView.hideLoading();
                    }
                    PlansListModel plansListModel = commonResponse.toResponseModel(PlansListModel.class);
                    mSubscriptionPlansView.updatePlansList(plansListModel.getTotalCount(), plansListModel.getPlans());
                }
            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {

            }
        });
    }
}
