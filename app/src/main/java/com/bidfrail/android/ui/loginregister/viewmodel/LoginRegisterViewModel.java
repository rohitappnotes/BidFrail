package com.bidfrail.android.ui.loginregister.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bidfrail.android.data.remote.ApiCallback;
import com.bidfrail.android.data.remote.ApiResponseObject;
import com.bidfrail.android.data.remote.exception.NetworkException;
import com.bidfrail.android.data.repository.LocalRepository;
import com.bidfrail.android.data.repository.RemoteRepository;
import com.bidfrail.android.model.SentOTP;
import com.bidfrail.android.ui.loginregister.introduction.Intro;
import java.util.ArrayList;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Response;

@HiltViewModel
public class LoginRegisterViewModel extends ViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    private MutableLiveData<ArrayList<Intro>> arrayListMutableLiveData;

    private MutableLiveData<SentOTP> checkDetailsSuccess = new MutableLiveData<>();
    private MutableLiveData<String> checkDetailsError = new MutableLiveData<>();

    @Inject
    public LoginRegisterViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public MutableLiveData<ArrayList<Intro>> getIntroArrayListSuccess() {
        if (arrayListMutableLiveData == null) {
            arrayListMutableLiveData = new MutableLiveData<>();
        }
        return arrayListMutableLiveData;
    }

    public void getIntroArrayList() {
        arrayListMutableLiveData.setValue(localRepository.getIntro());
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
}
