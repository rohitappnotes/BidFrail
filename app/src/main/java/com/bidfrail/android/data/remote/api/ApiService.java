package com.bidfrail.android.data.remote.api;

import com.bidfrail.android.data.remote.ApiConfiguration;
import com.bidfrail.android.data.remote.ApiResponseArray;
import com.bidfrail.android.data.remote.ApiResponseObject;
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

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST(ApiConfiguration.USER)
    Call<ApiResponseObject<User>> user(@Field("user_id") String userId);

    @FormUrlEncoded
    @POST(ApiConfiguration.CHECK_DETAILS)
    Call<ApiResponseObject<SentOTP>> checkDetails(@Field("phone_number") String phoneNumber,
                                                  @Field("referral_code") String referralCode);

    @FormUrlEncoded
    @POST(ApiConfiguration.REGISTER)
    Call<ApiResponseObject<User>> register(@Field("os") String operatingSystem,
                                           @Field("phone_number") String phoneNumber,
                                           @Field("referral_code") String referralCode,
                                           @Field("fcm_token") String fcmToken);

    @GET(ApiConfiguration.SUB_CATEGORY_BANNER)
    Call<ApiResponseArray<SubCategoryBanner>> subCategoryBanner();

    @GET(ApiConfiguration.SUB_CATEGORY_SEARCH)
    Call<ApiResponseArray<SubCategory>> subCategorySearch(@Query("search") String search);

    @GET(ApiConfiguration.CATEGORY_WITH_SUB_CATEGORY)
    Call<ApiResponseArray<CategoryWithSubCategory>> categoryWithSubCategory();

    @GET(ApiConfiguration.OFFER_BANNER)
    Call<ApiResponseArray<OfferBanner>> offerBanner();

    @GET(ApiConfiguration.TOP_BIDDING_CATEGORY)
    Call<ApiResponseArray<Category>> topBiddingCategory();

    @GET(ApiConfiguration.NEWLY_LAUNCHED_CATEGORY)
    Call<ApiResponseArray<Category>> newlyLaunchedCategory();

    @GET(ApiConfiguration.REFER_EARN_BANNER)
    Call<ApiResponseArray<ReferEarnBanner>> referEarnBanner();

    @GET(ApiConfiguration.TRENDING_SUB_CATEGORY)
    Call<ApiResponseArray<SubCategory>> trendingSubCategory();

    @FormUrlEncoded
    @POST(ApiConfiguration.SERVICE)
    Call<ApiResponseObject<SubCategoryBannerAndService>> subCategoryBannerAndService(@Field("sub_category_id") int subCategoryId);
}
