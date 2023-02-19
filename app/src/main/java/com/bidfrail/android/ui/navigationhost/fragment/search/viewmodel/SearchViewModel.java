package com.bidfrail.android.ui.navigationhost.fragment.search.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bidfrail.android.data.remote.ApiCallback;
import com.bidfrail.android.data.remote.ApiResponseArray;
import com.bidfrail.android.data.remote.exception.NetworkException;
import com.bidfrail.android.data.repository.LocalRepository;
import com.bidfrail.android.data.repository.RemoteRepository;
import com.bidfrail.android.model.SubCategory;
import java.util.ArrayList;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Response;

@HiltViewModel
public class SearchViewModel extends ViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    private MutableLiveData<ArrayList<SubCategory>> subCategorySearchSuccess = new MutableLiveData<>();
    private MutableLiveData<String> subCategorySearchError = new MutableLiveData<>();

    @Inject
    public SearchViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public MutableLiveData<ArrayList<SubCategory>> subCategorySearchSuccess() {
        return subCategorySearchSuccess;
    }

    public MutableLiveData<String> subCategorySearchError() {
        return subCategorySearchError;
    }

    public void subCategorySearch(String search) {
        remoteRepository.subCategorySearchCall(search).enqueue(new ApiCallback<ApiResponseArray<SubCategory>>() {
            @Override
            public void onSuccess(Response<ApiResponseArray<SubCategory>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            subCategorySearchError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                subCategorySearchError.setValue(response.body().getMessage());
                            } else {
                                subCategorySearchSuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        subCategorySearchError.setValue(response.body().getMessage());
                    }
                } else {
                    subCategorySearchError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                subCategorySearchError.setValue(networkException.getDisplayMessage());
            }
        });
    }
}
