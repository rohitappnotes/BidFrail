package com.bidfrail.android.ui.splash.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bidfrail.android.data.remote.ApiCallback;
import com.bidfrail.android.data.remote.ApiResponseObject;
import com.bidfrail.android.data.remote.exception.NetworkException;
import com.bidfrail.android.data.repository.LocalRepository;
import com.bidfrail.android.data.repository.RemoteRepository;
import com.bidfrail.android.model.User;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Response;

@HiltViewModel
public class SplashViewModel extends ViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    private MutableLiveData<User> userSuccess = new MutableLiveData<>();
    private MutableLiveData<String> userError = new MutableLiveData<>();

    @Inject
    public SplashViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public MutableLiveData<User> userSuccess() {
        return userSuccess;
    }

    public MutableLiveData<String> userError() {
        return userError;
    }

    public void userDetails(String userId) {
        remoteRepository.userCall(userId).enqueue(new ApiCallback<ApiResponseObject<User>>() {
            @Override
            public void onSuccess(Response<ApiResponseObject<User>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            userError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                userError.setValue(response.body().getMessage());
                            } else {
                                userSuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        userError.setValue(response.body().getMessage());
                    }
                } else {
                    userError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                userError.setValue(networkException.getDisplayMessage());
            }
        });
    }
}
