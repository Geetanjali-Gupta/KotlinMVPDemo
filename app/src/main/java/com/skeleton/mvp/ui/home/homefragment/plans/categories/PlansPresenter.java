package com.skeleton.mvp.ui.home.homefragment.plans.categories;

import com.skeleton.mvp.ui.base.BasePresenter;

/**
 * Created by clicklabs on 24/04/18.
 */

public interface PlansPresenter extends BasePresenter {

    /**
     * Used get all Plans categories
     *
     * @param skip skip Count for pagination
     */
    void getAllCategories(final int skip);

    /**
     * Used get all Offers
     */
    void getAllOffers();
}
