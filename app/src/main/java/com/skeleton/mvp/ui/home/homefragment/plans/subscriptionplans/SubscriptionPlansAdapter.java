package com.skeleton.mvp.ui.home.homefragment.plans.subscriptionplans;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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

import static com.skeleton.mvp.util.AppConstant.APP_CURRENCY;
import static com.skeleton.mvp.util.AppConstant.RATE;

/**
 * Created by clicklabs on 09/05/18.
 */

public class SubscriptionPlansAdapter extends BaseRecyclerAdapter {
    private ArrayList<Plan> dataList;
    private ItemClickListener itemClickListener;
    private int addToCompareCount = 0;

    /**
     * Instantiates a new Base recycler adapter.
     *
     * @param itemClickListener Item click listener
     * @param listener          the listener
     */
    public SubscriptionPlansAdapter(final ItemClickListener itemClickListener, final ActionItemListener listener) {
        super(listener);
        this.itemClickListener = itemClickListener;
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
            final SubscriptionPlansViewHolder subscriptionPlansViewHolder = (SubscriptionPlansViewHolder) holder;
            final Plan plan = dataList.get(holder.getAdapterPosition());
            Glide.with(subscriptionPlansViewHolder.ivSubscriptionPlanIcon.getContext()).load(plan.getImageUrl())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()).into(subscriptionPlansViewHolder.ivSubscriptionPlanIcon);
            subscriptionPlansViewHolder.tvPlanName.setText(plan.getPlanName());
            subscriptionPlansViewHolder.tvPlanMonthlyPrice.setText(APP_CURRENCY.concat(plan.getAmountInvestedMonthly()));
            subscriptionPlansViewHolder.tvPlanMonthlyFixedRate.setText(plan.getFixedRate().concat(RATE));
            subscriptionPlansViewHolder.tvPlanMonthlyOneTimeFee.setText(APP_CURRENCY.concat(plan.getOneTimeFee()));
            subscriptionPlansViewHolder.tvAddToCompare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (addToCompareCount < 2 || plan.isAddedToCompare() && addToCompareCount > 0) {
                        plan.setAddedToCompare(!plan.isAddedToCompare());
                        handleAddToCompareTextClick(plan.isAddedToCompare(), subscriptionPlansViewHolder.tvAddToCompare,
                                subscriptionPlansViewHolder.tvAddToCompare.getContext());
                        itemClickListener.onAddToCompareClick(plan.getId());
                    } else {
                        itemClickListener.onAddToCompareClick(null);
                    }
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
    public void updateDataList(final ArrayList<Plan> recyclerModelList) {
        super.updateData(recyclerModelList);
        this.dataList = recyclerModelList;
    }


    /**
     * The type My view holder.
     */
    public class SubscriptionPlansViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPlanName, tvPlanMonthlyPrice, tvPlanMonthlyFixedRate,
                tvPlanMonthlyOneTimeFee, tvAddToCompare;
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
            tvAddToCompare = itemView.findViewById(R.id.tvAddToCompare);
            ivSubscriptionPlanIcon = itemView.findViewById(R.id.ivSubscriptionPlanIcon);
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);
            btnPurchase = itemView.findViewById(R.id.btnPurchase);
        }
    }

    /**
     * Click Listener Interface
     */
    interface ItemClickListener {

        /**
         * add to compare
         *
         * @param planId Plan Id
         */
        void onAddToCompareClick(final String planId);

        /**
         * Check plan Details
         *
         * @param itemPosition Position of item
         */
        void onViewDetailsClick(final int itemPosition);

        /**
         * Purchase plans
         *
         * @param itemPosition position Of item
         */
        void onPurchaseClick(final int itemPosition);
    }

    /**
     * @param isAddedToCompare is plan added for comparison
     * @param tv               textView
     * @param context          context
     */
    private void handleAddToCompareTextClick(final boolean isAddedToCompare, final TextView tv, final Context context) {
        tv.setText(context.getString(isAddedToCompare ? R.string.remove : R.string.add_to_compare));
        tv.setTextColor(ContextCompat.getColor(context, isAddedToCompare ? R.color.red : R.color.green));
        tv.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context,
                isAddedToCompare ? R.drawable.ic_remove : R.drawable.ic_add), null, null, null);
        addToCompareCount = isAddedToCompare ? addToCompareCount + 1 : addToCompareCount - 1;
    }
}
