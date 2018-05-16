package com.skeleton.mvp.ui.home.homefragment.account;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.model.responsemodel.onboarding.signin.SignInResponseModel;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseFragment;
import com.skeleton.mvp.ui.dialog.CustomAlertDialog;
import com.skeleton.mvp.util.CommonUtil;
import com.skeleton.mvp.util.ExplicitIntentUtil;

/**
 * Developer: Geetanjali Gupta
 * Dated: 23/April/18.
 */
public class AccountFragment extends BaseFragment implements AccountView, View.OnClickListener {
    private AccountPresenter mAccountPresenter;
    private TextView tvUserName, tvUserEmailId;
    private Activity activity;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        initView(rootView);
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
     * @param rootView parent View
     */
    private void initView(final View rootView) {
        mAccountPresenter = new AccountPresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mAccountPresenter.onAttach();

        tvUserName = rootView.findViewById(R.id.tvUserName);
        tvUserEmailId = rootView.findViewById(R.id.tvUserEmailId);

        rootView.findViewById(R.id.rlMyProfile).setOnClickListener(this);
        rootView.findViewById(R.id.rlContactUs).setOnClickListener(this);
        rootView.findViewById(R.id.rlTermsOfServices).setOnClickListener(this);
        rootView.findViewById(R.id.rlLogout).setOnClickListener(this);

        mAccountPresenter.setDataFromSavedUserData();
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.rlMyProfile:
                break;
            case R.id.rlContactUs:
                break;
            case R.id.rlTermsOfServices:
                break;
            case R.id.rlLogout:
                showLogoutConfirmationDialog();
                break;
            default:
                break;
        }

    }

    @Override
    public void setViewsWithSavedData(final SignInResponseModel signInResponseModel) {
        String name = signInResponseModel.getName();
        if (name == null) {
            tvUserName.setVisibility(View.GONE);
        } else {
            tvUserName.setVisibility(View.VISIBLE);
            tvUserName.setText(signInResponseModel.getName());
        }
        tvUserEmailId.setText(signInResponseModel.getEmail());
    }

    @Override
    public void onLogoutSuccess(final String message) {
        CommonUtil.showToast(activity, message);
        ExplicitIntentUtil.restartAppOnSessionExpire(activity);
    }

    /**
     * Used to show Logout Confirmation Dialog
     */
    private void showLogoutConfirmationDialog() {
        new CustomAlertDialog.Builder(activity)
                .setMessage(R.string.logout_confirmation_msg)
                .setNegativeButton(R.string.text_cancel, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                    @Override
                    public void onClick() {

                    }
                })
                .setPositiveButton(R.string.text_ok, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        mAccountPresenter.onLogoutClick();
                    }
                })
                .setCancelable(false).show();
    }
}
