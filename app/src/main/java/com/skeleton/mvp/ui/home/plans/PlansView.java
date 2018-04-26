package com.skeleton.mvp.ui.home.plans;

import com.skeleton.mvp.data.model.responsemodel.home.PlanCategoriesModel;
import com.skeleton.mvp.ui.base.BaseView;

import java.util.List;

/**
 * Created by clicklabs on 24/04/18.
 */

public interface PlansView extends BaseView {

    /**
     * Used to update plan categories
     *
     * @param planCategoriesList plan categories list
     */
    void updatePlanCategories(List<PlanCategoriesModel> planCategoriesList);
}
