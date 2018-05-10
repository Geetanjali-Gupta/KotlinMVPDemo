package com.skeleton.mvp.ui.home.plans.subscriptionplans;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.skeleton.mvp.R;
import com.skeleton.mvp.data.model.responsemodel.home.plans.Plan;
import com.skeleton.mvp.ui.base.baserecycler.ActionItemListener;
import com.skeleton.mvp.ui.base.baserecycler.BaseRecyclerConstants;
import com.skeleton.mvp.ui.base.baserecycler.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by clicklabs on 09/05/18.
 */

public class SubscriptionPlansAdapter extends BaseRecyclerAdapter {
    private ArrayList<Plan> dataList;

    /**
     * Instantiates a new Base recycler adapter.
     *
     * @param listener the listener
     */
    public SubscriptionPlansAdapter(final ActionItemListener listener) {
        super(listener);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == BaseRecyclerConstants.VIEW_TYPE_DATA) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout_subscription_plans, parent, false);
            return new SubscriptionPlansViewHolder(view);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (getViewType(holder.getAdapterPosition()) == BaseRecyclerConstants.VIEW_TYPE_DATA) {
            SubscriptionPlansViewHolder subscriptionPlansViewHolder = (SubscriptionPlansViewHolder) holder;
            Plan plan = dataList.get(holder.getAdapterPosition());

            Glide.with(subscriptionPlansViewHolder.ivSubscriptionPlanIcon.getContext()).load(plan.getImageUrl())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()).into(subscriptionPlansViewHolder.ivSubscriptionPlanIcon);
            subscriptionPlansViewHolder.tvPlanName.setText(plan.getPlanName());
            subscriptionPlansViewHolder.tvPlanMonthlyPrice.setText("$" + plan.getAmountInvestedMonthly());
            subscriptionPlansViewHolder.tvPlanMonthlyFixedRate.setText(plan.getFixedRate() + "%");
            subscriptionPlansViewHolder.tvPlanMonthlyOneTimeFee.setText("$" + plan.getOneTimeFee());
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    /**
     * Update data list.
     *
     * @param recyclerModelList the recycler model list
     */
    public void updateDataList(final ArrayList<Plan> recyclerModelList) {
        super.updateData(recyclerModelList);
        this.dataList = recyclerModelList;
    }


    /**
     * The type My view holder.
     */
    public class SubscriptionPlansViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPlanName, tvPlanMonthlyPrice, tvPlanMonthlyFixedRate, tvPlanMonthlyOneTimeFee;
        private ImageView ivSubscriptionPlanIcon;
        private Button btnViewDetails, btnPurchase;

        /**
         * Instantiates a new My view holder.
         *
         * @param itemView the item view
         */
        SubscriptionPlansViewHolder(final View itemView) {
            super(itemView);
            tvPlanName = itemView.findViewById(R.id.tvPlanName);
            tvPlanMonthlyPrice = itemView.findViewById(R.id.tvPlanMonthlyPrice);
            tvPlanMonthlyFixedRate = itemView.findViewById(R.id.tvPlanMonthlyFixedRate);
            tvPlanMonthlyOneTimeFee = itemView.findViewById(R.id.tvPlanMonthlyOneTimeFee);
            ivSubscriptionPlanIcon = itemView.findViewById(R.id.ivSubscriptionPlanIcon);
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);
            btnPurchase = itemView.findViewById(R.id.btnPurchase);
        }
    }
}
