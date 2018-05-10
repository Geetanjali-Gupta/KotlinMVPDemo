package com.skeleton.mvp.data.model.responsemodel.home.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by clicklabs on 10/05/18.
 */
public class ParentCategory {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;

}
