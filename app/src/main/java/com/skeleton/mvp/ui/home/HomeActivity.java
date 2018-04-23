package com.skeleton.mvp.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.skeleton.mvp.R;
import com.skeleton.mvp.ui.base.BaseActivity;
import com.skeleton.mvp.ui.dialog.CustomAlertDialog;
import com.skeleton.mvp.util.ExplicitIntentUtil;

import java.lang.reflect.Field;

/**
 * Developer: Click Labs
 */
public class HomeActivity extends BaseActivity {

    private BottomNavigationView bottomNavigationView;

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
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        disableShiftMode(bottomNavigationView);

        setUpBottomNavigationView();
    }

    /**
     * It is used to set up Bottom navigation view
     */
    private void setUpBottomNavigationView() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //  changeFragment(new HomeFragment(), AppConstant.FragmentTags.HOME_FRAGMENT.toString());
                        break;
                    case R.id.action_plans:
                        //  changeFragment(new HomeFragment(), AppConstant.FragmentTags.HOME_FRAGMENT.toString());
                        break;
                    case R.id.action_cart:
                        //   changeFragment(new CartFragment(), AppConstant.FragmentTags.CART_FRAGMENT.toString());
                        break;
                    case R.id.action_account:
                        //  changeFragment(new HomeFragment(), AppConstant.FragmentTags.HOME_FRAGMENT.toString());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //Used to select an item programmatically
        //   bottomNavigationView.getMenu().getItem(2).setChecked(true);
        //   changeFragment(new HomeFragment(), AppConstant.FragmentTags.HOME_FRAGMENT.toString());

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
}

