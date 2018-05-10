package com.skeleton.mvp.ui.home.plans.subscriptionplans;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.responsemodel.home.plans.Plan;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.ui.base.baserecycler.ActionItemListener;
import com.skeleton.mvp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import static com.skeleton.mvp.ui.base.baserecycler.BaseRecyclerConstants.RECYCLER_VISIBLE_THRESH_HOLD;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_CATEGORY_ID;

/**
 * Developer: Geetanjali Gupta
 * Dated: 9/May/2018
 */
public class SubscriptionPlansActivity extends BaseActivity implements SubscriptionPlansView {
    private SubscriptionPlansPresenter mSubscriptionPlansPresenter;
    private SubscriptionPlansAdapter mSubscriptionPlansAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int totalPlansCount = 0, skip = 0;
    private String categoryId;
    private ArrayList<String> plansListToCompare = new ArrayList<>();
    private Button btnComparePlans;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_plans);
        initViews();
    }

    /**
     * Used to initialise views
     */
    private void initViews() {
        Bundle bundle = getIntent().getExtras();
        categoryId = bundle == null ? "" : bundle.getString(EXTRA_CATEGORY_ID);

        mSubscriptionPlansPresenter = new SubscriptionPlansPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mSubscriptionPlansPresenter.onAttach();

        btnComparePlans = findViewById(R.id.btnComparePlans);
        RecyclerView rvSubscriptionPlans = findViewById(R.id.rvSubscriptionPlans);
        linearLayoutManager = new LinearLayoutManager(this);
        rvSubscriptionPlans.setLayoutManager(linearLayoutManager);
        mSubscriptionPlansAdapter = new SubscriptionPlansAdapter(new SubscriptionPlansAdapter.ItemClickListener() {
            @Override
            public void onAddToCompareClick(final String planId) {
                if (planId == null) {
                    CommonUtil.showToast(SubscriptionPlansActivity.this, getString(R.string.compare_plans_count_error));
                } else {
                    if (!plansListToCompare.contains(planId)) {
                        plansListToCompare.add(planId);
                    }
                    if (plansListToCompare.size() > 1) {
                        btnComparePlans.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onViewDetailsClick(final int itemPosition) {

            }

            @Override
            public void onPurchaseClick(final int itemPosition) {

            }
        }, new ActionItemListener() {
            @Override
            public void onRetry() {

            }

            @Override
            public void onLoadMore() {
                mSubscriptionPlansPresenter.getAllPlansOfCategory(categoryId, skip);
            }

            @Override
            public void onItemClick(final String id) {

            }
        });
        rvSubscriptionPlans.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition()
                        == (mSubscriptionPlansAdapter.getItemCount() - RECYCLER_VISIBLE_THRESH_HOLD)) {
                    if (totalPlansCount > skip) {
                        mSubscriptionPlansAdapter.addLoaderView();
                    }
                }
            }
        });

        rvSubscriptionPlans.setAdapter(mSubscriptionPlansAdapter);
        mSubscriptionPlansPresenter.getAllPlansOfCategory(categoryId, skip);
    }

    @Override
    public void onBackPress() {
        onBackPressed();
    }

    @Override
    public void updatePlansList(final int totalCount, final List<Plan> plansList) {
        totalPlansCount = totalCount;
        mSubscriptionPlansAdapter.updateDataList((ArrayList<Plan>) plansList);
        skip = plansList.size();
    }
}
