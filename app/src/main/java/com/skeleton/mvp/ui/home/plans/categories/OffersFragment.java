package com.skeleton.mvp.ui.home.plans.categories;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.skeleton.mvp.ui.base.BaseFragment;
import com.skeleton.mvp.util.Log;

import static com.skeleton.mvp.util.IntentConstant.EXTRA_DESCRIPTION;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_IMAGE_URL;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_PLAN_ID;
import static com.skeleton.mvp.util.IntentConstant.EXTRA_TITLE;


/**
 * Developer: Geetanjali Gupta
 * Dated: 11/May/2018
 */
public class OffersFragment extends BaseFragment implements View.OnClickListener {

    private Activity activity;
    private ImageView ivBannerImage;
    private TextView tvBannerHeader;
    private TextView tvBannerDesc;
    private Button btnViewDetails;
    private String planId;


    /**
     * get a new instance of this fragment using this static method.
     *
     * @param bundle pass bundle to setArguments to fragment.
     * @return new instance of fragment.
     */
    public static OffersFragment getInstance(final Bundle bundle) {
        OffersFragment fragment = new OffersFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);
        initView(rootView);
        if (getArguments() != null) {
            setUpData(getArguments());
        }
        return rootView;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    /**
     * Used to Initialise Views
     *
     * @param rootView Parent View
     */
    private void initView(final View rootView) {
        ivBannerImage = rootView.findViewById(R.id.ivBannerImage);
        tvBannerHeader = rootView.findViewById(R.id.tvBannerHeader);
        tvBannerDesc = rootView.findViewById(R.id.tvBannerDesc);
        rootView.findViewById(R.id.btnViewDetails).setOnClickListener(this);
    }

    /**
     * Used to Set Up Data
     *
     * @param dataToSet Bundle passed
     */
    public void setUpData(final Bundle dataToSet) {
        planId = dataToSet.getString(EXTRA_PLAN_ID);
        tvBannerHeader.setText(dataToSet.getString(EXTRA_TITLE));
        tvBannerDesc.setText(dataToSet.getString(EXTRA_DESCRIPTION));
        Glide.with(activity).load(dataToSet.getString(EXTRA_IMAGE_URL))
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()).into(ivBannerImage);

    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnViewDetails:
                Log.e("Plan Id", ">>>>>>>  " + planId);
                break;
            default:
                break;
        }
    }
}
