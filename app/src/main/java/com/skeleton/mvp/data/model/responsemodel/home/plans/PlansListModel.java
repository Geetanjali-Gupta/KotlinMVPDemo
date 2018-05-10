package com.skeleton.mvp.data.model.responsemodel.home.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by clicklabs on 10/05/18.
 */

public class PlansListModel {
    @SerializedName("totalCount")
    @Expose
    private int totalCount;
    @SerializedName("plans")
    @Expose
    private List<Plan> plans = null;

    public int getTotalCount() {
        return totalCount;
    }

    public List<Plan> getPlans() {
        return plans;
    }
}