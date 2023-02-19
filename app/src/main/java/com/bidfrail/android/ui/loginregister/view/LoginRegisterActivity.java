package com.bidfrail.android.ui.loginregister.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;

import com.bidfrail.android.AppConstants;
import com.bidfrail.android.R;
import com.bidfrail.android.data.local.sharedpreferences.SharedPreferencesHelper;
import com.bidfrail.android.databinding.ActivityLoginRegisterBinding;
import com.bidfrail.android.model.SentOTP;
import com.bidfrail.android.model.User;
import com.bidfrail.android.ui.base.view.BaseActivity;
import com.bidfrail.android.ui.loginregister.introduction.Intro;
import com.bidfrail.android.ui.loginregister.introduction.IntroPagerAdapter;
import com.bidfrail.android.ui.loginregister.viewmodel.LoginRegisterViewModel;
import com.bidfrail.android.ui.navigationhost.view.NavigationHostActivity;
import com.bidfrail.android.ui.splash.view.SplashActivity;
import com.bidfrail.android.ui.verifyotp.view.VerifyOTPActivity;
import com.library.utilities.ValidationUtils;
import com.library.utilities.activity.ActivityUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginRegisterActivity extends BaseActivity<ActivityLoginRegisterBinding, LoginRegisterViewModel> {

    private IntroPagerAdapter introPagerAdapter;

    private String phoneNumber;
    private String referralCode;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected String getActivityClassName() {
        return LoginRegisterActivity.class.getSimpleName();
    }

    @Override
    protected void doBeforeSetContentView() {
    }

    @Override
    protected void doInOnCreate() {
    }

    @NonNull
    @Override
    protected ActivityLoginRegisterBinding getViewBinding(LayoutInflater inflater) {
        return ActivityLoginRegisterBinding.inflate(inflater);
    }

    @NonNull
    @Override
    protected LoginRegisterViewModel getViewModel() {
        return viewModelProvider(LoginRegisterViewModel.class);
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
        mViewBinding.phoneNumberTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence text, int start, int count, int after) {
                if (text.length() < 1) {
                    mViewBinding.phoneNumberTextInputLayout.setErrorEnabled(true);
                    mViewBinding.phoneNumberTextInputLayout.setError(getString(R.string.phone_number_message_one));
                } else if (text.length() > 0) {
                    mViewBinding.phoneNumberTextInputLayout.setError(null);
                    mViewBinding.phoneNumberTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int phoneValidCode = ValidationUtils.isPhoneNumberValid(mViewBinding.phoneNumberTextInputEditText.getText().toString().trim());
                if (phoneValidCode > 0) {
                    setGone(mViewBinding.loginRegisterMaterialButton);
                    if (phoneValidCode == 1) {
                        mViewBinding.phoneNumberTextInputLayout.setError(getString(R.string.phone_number_message_one));
                    } else if (phoneValidCode == 2) {
                        mViewBinding.phoneNumberTextInputLayout.setError(getString(R.string.phone_number_message_two));
                    }
                }
                else
                {
                    setVisible(mViewBinding.loginRegisterMaterialButton);
                }
            }
        });
    }

    @Override
    protected void setOnFocusChangeListener() {
    }

    @Override
    protected void setupObservers() {
        final Observer<ArrayList<Intro>> introductionObserver = new Observer<ArrayList<Intro>>() {
            @Override
            public void onChanged(ArrayList<Intro> intros) {
                introPagerAdapter = new IntroPagerAdapter(getApplicationContext(), intros);
                mViewBinding.viewPager.setAdapter(introPagerAdapter);
                mViewBinding.circleIndicator.setViewPager(mViewBinding.viewPager);
            }
        };
        mViewModel.getIntroArrayListSuccess().observe(this, introductionObserver);
        mViewModel.getIntroArrayList();

        Observer<String> checkDetailsErrorObserver = new Observer<String>() {
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
        mViewModel.checkDetailsError().observe(this, checkDetailsErrorObserver);

        final Observer<SentOTP> checkDetailsSuccessObserver = new Observer<SentOTP>() {
            @Override
            public void onChanged(SentOTP sentOTP) {
                hideProgressDialog();
                openVerifyOTPActivity(sentOTP, phoneNumber, referralCode);
            }
        };
        mViewModel.checkDetailsSuccess().observe(this, checkDetailsSuccessObserver);
    }

    @Override
    protected void setupListeners() {
        mViewBinding.scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mViewBinding.scrollView.post(new Runnable() {
                    public void run() {
                        mViewBinding.scrollView.scrollTo(0, mViewBinding.scrollView.getBottom() + mViewBinding.scrollView.getScrollY());
                    }
                });
            }
        });

        mViewBinding.loginRegisterMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                phoneNumber = mViewBinding.phoneNumberTextInputEditText.getText().toString().trim();
                referralCode = "";

                if (loginRegisterValidation(phoneNumber)) {
                    showProgressDialog();
                    mViewModel.checkDetails(phoneNumber, referralCode);
                }
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
        if (mViewBinding.progressDialog.pleaseWaitProgressBar.getVisibility() == View.GONE) {
            mViewBinding.progressDialog.pleaseWaitProgressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mViewBinding.progressDialog.pleaseWaitProgressBar.getVisibility() == View.VISIBLE) {
            mViewBinding.progressDialog.pleaseWaitProgressBar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    /***********************************************************************************************
     ********************************************Permission*****************************************
     **********************************************************************************************/

    /***********************************************************************************************
     *******************************************Validations*****************************************
     **********************************************************************************************/
    private boolean loginRegisterValidation(String phoneNumber) {
        int phoneValidCode = ValidationUtils.isPhoneNumberValid(phoneNumber);
        if (phoneValidCode > 0) {
            if (phoneValidCode == 1) {
                mViewBinding.phoneNumberTextInputLayout.setError(getString(R.string.login_register_error_phone_number_one));
                return false;
            } else if (phoneValidCode == 2) {
                mViewBinding.phoneNumberTextInputLayout.setError(getString(R.string.login_register_error_phone_number_two));
                return false;
            }
        }
        return true;
    }
    /***********************************************************************************************
     *******************************************Open Activity***************************************
     **********************************************************************************************/
    public void openVerifyOTPActivity(SentOTP sentOTP, String phoneNumber, String referralCode) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.Extras.EXTRA_PHONE_NUMBER, phoneNumber);
        bundle.putString(AppConstants.Extras.EXTRA_REFERRAL_CODE, referralCode);
        bundle.putParcelable(AppConstants.Extras.EXTRA_SENT_OTP, sentOTP);
        Intent intent = ActivityUtils.launchActivity(LoginRegisterActivity.this, VerifyOTPActivity.class, bundle);
        startActivity(intent);
    }
    /***********************************************************************************************
     *********************************************Helper********************************************
     **********************************************************************************************/
}