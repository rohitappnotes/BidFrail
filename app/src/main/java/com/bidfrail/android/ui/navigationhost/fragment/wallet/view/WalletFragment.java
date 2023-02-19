package com.bidfrail.android.ui.navigationhost.fragment.wallet.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.bidfrail.android.databinding.FragmentWalletBinding;
import com.bidfrail.android.ui.base.view.BaseFragment;
import com.bidfrail.android.ui.navigationhost.fragment.wallet.viewmodel.WalletViewModel;
import com.bidfrail.android.ui.navigationhost.view.NavigationHostActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WalletFragment extends BaseFragment<FragmentWalletBinding, WalletViewModel> {

    private NavController navController;

    @Override
    protected String getFragmentClassName() {
        return WalletFragment.class.getSimpleName();
    }

    @Override
    protected void doInOnCreate() {
    }

    @NonNull
    @Override
    protected FragmentWalletBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentWalletBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected WalletViewModel getViewModel() {
        return viewModelProvider(WalletViewModel.class);
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