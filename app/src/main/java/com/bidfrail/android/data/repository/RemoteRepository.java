package com.bidfrail.android.data.repository;

import com.bidfrail.android.data.remote.ApiResponseArray;
import com.bidfrail.android.data.remote.ApiResponseObject;
import com.bidfrail.android.data.remote.api.ApiService;
import com.bidfrail.android.model.Category;
import com.bidfrail.android.model.CategoryWithSubCategory;
import com.bidfrail.android.model.OfferBanner;
import com.bidfrail.android.model.ReferEarnBanner;
import com.bidfrail.android.model.SentOTP;
import com.bidfrail.android.model.Service;
import com.bidfrail.android.model.SubCategory;
import com.bidfrail.android.model.SubCategoryBanner;
import com.bidfrail.android.model.SubCategoryBannerAndService;
import com.bidfrail.android.model.User;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;

@Singleton
public class RemoteRepository {

    private ApiService apiService;

    @Inject
    public RemoteRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<ApiResponseObject<User>> userCall(String userId) {
        return apiService.user(userId);
    }

    public Call<ApiResponseObject<SentOTP>> checkDetailsCall(String phoneNumber, String referralCode) {
        return apiService.checkDetails(phoneNumber, referralCode);
    }

    public Call<ApiResponseObject<User>> registerCall(String operatingSystem,
                                                      String phoneNumber,
                                                      String referralCode,
                                                      String fcmToken) {
        return apiService.register(operatingSystem, phoneNumber, referralCode, fcmToken);
    }

    public Call<ApiResponseArray<SubCategoryBanner>> subCategoryBannerCall() {
        return apiService.subCategoryBanner();
    }

    public Call<ApiResponseArray<SubCategory>> subCategorySearchCall(String search) {
        return apiService.subCategorySearch(search);
    }

    public Call<ApiResponseArray<CategoryWithSubCategory>> categoryWithSubCategoryCall() {
        return apiService.categoryWithSubCategory();
    }

    public Call<ApiResponseArray<OfferBanner>> offerBannerCall() {
        return apiService.offerBanner();
    }

    public Call<ApiResponseArray<Category>> topBiddingCategoryCall() {
        return apiService.topBiddingCategory();
    }

    public Call<ApiResponseArray<Category>> newlyLaunchedCategoryCall() {
        return apiService.newlyLaunchedCategory();
    }

    public Call<ApiResponseArray<ReferEarnBanner>> referEarnBannerCall() {
        return apiService.referEarnBanner();
    }

    public Call<ApiResponseArray<SubCategory>> trendingSubCategoryCall() {
        return apiService.trendingSubCategory();
    }

    public Call<ApiResponseObject<SubCategoryBannerAndService>> subCategoryBannerAndServiceCall(int subCategoryId) {
        return apiService.subCategoryBannerAndService(subCategoryId);
    }
}
