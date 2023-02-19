package com.bidfrail.android.ui.verifyotp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.bidfrail.android.AppConstants;
import com.bidfrail.android.R;
import com.bidfrail.android.data.local.sharedpreferences.SharedPreferencesHelper;
import com.bidfrail.android.databinding.ActivityVerifyOtpBinding;
import com.bidfrail.android.model.SentOTP;
import com.bidfrail.android.model.User;
import com.bidfrail.android.ui.base.view.BaseActivity;
import com.bidfrail.android.ui.navigationhost.view.NavigationHostActivity;
import com.bidfrail.android.ui.verifyotp.viewmodel.VerifyOTPViewModel;
import com.library.utilities.activity.ActivityUtils;
import com.library.utilities.string.StringUtils;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VerifyOTPActivity extends BaseActivity<ActivityVerifyOtpBinding, VerifyOTPViewModel> {

    private CountDownTimer countDownTimerFor1Minute;

    private SentOTP sentOTPObject;
    private String reviewPhoneNumber = "7898680304";
    private String messageOTP;

    private String phoneNumber;
    private String referralCode;
    private String fcmToken = "not available";

    private String inputOTP;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected String getActivityClassName() {
        return VerifyOTPActivity.class.getSimpleName();
    }

    @Override
    protected void doBeforeSetContentView() {
    }

    @Override
    protected void doInOnCreate() {
        Bundle bundle = ActivityUtils.getBundle(VerifyOTPActivity.this);

        sentOTPObject = bundle.getParcelable(AppConstants.Extras.EXTRA_SENT_OTP);
        messageOTP = sentOTPObject.getSentOTP();

        phoneNumber = bundle.getString(AppConstants.Extras.EXTRA_PHONE_NUMBER);
        referralCode = bundle.getString(AppConstants.Extras.EXTRA_REFERRAL_CODE);
    }

    @NonNull
    @Override
    protected ActivityVerifyOtpBinding getViewBinding(LayoutInflater inflater) {
        return ActivityVerifyOtpBinding.inflate(inflater);
    }

    @NonNull
    @Override
    protected VerifyOTPViewModel getViewModel() {
        return viewModelProvider(VerifyOTPViewModel.class);
    }

    @Override
    protected void setupToolbar() {
    }

    @Override
    protected void init() {
        counter1Minute();
    }

    @Override
    protected void initView() {
        mViewBinding.sentToPhoneNumberTextView.setText("+91"+phoneNumber);
    }

    @Override
    protected void addTextChangedListener() {
        mViewBinding.pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(mTag, "onTextChanged() called with: s = [" + s + "], start = [" + start + "], before = [" + before + "], count = [" + count + "]");
                inputOTP = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void setOnFocusChangeListener() {
    }

    @Override
    protected void setupObservers() {
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
                counter1Minute();
                hideProgressDialog();
                sentOTPObject = sentOTP;
                messageOTP = sentOTPObject.getSentOTP();
            }
        };
        mViewModel.checkDetailsSuccess().observe(this, checkDetailsSuccessObserver);

        Observer<String> registerErrorObserver = new Observer<String>() {
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
        mViewModel.registerError().observe(this, registerErrorObserver);

        final Observer<User> registerSuccessObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                hideProgressDialog();
                openNavigationHostActivity(user);
            }
        };
        mViewModel.registerSuccess().observe(this, registerSuccessObserver);
    }

    @Override
    protected void setupListeners() {
        mViewBinding.navigationIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mViewBinding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                if (continueButtonValidation(inputOTP)) {
                    showProgressDialog();
                    mViewModel.register("ANDROID", phoneNumber, referralCode, fcmToken);
                }
            }
        });

        mViewBinding.reSendCodeLinkOrTimerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewBinding.pinView.setText("");
                hideKeyboard();
                if (mViewBinding.reSendCodeLinkOrTimerTextView.getText() == getString(R.string.verify_otp_link_resend_code))
                {
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
    public void onStart() {
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
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimerFor1Minute.cancel();
    }

    /***********************************************************************************************
     ********************************************Permission*****************************************
     **********************************************************************************************/

    /***********************************************************************************************
     *******************************************Validations*****************************************
     **********************************************************************************************/
    private boolean continueButtonValidation(String inputOTP) {
        if (StringUtils.isEmpty(inputOTP)) {
            showShortToast(R.string.verify_otp_error_otp_one);
            return false;
        } else if (!inputOTP.equals(messageOTP)) {
            showShortToast(R.string.verify_otp_error_otp_two);
            return false;
        }
        return true;
    }
    /***********************************************************************************************
     *******************************************Open Activity***************************************
     **********************************************************************************************/
    private void openNavigationHostActivity(User user) {
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

        sharedPreferencesHelper.setRemember(true);

        Intent intent = ActivityUtils.launchActivityWithClearBackStack(VerifyOTPActivity.this, NavigationHostActivity.class);
        startActivity(intent);
    }
    /***********************************************************************************************
     *********************************************Helper********************************************
     **********************************************************************************************/
    private void counter1Minute() {
        long secondsIn1Minute = 60;

        mViewBinding.reSendCodeLinkOrTimerTextView.setText(60+"s");
        mViewBinding.reSendCodeMessageTextView.setText(getString(R.string.verify_otp_label_resend_code_in));

        countDownTimerFor1Minute = new CountDownTimer(/*convert second to milliseconds*/1000*secondsIn1Minute, 1000) {
            public void onTick(long millisUntilFinished) {

                NumberFormat numberFormat = new DecimalFormat("00");
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);

                String hms = numberFormat.format(hours) + ":" + numberFormat.format(minutes) + ":" + numberFormat.format(seconds);
                System.out.println("===========HMS=========="+hms);
                mViewBinding.reSendCodeLinkOrTimerTextView.setText(seconds+"s");
                mViewBinding.reSendCodeMessageTextView.setText(getString(R.string.verify_otp_label_resend_code_in));
            }

            public void onFinish() {
                System.out.println("=============FINISH==========");
                mViewBinding.reSendCodeLinkOrTimerTextView.setText(getString(R.string.verify_otp_link_resend_code));
                mViewBinding.reSendCodeMessageTextView.setText(getString(R.string.verify_otp_label_did_not_get_the_code));
            }
        };
        countDownTimerFor1Minute.start();
    }
}