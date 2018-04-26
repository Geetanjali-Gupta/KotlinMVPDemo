package com.skeleton.mvp.ui.home.plans;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.responsemodel.home.PlanCategoriesModel;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseFragment;
import com.skeleton.mvp.ui.base.baserecycler.ActionItemListener;
import com.skeleton.mvp.ui.home.homebase.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Geetanjali Gupta
 * Dated: 23/04/18.
 */
public class PlansFragment extends BaseFragment implements PlansView {
    private PlansPresenter mPlansPresenter;
    private RecyclerView rvPlansCategories;
    private HomeActivity activity;
    private PlanCategoriesAdapter planCategoriesAdapter;

    @Override

    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        int numColumns = 2;
        rvPlansCategories = rootView.findViewById(R.id.rvPlansCategories);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, numColumns);
        rvPlansCategories.setLayoutManager(gridLayoutManager);
        planCategoriesAdapter = new PlanCategoriesAdapter(new ActionItemListener() {
            @Override
            public void onRetry() {

            }
        });
        rvPlansCategories.setAdapter(planCategoriesAdapter);
        rvPlansCategories.addItemDecoration(new GridDividerItemDecoration(activity));

        mPlansPresenter.getAllCategories(0);
    }

    @Override
    public void updatePlanCategories(final List<PlanCategoriesModel> planCategoriesList) {
        planCategoriesAdapter.updateDataList((ArrayList<PlanCategoriesModel>) planCategoriesList);

    }
}
