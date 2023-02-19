package com.bidfrail.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryBanner implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("is_active")
    @Expose
    private String isActive;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("sub_category")
    @Expose
    private SubCategory subCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.subCategoryId);
        dest.writeString(this.image);
        dest.writeString(this.isActive);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeParcelable(this.subCategory, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.subCategoryId = source.readString();
        this.image = source.readString();
        this.isActive = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
        this.subCategory = source.readParcelable(SubCategory.class.getClassLoader());
    }

    public SubCategoryBanner() {
    }

    protected SubCategoryBanner(Parcel in) {
        this.id = in.readInt();
        this.subCategoryId = in.readString();
        this.image = in.readString();
        this.isActive = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.subCategory = in.readParcelable(SubCategory.class.getClassLoader());
    }

    public static final Creator<SubCategoryBanner> CREATOR = new Creator<SubCategoryBanner>() {
        @Override
        public SubCategoryBanner createFromParcel(Parcel source) {
            return new SubCategoryBanner(source);
        }

        @Override
        public SubCategoryBanner[] newArray(int size) {
            return new SubCategoryBanner[size];
        }
    };
}
