package com.skeleton.mvp.ui.home.homefragment.plans.categories;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.responsemodel.home.categories.PlanCategoriesModel;
import com.skeleton.mvp.data.model.responsemodel.home.plans.Plan;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseFragment;
import com.skeleton.mvp.ui.base.baserecycler.ActionItemListener;
import com.skeleton.mvp.ui.home.HomeActivity;
import com.skeleton.mvp.ui.home.homefragment.plans.subscriptionplans.SubscriptionPlansActivity;
import com.skeleton.mvp.util.ExplicitIntentUtil;

import java.util.ArrayList;
import java.util.List;

import static com.skeleton.mvp.ui.base.baserecycler.BaseRecyclerConstants.RECYCLER_VISIBLE_THRESH_HOLD;
import static com.skeleton.mvp.ui.base.baserecycler.BaseRecyclerConstants.VIEW_TYPE_LOADER;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_CATEGORY_ID;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_DESCRIPTION;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_IMAGE_URL;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_PLAN_ID;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_TITLE;

/**
 * Developer: Geetanjali Gupta
 * Dated: 23/April/18.
 */
public class PlansFragment extends BaseFragment implements PlansView, ActionItemListener,
        PlanCategoriesAdapter.ItemClickListener, ViewPager.OnPageChangeListener {

    private static final int NUM_COLS = 2;
    private GridLayoutManager gridLayoutManager;
    private ViewPager vpOffers;
    private PlansPresenter mPlansPresenter;
    private RecyclerView rvPlansCategories;
    private HomeActivity activity;
    private PlanCategoriesAdapter planCategoriesAdapter;
    private int totalCategoriesCount = 0, skip = 0;

    private LinearLayout llSliderDots;
    private int totalDotsCount;
    private ImageView[] dots;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plans, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    /**
     * Used to initialise views
     *
     * @param rootView parent view
     */
    private void initViews(final View rootView) {
        mPlansPresenter = new PlansPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mPlansPresenter.onAttach();

        rvPlansCategories = rootView.findViewById(R.id.rvPlansCategories);
        vpOffers = rootView.findViewById(R.id.vpOffers);
        llSliderDots = rootView.findViewById(R.id.llSliderDots);
        setUpRecyclerView();

        mPlansPresenter.getAllOffers();
    }

    /**
     * Used to set Up recycler View
     */
    private void setUpRecyclerView() {
        gridLayoutManager = new GridLayoutManager(activity, NUM_COLS);
        setSpanSizeForLoadingView();

        rvPlansCategories.setLayoutManager(gridLayoutManager);
        planCategoriesAdapter = new PlanCategoriesAdapter(this, this);
        rvPlansCategories.setAdapter(planCategoriesAdapter);
        rvPlansCategories.addItemDecoration(new GridDividerItemDecoration(activity));

        handleAddOnScrollListener();
    }

    /**
     * Used to set span size to 2 for loading view so that it be in center of the screen
     */
    private void setSpanSizeForLoadingView() {
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if (planCategoriesAdapter.getItemViewType(planCategoriesAdapter.getItemCount() - 1)
                        == VIEW_TYPE_LOADER) {
                    return position == planCategoriesAdapter.getItemCount() - 1 ? NUM_COLS : 1;
                } else {
                    return 1;
                }
            }
        });
    }

    /**
     * Used to handle Add On Scroll Listeners
     */
    private void handleAddOnScrollListener() {
        rvPlansCategories.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition()
                        == (planCategoriesAdapter.getItemCount() - RECYCLER_VISIBLE_THRESH_HOLD)) {
                    if (totalCategoriesCount > skip) {
                        planCategoriesAdapter.addLoaderView();
                    }
                }
            }
        });
    }

    @Override
    public void updatePlanCategories(final int totalCount, final List<PlanCategoriesModel> planCategoriesList) {
        totalCategoriesCount = totalCount;
        planCategoriesAdapter.updateDataList((ArrayList<PlanCategoriesModel>) planCategoriesList);
        skip = planCategoriesList.size();
    }

    @Override
    public void navigateToSubscriptionPlansActivity(final String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CATEGORY_ID, categoryId);
        ExplicitIntentUtil.startActivity(activity, SubscriptionPlansActivity.class, bundle);
    }

    @Override
    public void updateOfferPlan(final int totalCount, final List<Plan> plansList) {
        totalDotsCount = totalCount;
        vpOffers.setVisibility(totalCount == 0 ? View.GONE : View.VISIBLE);
        llSliderDots.setVisibility(totalCount == 0 ? View.GONE : View.VISIBLE);

        if (totalDotsCount > 0) {
            List<BaseFragment> fragments = new ArrayList<>(totalCount);
            for (int i = 0; i < totalCount; i++) {
                Plan plan = plansList.get(i);
                Bundle bundle = new Bundle();
                bundle.putString(EXTRA_PLAN_ID, plan.getId());
                bundle.putString(EXTRA_TITLE, plan.getOfferDescription().getTitle());
                bundle.putString(EXTRA_DESCRIPTION, plan.getOfferDescription().getSubTitle());
                bundle.putString(EXTRA_IMAGE_URL, plan.getBannerImageUrl());
                fragments.add(OffersFragment.getInstance(bundle));
            }

            PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(), fragments);
            vpOffers.setAdapter(pagerAdapter);
            setUpDotsBelowPager();
            vpOffers.addOnPageChangeListener(this);
        }
        mPlansPresenter.getAllCategories(skip);
    }

    /**
     * Used to set Up Dots Below view pager
     */
    private void setUpDotsBelowPager() {
        dots = new ImageView[totalDotsCount];
        for (int i = 0; i < totalDotsCount; i++) {
            dots[i] = new ImageView(activity);
            dots[i].setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.page_unselected));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins((int) activity.getResources().getDimension(R.dimen.spacing_xsmall),
                    0, (int) activity.getResources().getDimension(R.dimen.spacing_xsmall), 0);
            llSliderDots.addView(dots[i], params);
        }
        if (totalDotsCount > 0) {
            dots[0].setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.page_selected));
        }
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void onLoadMore() {
        mPlansPresenter.getAllCategories(skip);
    }

    @Override
    public void onItemClick(final String id) {
        navigateToSubscriptionPlansActivity(id);
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        for (int i = 0; i < totalDotsCount; i++) {
            dots[i].setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.page_unselected));
        }
        dots[position].setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.page_selected));
    }

    @Override
    public void onPageScrollStateChanged(final int state) {

    }
}
