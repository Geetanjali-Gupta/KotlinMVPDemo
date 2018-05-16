package com.skeleton.mvp.ui.home.homefragment.plans.ComparePlans;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;

import static com.skeleton.mvp.util.IntentConstant.EXTRA_PLAN_ID_1;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_PLAN_ID_2;

/**
 * Developer: Geetanjali Gupta
 * Dated: 11/May/2018
 */
public class ComparePlansActivity extends BaseActivity implements ComparePlansView, View.OnClickListener {
    private ComparePlansPresenter comparePlansPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_plans);
        initViews();
    }

    private void initViews() {
        comparePlansPresenter = new ComparePlansPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        comparePlansPresenter.onAttach();

        setUpToolbar();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String planId1 = bundle.getString(EXTRA_PLAN_ID_1);
            String planId2 = bundle.getString(EXTRA_PLAN_ID_2);
            comparePlansPresenter.comparePlans(planId1, planId2);
        }


    }

    /**
     * Used to set Up Toolbar
     */
    private void setUpToolbar() {
        findViewById(R.id.ivBack).setOnClickListener(this);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(getString(R.string.comparing_plans));
    }


    @Override
    public void onBackPress() {
        onBackPressed();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                onBackPress();
                break;
            default:
                break;
        }
    }
}
