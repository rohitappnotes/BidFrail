package com.bidfrail.android.ui.splash.view;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.bidfrail.android.AppConstants;
import com.bidfrail.android.R;
import com.bidfrail.android.data.local.sharedpreferences.SharedPreferencesHelper;
import com.bidfrail.android.databinding.ActivitySplashBinding;
import com.bidfrail.android.model.User;
import com.bidfrail.android.ui.base.view.BaseActivity;
import com.bidfrail.android.ui.loginregister.view.LoginRegisterActivity;
import com.bidfrail.android.ui.navigationhost.view.NavigationHostActivity;
import com.bidfrail.android.ui.splash.viewmodel.SplashViewModel;
import com.library.utilities.activity.ActivityUtils;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    private Handler handler;
    private Runnable runnable;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected String getActivityClassName() {
        return SplashActivity.class.getSimpleName();
    }

    @Override
    protected void doBeforeSetContentView() {
    }

    @Override
    protected void doInOnCreate() {
    }

    @NonNull
    @Override
    protected ActivitySplashBinding getViewBinding(LayoutInflater inflater) {
        return ActivitySplashBinding.inflate(inflater);
    }

    @NonNull
    @Override
    protected SplashViewModel getViewModel() {
        return viewModelProvider(SplashViewModel.class);
    }

    @Override
    protected void setupToolbar() {
    }

    @Override
    protected void init() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (sharedPreferencesHelper.getUserId() == null) {
                    Intent intent = ActivityUtils.launchActivityWithClearBackStack(SplashActivity.this, LoginRegisterActivity.class);
                    startActivity(intent);
                } else {
                    mViewModel.userDetails(sharedPreferencesHelper.getUserId());
                }
            }
        };
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
        Observer<String> userDetailsErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    Intent intent = ActivityUtils.launchActivityWithClearBackStack(SplashActivity.this, LoginRegisterActivity.class);
                    startActivity(intent);
                }
            }
        };
        mViewModel.userError().observe(this, userDetailsErrorObserver);

        final Observer<User> userDetailsSuccessObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {

                sharedPreferencesHelper.setUserId(user.getUserId());
                sharedPreferencesHelper.setPicture(user.getProfilePicture());
                sharedPreferencesHelper.setName(user.getName());
                sharedPreferencesHelper.setPhoneNumber(user.getPhoneNumber());
                sharedPreferencesHelper.setEmail(user.getEmail());
                sharedPreferencesHelper.setFlatNumber(user.getFlatNumber());
                sharedPreferencesHelper.setStreetName(user.getStreetName());
                sharedPreferencesHelper.setSocietyName(user.getSocietyName());
                sharedPreferencesHelper.setLocality(user.getLocality());
                sharedPreferencesHelper.setReferralCode(user.getReferralCode());
                sharedPreferencesHelper.setReferenceBy(user.getReferenceBy());
                sharedPreferencesHelper.setIsActive(user.getIsActive());
                sharedPreferencesHelper.setFcmToken(user.getFcmToken());
                sharedPreferencesHelper.setCreatedAt(user.getCreatedAt());
                sharedPreferencesHelper.setUpdatedAt(user.getUpdatedAt());

                Intent intent;
                if (sharedPreferencesHelper.getIsActive().equals("Active")) {
                    if (sharedPreferencesHelper.getRemember()) {
                        intent = ActivityUtils.launchActivityWithClearBackStack(SplashActivity.this, NavigationHostActivity.class);
                    } else {
                        intent = ActivityUtils.launchActivityWithClearBackStack(SplashActivity.this, LoginRegisterActivity.class);
                    }
                } else {
                    intent = ActivityUtils.launchActivityWithClearBackStack(SplashActivity.this, LoginRegisterActivity.class);
                }
                startActivity(intent);
            }
        };
        mViewModel.userSuccess().observe(this, userDetailsSuccessObserver);
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handler != null) {
            handler.postDelayed(runnable, AppConstants.Delay.SPLASH);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}