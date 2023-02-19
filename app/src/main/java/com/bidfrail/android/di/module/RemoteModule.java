package com.bidfrail.android.di.module;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import android.content.Context;
import com.bidfrail.android.AppConstants;
import com.bidfrail.android.data.remote.ApiConfiguration;
import com.bidfrail.android.data.remote.api.ApiService;
import com.bidfrail.android.data.remote.helper.OkHttpClientHelper;
import com.bidfrail.android.data.remote.helper.RetrofitHelper;
import com.bidfrail.android.di.qualifier.isDebug;
import com.bidfrail.android.di.qualifier.remote.ApiKey;
import com.bidfrail.android.di.qualifier.remote.BaseUrl;

@Module
@InstallIn(SingletonComponent.class)
public class RemoteModule {

    @Provides
    @Singleton
    @isDebug
    boolean provideIsDebug() {
        return AppConstants.AppConfig.IS_DEBUG;
    }

    @Provides
    @Singleton
    @BaseUrl
    String provideBaseUrl(@isDebug boolean isDebug) {
        return ApiConfiguration.BASE_URL;
    }

    @Provides
    @Singleton
    @ApiKey
    String provideApiKey(@isDebug boolean isDebug) {
        return ApiConfiguration.API_KEY;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@ApplicationContext Context context, @isDebug boolean isDebug) {
        OkHttpClientHelper.Builder builder = OkHttpClientHelper.getInstance().new Builder(context);
        builder.setShowLog(isDebug);
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@BaseUrl String baseUrl, OkHttpClient okHttpClient) {
        RetrofitHelper.Builder builder = RetrofitHelper.getInstance().new Builder(baseUrl);
        builder.setOkHttpClient(okHttpClient);
        return builder.build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
