package com.bidfrail.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class CategoryWithSubCategory implements Parcelable {

    private boolean isClick = false;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("category_type")
    @Expose
    private String categoryType;

    @SerializedName("is_active")
    @Expose
    private String isActive;

    @SerializedName("is_trending")
    @Expose
    private String isTrending;

    @SerializedName("is_new")
    @Expose
    private String isNew;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("subcategory")
    @Expose
    private ArrayList<SubCategory> subCategoryArrayList;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsTrending() {
        return isTrending;
    }

    public void setIsTrending(String isTrending) {
        this.isTrending = isTrending;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
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

    public ArrayList<SubCategory> getSubCategoryArrayList() {
        return subCategoryArrayList;
    }

    public void setSubCategoryArrayList(ArrayList<SubCategory> subCategoryArrayList) {
        this.subCategoryArrayList = subCategoryArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isClick ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeString(this.categoryType);
        dest.writeString(this.isActive);
        dest.writeString(this.isTrending);
        dest.writeString(this.isNew);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeTypedList(this.subCategoryArrayList);
    }

    public void readFromParcel(Parcel source) {
        this.isClick = source.readByte() != 0;
        this.id = source.readInt();
        this.name = source.readString();
        this.image = source.readString();
        this.categoryType = source.readString();
        this.isActive = source.readString();
        this.isTrending = source.readString();
        this.isNew = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
        this.subCategoryArrayList = source.createTypedArrayList(SubCategory.CREATOR);
    }

    public CategoryWithSubCategory() {
    }

    public CategoryWithSubCategory(boolean isClick, int id, String name, String image, String categoryType, String isActive, String isTrending, String isNew, String createdAt, String updatedAt, ArrayList<SubCategory> subCategoryArrayList) {
        this.isClick = isClick;
        this.id = id;
        this.name = name;
        this.image = image;
        this.categoryType = categoryType;
        this.isActive = isActive;
        this.isTrending = isTrending;
        this.isNew = isNew;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.subCategoryArrayList = subCategoryArrayList;
    }

    protected CategoryWithSubCategory(Parcel in) {
        this.isClick = in.readByte() != 0;
        this.id = in.readInt();
        this.name = in.readString();
        this.image = in.readString();
        this.categoryType = in.readString();
        this.isActive = in.readString();
        this.isTrending = in.readString();
        this.isNew = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.subCategoryArrayList = in.createTypedArrayList(SubCategory.CREATOR);
    }

    public static final Creator<CategoryWithSubCategory> CREATOR = new Creator<CategoryWithSubCategory>() {
        @Override
        public CategoryWithSubCategory createFromParcel(Parcel source) {
            return new CategoryWithSubCategory(source);
        }

        @Override
        public CategoryWithSubCategory[] newArray(int size) {
            return new CategoryWithSubCategory[size];
        }
    };
}
