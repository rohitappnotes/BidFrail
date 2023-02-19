package com.bidfrail.android.ui.navigationhost.fragment.subservice.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import com.bidfrail.android.databinding.FragmentSubServiceBinding;
import com.bidfrail.android.model.SubCategory;
import com.bidfrail.android.ui.base.view.BaseFragment;
import com.bidfrail.android.ui.navigationhost.fragment.service.OnServiceSelectedListener;
import com.bidfrail.android.ui.navigationhost.fragment.subservice.viewmodel.SubServiceViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SubServiceFragment extends BaseFragment<FragmentSubServiceBinding, SubServiceViewModel> {

    private NavController navController;

    private SubCategory subCategory;
    private OnServiceSelectedListener onServiceSelectedListener;

    @Override
    protected String getFragmentClassName() {
        return SubServiceFragment.class.getSimpleName();
    }

    @Override
    protected void doInOnCreate() {
    }

    @NonNull
    @Override
    protected FragmentSubServiceBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSubServiceBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected SubServiceViewModel getViewModel() {
        return viewModelProvider(SubServiceViewModel.class);
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