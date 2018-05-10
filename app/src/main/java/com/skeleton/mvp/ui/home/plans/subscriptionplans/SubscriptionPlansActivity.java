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
public class SubscriptionPlansActivity extends BaseActivity implements SubscriptionPlansView,
        SubscriptionPlansAdapter.ItemClickListener, ActionItemListener {

    private SubscriptionPlansPresenter mSubscriptionPlansPresenter;
    private RecyclerView rvSubscriptionPlans;
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
        rvSubscriptionPlans = findViewById(R.id.rvSubscriptionPlans);
        linearLayoutManager = new LinearLayoutManager(this);

        setUpRecyclerView();

        mSubscriptionPlansPresenter.getAllPlansOfCategory(categoryId, skip);
    }

    /**
     * Used to set Up recycler View
     */
    private void setUpRecyclerView() {
        rvSubscriptionPlans.setLayoutManager(linearLayoutManager);
        mSubscriptionPlansAdapter = new SubscriptionPlansAdapter(this, this);
        handleAddOnScrollListener();
        rvSubscriptionPlans.setAdapter(mSubscriptionPlansAdapter);
    }

    /**
     * Used to handle Add On Scroll Listeners
     */
    private void handleAddOnScrollListener() {
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
    }

    /**
     * Used to handle Add To Compare
     *
     * @param planId plan id
     */
    private void handleAddToCompare(final String planId) {
        if (planId == null) {
            CommonUtil.showToast(SubscriptionPlansActivity.this, getString(R.string.compare_plans_count_error));
        } else {
            if (!plansListToCompare.contains(planId)) {
                plansListToCompare.add(planId);
            } else {
                plansListToCompare.remove(planId);
            }
            if (plansListToCompare.size() > 1) {
                btnComparePlans.setVisibility(View.VISIBLE);
            } else {
                btnComparePlans.setVisibility(View.GONE);
            }
        }
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

    @Override
    public void onAddToCompareClick(final String planId) {
        handleAddToCompare(planId);
    }

    @Override
    public void onViewDetailsClick(final int itemPosition) {

    }

    @Override
    public void onPurchaseClick(final int itemPosition) {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void onLoadMore() {
        mSubscriptionPlansPresenter.getAllPlansOfCategory(categoryId, skip);
    }
}
