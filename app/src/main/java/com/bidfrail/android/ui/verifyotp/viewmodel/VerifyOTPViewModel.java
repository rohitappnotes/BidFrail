package com.bidfrail.android.ui.verifyotp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bidfrail.android.data.remote.ApiCallback;
import com.bidfrail.android.data.remote.ApiResponseObject;
import com.bidfrail.android.data.remote.exception.NetworkException;
import com.bidfrail.android.data.repository.LocalRepository;
import com.bidfrail.android.data.repository.RemoteRepository;
import com.bidfrail.android.model.SentOTP;
import com.bidfrail.android.model.User;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Response;

@HiltViewModel
public class VerifyOTPViewModel extends ViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    private MutableLiveData<SentOTP> checkDetailsSuccess = new MutableLiveData<>();
    private MutableLiveData<String> checkDetailsError = new MutableLiveData<>();

    private MutableLiveData<User> registerSuccess = new MutableLiveData<>();
    private MutableLiveData<String> registerError = new MutableLiveData<>();

    @Inject
    public VerifyOTPViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public MutableLiveData<SentOTP> checkDetailsSuccess() {
        return checkDetailsSuccess;
    }

    public MutableLiveData<String> checkDetailsError() {
        return checkDetailsError;
    }

    public void checkDetails(String phoneNumber, String referralCode) {
        remoteRepository.checkDetailsCall(phoneNumber, referralCode).enqueue(new ApiCallback<ApiResponseObject<SentOTP>>() {
            @Override
            public void onSuccess(Response<ApiResponseObject<SentOTP>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            checkDetailsError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                checkDetailsError.setValue(response.body().getMessage());
                            } else {
                                checkDetailsSuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        checkDetailsError.setValue(response.body().getMessage());
                    }
                } else {
                    checkDetailsError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                checkDetailsError.setValue(networkException.getDisplayMessage());
            }
        });
    }

    public MutableLiveData<User> registerSuccess() {
        return registerSuccess;
    }

    public MutableLiveData<String> registerError() {
        return registerError;
    }

    public void register(String operatingSystem, String phoneNumber, String referralCode, String fcmToken) {
        remoteRepository.registerCall(operatingSystem, phoneNumber, referralCode, fcmToken).enqueue(new ApiCallback<ApiResponseObject<User>>() {
            @Override
            public void onSuccess(Response<ApiResponseObject<User>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            registerError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                registerError.setValue(response.body().getMessage());
                            } else {
                                registerSuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        registerError.setValue(response.body().getMessage());
                    }
                } else {
                    registerError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                registerError.setValue(networkException.getDisplayMessage());
            }
        });
    }
}
