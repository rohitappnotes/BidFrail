package com.bidfrail.android.ui.navigationhost.fragment.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bidfrail.android.data.remote.ApiCallback;
import com.bidfrail.android.data.remote.ApiResponseArray;
import com.bidfrail.android.data.remote.exception.NetworkException;
import com.bidfrail.android.data.repository.LocalRepository;
import com.bidfrail.android.data.repository.RemoteRepository;
import com.bidfrail.android.model.Category;
import com.bidfrail.android.model.CategoryWithSubCategory;
import com.bidfrail.android.model.OfferBanner;
import com.bidfrail.android.model.ReferEarnBanner;
import com.bidfrail.android.model.SubCategory;
import com.bidfrail.android.model.SubCategoryBanner;

import java.util.ArrayList;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Response;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    private MutableLiveData<ArrayList<SubCategoryBanner>> subCategoryBannerSuccess = new MutableLiveData<>();
    private MutableLiveData<String> subCategoryBannerError = new MutableLiveData<>();

    private MutableLiveData<ArrayList<CategoryWithSubCategory>> categoryWithSubCategorySuccess = new MutableLiveData<>();
    private MutableLiveData<String> categoryWithSubCategoryError = new MutableLiveData<>();

    private MutableLiveData<ArrayList<OfferBanner>> offerBannerSuccess = new MutableLiveData<>();
    private MutableLiveData<String> offerBannerError = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Category>> topBiddingCategorySuccess = new MutableLiveData<>();
    private MutableLiveData<String> topBiddingCategoryError = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Category>> newlyLaunchedCategorySuccess = new MutableLiveData<>();
    private MutableLiveData<String> newlyLaunchedCategoryError = new MutableLiveData<>();

    private MutableLiveData<ArrayList<ReferEarnBanner>> referEarnBannerSuccess = new MutableLiveData<>();
    private MutableLiveData<String> referEarnBannerError = new MutableLiveData<>();

    private MutableLiveData<ArrayList<SubCategory>> trendingSubCategorySuccess = new MutableLiveData<>();
    private MutableLiveData<String> trendingSubCategoryError = new MutableLiveData<>();

    @Inject
    public HomeViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public MutableLiveData<ArrayList<SubCategoryBanner>> subCategoryBannerSuccess() {
        return subCategoryBannerSuccess;
    }

    public MutableLiveData<String> subCategoryBannerError() {
        return subCategoryBannerError;
    }

    public void subCategoryBanner() {
        remoteRepository.subCategoryBannerCall().enqueue(new ApiCallback<ApiResponseArray<SubCategoryBanner>>() {
            @Override
            public void onSuccess(Response<ApiResponseArray<SubCategoryBanner>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            subCategoryBannerError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                subCategoryBannerError.setValue(response.body().getMessage());
                            } else {
                                subCategoryBannerSuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        subCategoryBannerError.setValue(response.body().getMessage());
                    }
                } else {
                    subCategoryBannerError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                subCategoryBannerError.setValue(networkException.getDisplayMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<CategoryWithSubCategory>> categoryWithSubCategorySuccess() {
        return categoryWithSubCategorySuccess;
    }

    public MutableLiveData<String> categoryWithSubCategoryError() {
        return categoryWithSubCategoryError;
    }

    public void categoryWithSubCategory() {
        remoteRepository.categoryWithSubCategoryCall().enqueue(new ApiCallback<ApiResponseArray<CategoryWithSubCategory>>() {
            @Override
            public void onSuccess(Response<ApiResponseArray<CategoryWithSubCategory>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            categoryWithSubCategoryError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                categoryWithSubCategoryError.setValue(response.body().getMessage());
                            } else {
                                categoryWithSubCategorySuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        categoryWithSubCategoryError.setValue(response.body().getMessage());
                    }
                } else {
                    categoryWithSubCategoryError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                categoryWithSubCategoryError.setValue(networkException.getDisplayMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<OfferBanner>> offerBannerSuccess() {
        return offerBannerSuccess;
    }

    public MutableLiveData<String> offerBannerError() {
        return offerBannerError;
    }

    public void offerBanner() {
        remoteRepository.offerBannerCall().enqueue(new ApiCallback<ApiResponseArray<OfferBanner>>() {
            @Override
            public void onSuccess(Response<ApiResponseArray<OfferBanner>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            offerBannerError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                offerBannerError.setValue(response.body().getMessage());
                            } else {
                                offerBannerSuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        offerBannerError.setValue(response.body().getMessage());
                    }
                } else {
                    offerBannerError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                offerBannerError.setValue(networkException.getDisplayMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<Category>> topBiddingCategorySuccess() {
        return topBiddingCategorySuccess;
    }

    public MutableLiveData<String> topBiddingCategoryError() {
        return topBiddingCategoryError;
    }

    public void topBiddingCategory() {
        remoteRepository.topBiddingCategoryCall().enqueue(new ApiCallback<ApiResponseArray<Category>>() {
            @Override
            public void onSuccess(Response<ApiResponseArray<Category>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            topBiddingCategoryError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                topBiddingCategoryError.setValue(response.body().getMessage());
                            } else {
                                topBiddingCategorySuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        topBiddingCategoryError.setValue(response.body().getMessage());
                    }
                } else {
                    topBiddingCategoryError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                topBiddingCategoryError.setValue(networkException.getDisplayMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<Category>> newlyLaunchedCategorySuccess() {
        return newlyLaunchedCategorySuccess;
    }

    public MutableLiveData<String> newlyLaunchedCategoryError() {
        return newlyLaunchedCategoryError;
    }

    public void newlyLaunchedCategory() {
        remoteRepository.newlyLaunchedCategoryCall().enqueue(new ApiCallback<ApiResponseArray<Category>>() {
            @Override
            public void onSuccess(Response<ApiResponseArray<Category>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            newlyLaunchedCategoryError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                newlyLaunchedCategoryError.setValue(response.body().getMessage());
                            } else {
                                newlyLaunchedCategorySuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        newlyLaunchedCategoryError.setValue(response.body().getMessage());
                    }
                } else {
                    newlyLaunchedCategoryError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                newlyLaunchedCategoryError.setValue(networkException.getDisplayMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<ReferEarnBanner>> referEarnBannerSuccess() {
        return referEarnBannerSuccess;
    }

    public MutableLiveData<String> referEarnBannerError() {
        return referEarnBannerError;
    }

    public void referEarnBanner() {
        remoteRepository.referEarnBannerCall().enqueue(new ApiCallback<ApiResponseArray<ReferEarnBanner>>() {
            @Override
            public void onSuccess(Response<ApiResponseArray<ReferEarnBanner>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            referEarnBannerError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                referEarnBannerError.setValue(response.body().getMessage());
                            } else {
                                referEarnBannerSuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        referEarnBannerError.setValue(response.body().getMessage());
                    }
                } else {
                    referEarnBannerError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                referEarnBannerError.setValue(networkException.getDisplayMessage());
            }
        });
    }

    public MutableLiveData<ArrayList<SubCategory>> trendingSubCategorySuccess() {
        return trendingSubCategorySuccess;
    }

    public MutableLiveData<String> trendingSubCategoryError() {
        return trendingSubCategoryError;
    }

    public void trendingSubCategory() {
        remoteRepository.trendingSubCategoryCall().enqueue(new ApiCallback<ApiResponseArray<SubCategory>>() {
            @Override
            public void onSuccess(Response<ApiResponseArray<SubCategory>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().isError()) {
                            trendingSubCategoryError.setValue(response.body().getMessage());
                        } else {
                            if (response.body().getData() == null) {
                                trendingSubCategoryError.setValue(response.body().getMessage());
                            } else {
                                trendingSubCategorySuccess.setValue(response.body().getData());
                            }
                        }
                    } else {
                        trendingSubCategoryError.setValue(response.body().getMessage());
                    }
                } else {
                    trendingSubCategoryError.setValue(null);
                }
            }

            @Override
            public void onFailure(NetworkException networkException) {
                trendingSubCategoryError.setValue(networkException.getDisplayMessage());
            }
        });
    }
}
