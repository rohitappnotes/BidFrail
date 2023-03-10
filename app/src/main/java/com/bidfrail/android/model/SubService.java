package com.bidfrail.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubService implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("service_id")
    @Expose
    private int serviceId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("main_price")
    @Expose
    private String mainPrice;

    @SerializedName("offer_price")
    @Expose
    private String offerPrice;

    @SerializedName("highlight_tag")
    @Expose
    private String highlightTag;

    @SerializedName("highlight_tag_color")
    @Expose
    private String highlightTagColor;

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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMainPrice() {
        return mainPrice;
    }

    public void setMainPrice(String mainPrice) {
        this.mainPrice = mainPrice;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getHighlightTag() {
        return highlightTag;
    }

    public void setHighlightTag(String highlightTag) {
        this.highlightTag = highlightTag;
    }

    public String getHighlightTagColor() {
        return highlightTagColor;
    }

    public void setHighlightTagColor(String highlightTagColor) {
        this.highlightTagColor = highlightTagColor;
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
        dest.writeInt(this.serviceId);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeString(this.mainPrice);
        dest.writeString(this.offerPrice);
        dest.writeString(this.highlightTag);
        dest.writeString(this.highlightTagColor);
        dest.writeString(this.isActive);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.serviceId = source.readInt();
        this.name = source.readString();
        this.description = source.readString();
        this.image = source.readString();
        this.mainPrice = source.readString();
        this.offerPrice = source.readString();
        this.highlightTag = source.readString();
        this.highlightTagColor = source.readString();
        this.isActive = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
    }

    public SubService() {
    }

    protected SubService(Parcel in) {
        this.id = in.readInt();
        this.serviceId = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.mainPrice = in.readString();
        this.offerPrice = in.readString();
        this.highlightTag = in.readString();
        this.highlightTagColor = in.readString();
        this.isActive = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<SubService> CREATOR = new Parcelable.Creator<SubService>() {
        @Override
        public SubService createFromParcel(Parcel source) {
            return new SubService(source);
        }

        @Override
        public SubService[] newArray(int size) {
            return new SubService[size];
        }
    };
}