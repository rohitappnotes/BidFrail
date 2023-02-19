package com.bidfrail.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Service implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("sub_category_id")
    @Expose
    private int subCategoryId;

    @SerializedName("category_id")
    @Expose
    private int categoryId;

    @SerializedName("service_name")
    @Expose
    private String serviceName;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("bid_credit_point")
    @Expose
    private String bidCreditPoint;

    @SerializedName("urgent_work_credit_point")
    @Expose
    private String urgentWorkCreditPoint;

    @SerializedName("pdf")
    @Expose
    private String pdf;

    @SerializedName("is_active")
    @Expose
    private String isActive;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("sub_service")
    @Expose
    private ArrayList<SubService> subServiceArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBidCreditPoint() {
        return bidCreditPoint;
    }

    public void setBidCreditPoint(String bidCreditPoint) {
        this.bidCreditPoint = bidCreditPoint;
    }

    public String getUrgentWorkCreditPoint() {
        return urgentWorkCreditPoint;
    }

    public void setUrgentWorkCreditPoint(String urgentWorkCreditPoint) {
        this.urgentWorkCreditPoint = urgentWorkCreditPoint;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ArrayList<SubService> getSubServiceArrayList() {
        return subServiceArrayList;
    }

    public void setSubServiceArrayList(ArrayList<SubService> subServiceArrayList) {
        this.subServiceArrayList = subServiceArrayList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.subCategoryId);
        dest.writeInt(this.categoryId);
        dest.writeString(this.serviceName);
        dest.writeString(this.image);
        dest.writeString(this.price);
        dest.writeString(this.bidCreditPoint);
        dest.writeString(this.urgentWorkCreditPoint);
        dest.writeString(this.pdf);
        dest.writeString(this.isActive);
        dest.writeString(this.description);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeTypedList(this.subServiceArrayList);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.subCategoryId = source.readInt();
        this.categoryId = source.readInt();
        this.serviceName = source.readString();
        this.image = source.readString();
        this.price = source.readString();
        this.bidCreditPoint = source.readString();
        this.urgentWorkCreditPoint = source.readString();
        this.pdf = source.readString();
        this.isActive = source.readString();
        this.description = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
        this.subServiceArrayList = source.createTypedArrayList(SubService.CREATOR);
    }

    public Service() {
    }

    protected Service(Parcel in) {
        this.id = in.readInt();
        this.subCategoryId = in.readInt();
        this.categoryId = in.readInt();
        this.serviceName = in.readString();
        this.image = in.readString();
        this.price = in.readString();
        this.bidCreditPoint = in.readString();
        this.urgentWorkCreditPoint = in.readString();
        this.pdf = in.readString();
        this.isActive = in.readString();
        this.description = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.subServiceArrayList = in.createTypedArrayList(SubService.CREATOR);
    }

    public static final Parcelable.Creator<Service> CREATOR = new Parcelable.Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel source) {
            return new Service(source);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };
}