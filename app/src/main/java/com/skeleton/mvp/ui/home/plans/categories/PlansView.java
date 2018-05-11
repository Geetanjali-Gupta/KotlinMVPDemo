package com.skeleton.mvp.ui.home.plans.categories;

import com.skeleton.mvp.data.model.responsemodel.home.categories.PlanCategoriesModel;
import com.skeleton.mvp.data.model.responsemodel.home.plans.Plan;
import com.skeleton.mvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by clicklabs on 24/04/18.
 */

public interface PlansView extends BaseView {

    /**
     * Used to update plan categories
     *
     * @param totalCount         Total Count
     * @param planCategoriesList plan categories list
     */
    void updatePlanCategories(final int totalCount, final List<PlanCategoriesModel> planCategoriesList);

    /**
     * Used to navigate to subscription plans
     *
     * @param categoryId Category Id
     */
    void navigateToSubscriptionPlansActivity(final String categoryId);

    /**
     * Used to update offer plan
     *
     * @param totalCount Total Count
     * @param plansList  plan categories list
     */
    void updateOfferPlan(final int totalCount, final List<Plan> plansList);
}
