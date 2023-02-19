package com.bidfrail.android.ui.navigationhost.fragment.booknow.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bidfrail.android.data.remote.ApiCallback;
import com.bidfrail.android.data.remote.ApiResponseArray;
import com.bidfrail.android.data.remote.ApiResponseObject;
import com.bidfrail.android.data.remote.exception.NetworkException;
import com.bidfrail.android.data.repository.LocalRepository;
import com.bidfrail.android.data.repository.RemoteRepository;
import com.bidfrail.android.model.Service;
import com.bidfrail.android.model.SubCategoryBannerAndService;

import java.util.ArrayList;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Response;

@HiltViewModel
public class BookNowViewModel extends ViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;


    private MutableLiveData<SubCategoryBannerAndService> subCategoryBannerAndServiceSuccess = new MutableLiveData<>();
    private MutableLiveData<String> subCategoryBannerAndServiceError = new MutableLiveData<>();

    @Inject
    public BookNowViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public MutableLiveData<SubCategoryBannerAndService> subCategoryBannerAndServiceSuccess() {
        return subCategoryBannerAndServiceSuccess;
    }

    public MutableLiveData<String> subCategoryBannerAndServiceError() {
        return subCategoryBannerAndServiceError;
    }

    public void subCategoryBannerAndService(int subCategoryId) {
        remoteRepository.subCategoryBannerAndServiceCall(subCategoryId).enqueue(new ApiCallback<ApiResponseObject<SubCategoryBannerAndService>>() {
            @Override
            public void onSuccess(Response<ApiResponseObject<SubCategoryBannerAndService>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            subCategoryBannerAndServiceError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                subCategoryBannerAndServiceError.setValue(response.body().getMessage());
                            } else {
                                subCategoryBannerAndServiceSuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        subCategoryBannerAndServiceError.setValue(response.body().getMessage());
                    }
                } else {
                    subCategoryBannerAndServiceError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                subCategoryBannerAndServiceError.setValue(networkException.getDisplayMessage());
            }
        });
    }
}
