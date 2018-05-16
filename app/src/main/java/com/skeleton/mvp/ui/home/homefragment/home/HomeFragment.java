package com.skeleton.mvp.ui.home.homefragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skeleton.mvp.R;
import com.skeleton.mvp.ui.base.BaseFragment;

/**
 * Developer: Geetanjali Gupta
 * Dated: 23/04/18.
 */
public class HomeFragment extends BaseFragment {
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
