package com.skeleton.mvp.ui.home.homefragment.plans.ComparePlans;

import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Developer:Geetanjali Gupta
 * Dated: 14/May/18.
 */
public interface ComparePlansPresenter extends BasePresenter {

    /**
     * Api hit to compare plans
     *
     * @param planId1 First plan id
     * @param planId2 Second plan id
     */
    void comparePlans(final String planId1, final String planId2);
}
