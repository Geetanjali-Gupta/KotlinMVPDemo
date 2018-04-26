package com.skeleton.mvp.ui.home.homebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.skeleton.mvp.R;
import com.skeleton.mvp.data.DataManagerImpl;
import com.skeleton.mvp.data.network.RestClient;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.ui.dialog.CustomAlertDialog;
import com.skeleton.mvp.ui.home.account.AccountFragment;
import com.skeleton.mvp.ui.home.cart.CartFragment;
import com.skeleton.mvp.ui.home.home.HomeFragment;
import com.skeleton.mvp.ui.home.plans.PlansFragment;
import com.skeleton.mvp.util.ExplicitIntentUtil;

import java.lang.reflect.Field;

import static com.skeleton.mvp.util.AppConstant.FragmentTags.ACCOUNT_FRAGMENT;
import static com.skeleton.mvp.util.AppConstant.FragmentTags.CART_FRAGMENT;
import static com.skeleton.mvp.util.AppConstant.FragmentTags.HOME_FRAGMENT;
import static com.skeleton.mvp.util.AppConstant.FragmentTags.PLANS_FRAGMENT;

/**
 * Developer: Click Labs
 */
public class HomeActivity extends BaseActivity implements HomeView, View.OnClickListener {
    private HomePresenter mHomePresenter;
    private String mCurrentFragment = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

    }

    /**
     * Used to Initialise Views
     */
    private void initViews() {
        mHomePresenter = new HomePresenterImpl(this, new DataManagerImpl(RestClient.getRetrofitBuilder()));
        mHomePresenter.onAttach();

        findViewById(R.id.ivNotification).setOnClickListener(this);

        setUpBottomNavigationView();
    }

    /**
     * It is used to set up Bottom navigation view
     */
    private void setUpBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        mHomePresenter.onHomeTabClick(new HomeFragment(), HOME_FRAGMENT);
                        break;
                    case R.id.action_plans:
                        mHomePresenter.onPlansTabClick(new PlansFragment(), PLANS_FRAGMENT);
                        break;
                    case R.id.action_cart:
                        mHomePresenter.onCartTabClick(new CartFragment(), CART_FRAGMENT);
                        break;
                    case R.id.action_account:
                        mHomePresenter.onAccountsTabClick(new AccountFragment(), ACCOUNT_FRAGMENT);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        //Used to select an item programmatically
        // bottomNavigationView.getMenu().getItem(0).setChecked(true);
        mHomePresenter.onHomeTabClick(new HomeFragment(), HOME_FRAGMENT);


    }

    @Override
    public void onBackPress() {
        new CustomAlertDialog.Builder(this)
                .setMessage(R.string.exit_confirmation_msg)
                .setNegativeButton(R.string.text_cancel, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                    @Override
                    public void onClick() {

                    }
                })
                .setPositiveButton(R.string.text_ok, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        ExplicitIntentUtil.finishActivity(HomeActivity.this);
                    }
                })
                .setCancelable(false).show();

    }

    @Override
    public void onBackPressed() {
        onBackPress();
    }

    /**
     * Used to disable the Shift Mode of Bottom Navigation
     *
     * @param view , Bottom NavigationView whose Shifting to be disabled
     */
    @SuppressLint("RestrictedApi")
    public void disableShiftMode(final BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeFragment(final Fragment intendedFragment, final String tag) {
        if (mCurrentFragment.equals(tag)) {
            return;
        }
        mCurrentFragment = tag;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, intendedFragment, tag)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onNotificationBellClick() {

    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.ivNotification:

                break;
            default:
                break;
        }
    }
}

