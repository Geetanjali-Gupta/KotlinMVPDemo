package com.skeleton.mvp.ui.home.plans.subscriptionplans;

import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Created by clicklabs on 10/05/18.
 */

public interface SubscriptionPlansPresenter extends BasePresenter {
    /**
     * Used get all Plans categories
     *
     * @param categoryId Category Id
     * @param skip       skip Count for pagination
     */
    void getAllPlansOfCategory(final String categoryId, final int skip);
}
