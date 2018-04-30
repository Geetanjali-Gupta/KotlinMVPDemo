package com.skeleton.mvp.data.model.responsemodel.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by clicklabs on 26/04/18.
 */

public class PlansModel {
    @SerializedName("totalCount")
    @Expose
    private int totalCount;
    @SerializedName("categories")
    @Expose
    private List<PlanCategoriesModel> categories = null;

    public int getTotalCount() {
        return totalCount;
    }

    public List<PlanCategoriesModel> getCategories() {
        return categories;
    }
}
