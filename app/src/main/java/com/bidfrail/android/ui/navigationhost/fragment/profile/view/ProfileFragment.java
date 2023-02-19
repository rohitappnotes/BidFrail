package com.bidfrail.android.ui.navigationhost.fragment.profile.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.bidfrail.android.databinding.FragmentProfileBinding;
import com.bidfrail.android.ui.base.view.BaseFragment;
import com.bidfrail.android.ui.navigationhost.fragment.profile.viewmodel.ProfileViewModel;
import com.bidfrail.android.ui.navigationhost.view.NavigationHostActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel> {

    private NavController navController;

    @Override
    protected String getFragmentClassName() {
        return ProfileFragment.class.getSimpleName();
    }

    @Override
    protected void doInOnCreate() {
    }

    @NonNull
    @Override
    protected FragmentProfileBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentProfileBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected ProfileViewModel getViewModel() {
        return viewModelProvider(ProfileViewModel.class);
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