package com.bidfrail.android.ui.navigationhost.fragment.service.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.bidfrail.android.R;
import com.bidfrail.android.databinding.FragmentServiceBinding;
import com.bidfrail.android.model.Service;
import com.bidfrail.android.model.SubCategory;
import com.bidfrail.android.ui.base.view.BaseFragment;
import com.bidfrail.android.ui.navigationhost.fragment.booknow.view.BookNowFragment;
import com.bidfrail.android.ui.navigationhost.fragment.getbids.view.GetBidsFragment;
import com.bidfrail.android.ui.navigationhost.fragment.home.view.HomeFragmentDirections;
import com.bidfrail.android.ui.navigationhost.fragment.service.OnServiceSelectedListener;
import com.bidfrail.android.ui.navigationhost.fragment.service.viewmodel.ServiceViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ServiceFragment extends BaseFragment<FragmentServiceBinding, ServiceViewModel> implements OnServiceSelectedListener {

    private NavController navController;

    private SubCategory subCategory;

    @Override
    protected String getFragmentClassName() {
        return ServiceFragment.class.getSimpleName();
    }

    @Override
    protected void doInOnCreate() {
        if (getArguments() != null) {
            /* Also you can observe transaction live data */
            ServiceFragmentArgs args = ServiceFragmentArgs.fromBundle(getArguments());
            subCategory = args.getSubCategory();
        }
    }

    @NonNull
    @Override
    protected FragmentServiceBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentServiceBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected ServiceViewModel getViewModel() {
        return viewModelProvider(ServiceViewModel.class);
    }

    @Override
    protected void setupToolbar() {
    }

    @Override
    protected void init() {
        navController = Navigation.findNavController(rootView);
        pushFragment(new GetBidsFragment(subCategory, ServiceFragment.this));
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
        mViewBinding.tabOneLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewBinding.tabOneLinearLayout.setBackgroundResource(R.drawable.tab_layout_selected);
                mViewBinding.tabTwoLinearLayout.setBackgroundResource(R.drawable.tab_layout_unselected);

                mViewBinding.tabOneTextView.setTextColor(getResources().getColor(R.color.white));
                mViewBinding.tabOneImageView.setColorFilter(getResources().getColor(R.color.white));

                mViewBinding.tabTwoTextView.setTextColor(getResources().getColor(R.color.black));
                mViewBinding.tabTwoImageView.setColorFilter(getResources().getColor(R.color.black));

                pushFragment(new GetBidsFragment(subCategory, ServiceFragment.this));
            }
        });

        mViewBinding.tabTwoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewBinding.tabTwoLinearLayout.setBackgroundResource(R.drawable.tab_layout_selected);
                mViewBinding.tabOneLinearLayout.setBackgroundResource(R.drawable.tab_layout_unselected);

                mViewBinding.tabTwoTextView.setTextColor(getResources().getColor(R.color.white));
                mViewBinding.tabTwoImageView.setColorFilter(getResources().getColor(R.color.white));

                mViewBinding.tabOneTextView.setTextColor(getResources().getColor(R.color.black));
                mViewBinding.tabOneImageView.setColorFilter(getResources().getColor(R.color.black));

                pushFragment(new BookNowFragment(subCategory, ServiceFragment.this));
            }
        });
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

    /**
     * Method to push any fragment into given id.
     *
     * @param fragment An instance of Fragment to show into the given id.
     */
    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onSelected(Service service, String from) {
        ServiceFragmentDirections.ActionServiceFragmentToSubServiceFragment action = ServiceFragmentDirections.actionServiceFragmentToSubServiceFragment(service.getServiceName(), service);
        navController.navigate((NavDirections) action);
    }
}