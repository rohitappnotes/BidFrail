package com.bidfrail.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferEarnBanner implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.image);
        dest.writeString(this.isActive);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.image = source.readString();
        this.isActive = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
    }

    public ReferEarnBanner() {
    }

    protected ReferEarnBanner(Parcel in) {
        this.id = in.readInt();
        this.image = in.readString();
        this.isActive = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<ReferEarnBanner> CREATOR = new Creator<ReferEarnBanner>() {
        @Override
        public ReferEarnBanner createFromParcel(Parcel source) {
            return new ReferEarnBanner(source);
        }

        @Override
        public ReferEarnBanner[] newArray(int size) {
            return new ReferEarnBanner[size];
        }
    };
}
