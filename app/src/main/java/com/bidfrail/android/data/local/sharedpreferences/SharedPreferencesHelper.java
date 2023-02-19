package com.bidfrail.android.data.local.sharedpreferences;

import android.content.Context;
import com.bidfrail.android.AppConstants;
import com.bidfrail.android.di.qualifier.local.SharedPreferencesName;
import com.library.sharedpreferences.SharedPreferenceBuilder;
import javax.inject.Inject;
import javax.inject.Singleton;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class SharedPreferencesHelper extends SharedPreferenceBuilder {

    @Inject
    public SharedPreferencesHelper(@ApplicationContext Context context, @SharedPreferencesName String sharedPreferencesFileName) {
        super(context, sharedPreferencesFileName);
    }

    public void setCurrentTheme(int value) {
        putInt(AppConstants.SharedPreferences.CURRENT_THEME, value);
    }

    public int getCurrentTheme() {
        return getInt(AppConstants.SharedPreferences.CURRENT_THEME, 0);
    }

    public void setIsLanguageSelect(boolean value) {
        putBoolean(AppConstants.SharedPreferences.IS_LANGUAGE_SELECT, value);
    }

    public boolean getIsLanguageSelect() {
        return getBoolean(AppConstants.SharedPreferences.IS_LANGUAGE_SELECT, false);
    }

    public String getCurrentLanguage() {
        return getString(AppConstants.SharedPreferences.CURRENT_LANGUAGE, "en");
    }

    public void setCurrentLanguage(String value) {
        putString(AppConstants.SharedPreferences.CURRENT_LANGUAGE, value);
    }

    public void setIsShowAppIntro(boolean value) {
        putBoolean(AppConstants.SharedPreferences.IS_SHOW_APP_INTRO, value);
    }

    public boolean getIsShowAppIntro() {
        return getBoolean(AppConstants.SharedPreferences.IS_SHOW_APP_INTRO, true);
    }

    public void setRemember(boolean value) {
        putBoolean(AppConstants.SharedPreferences.REMEMBER_ME, value);
    }

    public boolean getRemember() {
        return getBoolean(AppConstants.SharedPreferences.REMEMBER_ME, false);
    }

    public void setUserId(String value) {
        putString(AppConstants.SharedPreferences.USER_ID, value);
    }

    public String getUserId() {
        return getString(AppConstants.SharedPreferences.USER_ID, null);
    }

    public void setPicture(String value) {
        putString(AppConstants.SharedPreferences.PICTURE, value);
    }

    public String getPicture() {
        return getString(AppConstants.SharedPreferences.PICTURE);
    }

    public void setName(String value) {
        putString(AppConstants.SharedPreferences.NAME, value);
    }

    public String getName() {
        return getString(AppConstants.SharedPreferences.NAME);
    }

    public void setPhoneNumber(String value) {
        putString(AppConstants.SharedPreferences.PHONE_NUMBER, value);
    }

    public String getPhoneNumber() {
        return getString(AppConstants.SharedPreferences.PHONE_NUMBER);
    }

    public void setEmail(String value) {
        putString(AppConstants.SharedPreferences.EMAIL, value);
    }

    public String getEmail() {
        return getString(AppConstants.SharedPreferences.EMAIL);
    }

    public void setFlatNumber(String value) {
        putString(AppConstants.SharedPreferences.FLAT_NUMBER, value);
    }

    public String getFlatNumber() {
        return getString(AppConstants.SharedPreferences.FLAT_NUMBER);
    }

    public void setStreetName(String value) {
        putString(AppConstants.SharedPreferences.STREET_NAME, value);
    }

    public String getStreetName() {
        return getString(AppConstants.SharedPreferences.STREET_NAME);
    }

    public void setSocietyName(String value) {
        putString(AppConstants.SharedPreferences.SOCIETY_NAME, value);
    }

    public String getSocietyName() {
        return getString(AppConstants.SharedPreferences.SOCIETY_NAME);
    }

    public void setLocality(String value) {
        putString(AppConstants.SharedPreferences.LOCALITY, value);
    }

    public String getLocality() {
        return getString(AppConstants.SharedPreferences.LOCALITY);
    }


    public void setReferralCode(String value) {
        putString(AppConstants.SharedPreferences.REFERRAL_CODE, value);
    }

    public String getReferralCode() {
        return getString(AppConstants.SharedPreferences.REFERRAL_CODE);
    }

    public void setReferenceBy(String value) {
        putString(AppConstants.SharedPreferences.REFERENCE_BY, value);
    }

    public String getReferenceBy() {
        return getString(AppConstants.SharedPreferences.REFERENCE_BY);
    }

    public void setIsActive(String value) {
        putString(AppConstants.SharedPreferences.IS_ACTIVE, value);
    }

    public String getIsActive() {
        return getString(AppConstants.SharedPreferences.IS_ACTIVE);
    }

    public void setFcmToken(String value) {
        putString(AppConstants.SharedPreferences.FCM_TOKEN, value);
    }

    public String getFcmToken() {
        return getString(AppConstants.SharedPreferences.FCM_TOKEN);
    }

    public void setCreatedAt(String value) {
        putString(AppConstants.SharedPreferences.CREATED_AT, value);
    }

    public String getCreatedAt() {
        return getString(AppConstants.SharedPreferences.CREATED_AT);
    }

    public void setUpdatedAt(String value) {
        putString(AppConstants.SharedPreferences.UPDATED_AT, value);
    }

    public String getUpdatedAt() {
        return getString(AppConstants.SharedPreferences.UPDATED_AT);
    }
}
