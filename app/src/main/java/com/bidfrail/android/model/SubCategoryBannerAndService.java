package com.bidfrail.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class SubCategoryBannerAndService implements Parcelable {

    @SerializedName("service_banner")
    @Expose
    private ArrayList<SubCategoryBanner> subCategoryBannerArrayList;

    @SerializedName("service")
    @Expose
    private ArrayList<Service> serviceArrayList;

    public ArrayList<SubCategoryBanner> getSubCategoryBannerArrayList() {
        return subCategoryBannerArrayList;
    }

    public void setSubCategoryBannerArrayList(ArrayList<SubCategoryBanner> subCategoryBannerArrayList) {
        this.subCategoryBannerArrayList = subCategoryBannerArrayList;
    }

    public ArrayList<Service> getServiceArrayList() {
        return serviceArrayList;
    }

    public void setServiceArrayList(ArrayList<Service> serviceArrayList) {
        this.serviceArrayList = serviceArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.subCategoryBannerArrayList);
        dest.writeList(this.serviceArrayList);
    }

    public void readFromParcel(Parcel source) {
        this.subCategoryBannerArrayList = source.createTypedArrayList(SubCategoryBanner.CREATOR);
        this.serviceArrayList = new ArrayList<Service>();
        source.readList(this.serviceArrayList, Service.class.getClassLoader());
    }

    public SubCategoryBannerAndService() {
    }

    protected SubCategoryBannerAndService(Parcel in) {
        this.subCategoryBannerArrayList = in.createTypedArrayList(SubCategoryBanner.CREATOR);
        this.serviceArrayList = new ArrayList<Service>();
        in.readList(this.serviceArrayList, Service.class.getClassLoader());
    }

    public static final Parcelable.Creator<SubCategoryBannerAndService> CREATOR = new Parcelable.Creator<SubCategoryBannerAndService>() {
        @Override
        public SubCategoryBannerAndService createFromParcel(Parcel source) {
            return new SubCategoryBannerAndService(source);
        }

        @Override
        public SubCategoryBannerAndService[] newArray(int size) {
            return new SubCategoryBannerAndService[size];
        }
    };
}