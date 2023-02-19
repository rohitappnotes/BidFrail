package com.bidfrail.android.ui.navigationhost.fragment.getbids.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bidfrail.android.R;
import com.bidfrail.android.databinding.FragmentGetBidsBinding;
import com.bidfrail.android.model.Service;
import com.bidfrail.android.model.SubCategory;
import com.bidfrail.android.model.SubCategoryBanner;
import com.bidfrail.android.model.SubCategoryBannerAndService;
import com.bidfrail.android.ui.base.view.BaseFragment;
import com.bidfrail.android.ui.navigationhost.fragment.booknow.view.BookNowFragment;
import com.bidfrail.android.ui.navigationhost.fragment.getbids.viewmodel.GetBidsViewModel;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.SubCategorySliderViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.service.OnServiceSelectedListener;
import com.bidfrail.android.ui.navigationhost.fragment.service.view.ServiceFragmentArgs;
import com.library.sliderview.IndicatorView.animation.type.IndicatorAnimationType;
import com.library.sliderview.SliderAnimations;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GetBidsFragment extends BaseFragment<FragmentGetBidsBinding, GetBidsViewModel> {

    private NavController navController;

    private SubCategory subCategory;
    private OnServiceSelectedListener onServiceSelectedListener;

    public GetBidsFragment(SubCategory subCategory, OnServiceSelectedListener onServiceSelectedListener) {
        this.subCategory = subCategory;
        this.onServiceSelectedListener = onServiceSelectedListener;
    }

    @Override
    protected String getFragmentClassName() {
        return GetBidsFragment.class.getSimpleName();
    }

    @Override
    protected void doInOnCreate() {
    }

    @NonNull
    @Override
    protected FragmentGetBidsBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentGetBidsBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected GetBidsViewModel getViewModel() {
        return viewModelProvider(GetBidsViewModel.class);
    }

    @Override
    protected void setupToolbar() {
    }

    @Override
    protected void init() {
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
        Observer<String> subCategoryBannerAndServiceErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressDialog();
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    showShortToast(error);
                }
            }
        };
        mViewModel.subCategoryBannerAndServiceError().observe(this, subCategoryBannerAndServiceErrorObserver);

        final Observer<SubCategoryBannerAndService> subCategoryBannerAndServiceSuccessObserver = new Observer<SubCategoryBannerAndService>() {
            @Override
            public void onChanged(SubCategoryBannerAndService subCategoryBannerAndService) {
                hideProgressDialog();
            }
        };
        mViewModel.subCategoryBannerAndServiceSuccess().observe(this, subCategoryBannerAndServiceSuccessObserver);
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
        mViewModel.subCategoryBannerAndService(subCategory.getId());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}