package com.bidfrail.android.data.remote;

import com.bidfrail.android.AppConstants;
import com.bidfrail.android.di.qualifier.isDebug;
import com.bidfrail.android.di.qualifier.remote.ApiKey;
import com.bidfrail.android.di.qualifier.remote.BaseUrl;

import javax.inject.Singleton;

import dagger.Provides;

public class ApiConfiguration {

    public static final int CUSTOM_HTTP_CONNECT_TIMEOUT_IN_SECONDS              = 2 * 60; /* 2 minutes */
    public static final int CUSTOM_HTTP_WRITE_TIMEOUT_IN_SECONDS                = 25; /* 25 seconds */
    public static final int CUSTOM_HTTP_READ_TIMEOUT_IN_SECONDS                 = 40; /* 40 seconds */

    public static final String CUSTOM_OK_HTTP_CACHE_FILE_NAME                   = "customOkHttpClientCache";
    public static final long CUSTOM_OK_HTTP_CACHE_SIZE                          = 20 * 1024 * 1024; /* 20 MB Cache size */

    public static final int CUSTOM_CACHE_DURATION_WITH_NETWORK_IN_SECONDS       = 10;
    public static final int CUSTOM_CACHE_DURATION_WITHOUT_NETWORK_IN_SECONDS    = 14 * 24 * 60 * 60; /* Expired in two week. */

    public static final String BASE_URL                     =  AppConstants.AppConfig.IS_DEBUG ? AppConstants.ApiInfo.Development.BASE_URL : AppConstants.ApiInfo.Production.BASE_URL;
    public static final String API_KEY                      =  AppConstants.AppConfig.IS_DEBUG ? AppConstants.ApiInfo.Development.BASE_URL : AppConstants.ApiInfo.Production.BASE_URL;
    public static final String BEARER_AUTHENTICATION_TOKEN  =  AppConstants.AppConfig.IS_DEBUG ? AppConstants.ApiInfo.Development.BASE_URL : AppConstants.ApiInfo.Production.BASE_URL;

    public static final String IMAGE_URL                     =  "https://admin.bidfrail.com/DevTeam/";

    /*
     * End points
     */
    public static final String USER                                         = "user";
    public static final String CHECK_DETAILS                                = "check_details";
    public static final String REGISTER                                     = "register_user";
    public static final String SUB_CATEGORY_BANNER                          = "sub_category_banner";
    public static final String SUB_CATEGORY_SEARCH                          = "sub_category_search";
    public static final String CATEGORY_WITH_SUB_CATEGORY                   = "category_with_sub_category";
    public static final String OFFER_BANNER                                 = "offer_banner";
    public static final String TOP_BIDDING_CATEGORY                         = "top_bidding_category";
    public static final String NEWLY_LAUNCHED_CATEGORY                      = "newly_launched_category";
    public static final String REFER_EARN_BANNER                            = "refer_earn_banner";
    public static final String TRENDING_SUB_CATEGORY                        = "trending_sub_category";
    public static final String SERVICE                                      = "service";

}
