package com.skeleton.mvp.ui.home.plans;

import com.skeleton.mvp.data.DataManager;
import com.skeleton.mvp.data.model.responsemodel.base.CommonResponse;
import com.skeleton.mvp.data.model.responsemodel.home.PlanCategoriesModel;
import com.skeleton.mvp.data.network.ApiError;
import com.skeleton.mvp.data.network.ApiHelper;
import com.skeleton.mvp.ui.base.BasePresenterImpl;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Developer: Geetanjali Gupta
 * Date:  24/04/18.
 */

public class PlansPresenterImpl extends BasePresenterImpl implements PlansPresenter {
    private PlansView mPlansView;
    private DataManager mDataManager;

    /**
     * Constructor
     *
     * @param mPlansView   the associated Plan view
     * @param mDataManager the m data manager
     */
    PlansPresenterImpl(final PlansView mPlansView, final DataManager mDataManager) {
        this.mPlansView = mPlansView;
        this.mDataManager = mDataManager;
    }

    @Override
    public void getAllCategories(final int skip) {
        mPlansView.showLoading();
        mDataManager.apiCallToGetPlanCategories(skip, new ApiHelper.ApiListener() {
            @Override
            public void onSuccess(final CommonResponse commonResponse) {
                if (isViewAttached()) {
                    mPlansView.hideLoading();
                    PlanCategoriesModel[] planCategoriesModel = commonResponse.toResponseModel(PlanCategoriesModel[].class);
                    mPlansView.updatePlanCategories(new ArrayList<>(Arrays.asList(planCategoriesModel)));
                }
            }

            @Override
            public void onFailure(final ApiError apiError, final Throwable throwable) {

            }
        });
    }
}
