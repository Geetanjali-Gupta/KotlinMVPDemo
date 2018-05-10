package com.skeleton.mvp.ui.home.plans.subscriptionplans;

import com.skeleton.mvp.data.model.responsemodel.home.plans.Plan;
import com.skeleton.mvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by clicklabs on 09/05/18.
 */

public interface SubscriptionPlansView extends BaseView {
    /**
     * Used to update plan categories
     *
     * @param totalCount Total Count
     * @param plansList  plan categories list
     */
    void updatePlansList(final int totalCount, final List<Plan> plansList);
}
