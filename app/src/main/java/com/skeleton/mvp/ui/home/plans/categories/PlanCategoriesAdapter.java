package com.skeleton.mvp.ui.home.plans.categories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.skeleton.mvp.R;
import com.skeleton.mvp.data.model.responsemodel.home.categories.PlanCategoriesModel;
import com.skeleton.mvp.ui.base.baserecycler.ActionItemListener;
import com.skeleton.mvp.ui.base.baserecycler.BaseRecyclerConstants;
import com.skeleton.mvp.ui.base.baserecycler.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by clicklabs on 24/04/18.
 */

public class PlanCategoriesAdapter extends BaseRecyclerAdapter {
    private ArrayList<PlanCategoriesModel> dataList;
    private ActionItemListener actionItemListener;

    /**
     * Instantiates a new Base recycler adapter.
     *
     * @param listener the listener
     */
    PlanCategoriesAdapter(final ActionItemListener listener) {
        super(listener);
        actionItemListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == BaseRecyclerConstants.VIEW_TYPE_DATA) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout_plan_category, parent, false);
            return new PlansViewHolder(view);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (getViewType(holder.getAdapterPosition()) == BaseRecyclerConstants.VIEW_TYPE_DATA) {
            PlansViewHolder plansViewHolder = (PlansViewHolder) holder;
            final PlanCategoriesModel planCategory = dataList.get(holder.getAdapterPosition());
            Glide.with(plansViewHolder.ivPlanIcon.getContext()).load(planCategory.getIcon())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()).into(plansViewHolder.ivPlanIcon);
            plansViewHolder.tvPlanName.setText(planCategory.getCategoryName());
            plansViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    actionItemListener.onItemClick(planCategory.getId());
                }
            });
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    /**
     * Update data list.
     *
     * @param recyclerModelList the recycler model list
     */
    public void updateDataList(final ArrayList<PlanCategoriesModel> recyclerModelList) {
        super.updateData(recyclerModelList);
        this.dataList = recyclerModelList;
    }


    /**
     * The type My view holder.
     */
    public class PlansViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPlanName;
        private ImageView ivPlanIcon;

        /**
         * Instantiates a new My view holder.
         *
         * @param itemView the item view
         */
        PlansViewHolder(final View itemView) {
            super(itemView);
            tvPlanName = itemView.findViewById(R.id.tvPlanName);
            ivPlanIcon = itemView.findViewById(R.id.ivPlanIcon);
        }
    }

}
