package com.skeleton.mvp.data.model.responsemodel.home.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by clicklabs on 24/04/18.
 */

public class PlanCategoriesModel {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("__v")
    @Expose
    private int v;
    @SerializedName("hasChild")
    @Expose
    private boolean hasChild;
    @SerializedName("parentID")
    @Expose
    private Object parentID;
    @SerializedName("categoryCode")
    @Expose
    private Object categoryCode;
    @SerializedName("isBlocked")
    @Expose
    private boolean isBlocked;
    @SerializedName("isDeleted")
    @Expose
    private boolean isDeleted;

    public String getCategoryName() {
        return categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }
}
