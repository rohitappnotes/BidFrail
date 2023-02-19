package com.bidfrail.android.ui.navigationhost.fragment.bookings.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.bidfrail.android.data.local.sharedpreferences.SharedPreferencesHelper;
import com.bidfrail.android.databinding.FragmentBookingsBinding;
import com.bidfrail.android.databinding.FragmentHomeBinding;
import com.bidfrail.android.ui.base.view.BaseFragment;
import com.bidfrail.android.ui.navigationhost.fragment.bookings.viewmodel.BookingsViewModel;
import com.bidfrail.android.ui.navigationhost.fragment.home.view.HomeFragment;
import com.bidfrail.android.ui.navigationhost.fragment.home.viewmodel.HomeViewModel;
import com.bidfrail.android.ui.navigationhost.view.NavigationHostActivity;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BookingsFragment extends BaseFragment<FragmentBookingsBinding, BookingsViewModel> {


    private NavController navController;

    @Override
    protected String getFragmentClassName() {
        return BookingsFragment.class.getSimpleName();
    }

    @Override
    protected void doInOnCreate() {
    }

    @NonNull
    @Override
    protected FragmentBookingsBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentBookingsBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected BookingsViewModel getViewModel() {
        return viewModelProvider(BookingsViewModel.class);
    }

    @Override
    protected void setupToolbar() {
    }

    @Override
    protected void init() {
        navController = Navigation.findNavController(rootView);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void addTextChangedListener() {
    }

    @Override
    protected void setOnFocusChangeListener() {
    }

    @Override
    protected void setupObservers() {
    }

    @Override
    protected void setupListeners() {
    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {
    }

    @Override
    public void showProgressDialog() {
    }

    @Override
    public void hideProgressDialog() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof NavigationHostActivity) {
            NavigationHostActivity navigationHostActivity = (NavigationHostActivity) getActivity();
            navigationHostActivity.showBottomNavigation();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() instanceof NavigationHostActivity) {
            NavigationHostActivity navigationHostActivity = (NavigationHostActivity) getActivity();
            navigationHostActivity.hideBottomNavigation();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}