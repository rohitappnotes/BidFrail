package com.bidfrail.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("id")
    @Expose
    private String userId;

    @SerializedName("os")
    @Expose
    private String os;

    @SerializedName("picture")
    @Expose
    private String profilePicture;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("flat_number")
    @Expose
    private String flatNumber;

    @SerializedName("street_name")
    @Expose
    private String streetName;

    @SerializedName("society_name")
    @Expose
    private String societyName;

    @SerializedName("locality")
    @Expose
    private String locality;

    @SerializedName("referral_code")
    @Expose
    private String referralCode;

    @SerializedName("reference_by")
    @Expose
    private String referenceBy;

    @SerializedName("amount")
    @Expose
    private int amount;

    @SerializedName("fcm_token")
    @Expose
    private String fcmToken;

    @SerializedName("is_active")
    @Expose
    private String isActive;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getReferenceBy() {
        return referenceBy;
    }

    public void setReferenceBy(String referenceBy) {
        this.referenceBy = referenceBy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
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
        dest.writeString(this.userId);
        dest.writeString(this.profilePicture);
        dest.writeString(this.name);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.email);
        dest.writeString(this.flatNumber);
        dest.writeString(this.streetName);
        dest.writeString(this.societyName);
        dest.writeString(this.locality);
        dest.writeString(this.referralCode);
        dest.writeString(this.referenceBy);
        dest.writeInt(this.amount);
        dest.writeString(this.fcmToken);
        dest.writeString(this.isActive);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.userId = source.readString();
        this.profilePicture = source.readString();
        this.name = source.readString();
        this.phoneNumber = source.readString();
        this.email = source.readString();
        this.flatNumber = source.readString();
        this.streetName = source.readString();
        this.societyName = source.readString();
        this.locality = source.readString();
        this.referralCode = source.readString();
        this.referenceBy = source.readString();
        this.amount = source.readInt();
        this.fcmToken = source.readString();
        this.isActive = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userId = in.readString();
        this.profilePicture = in.readString();
        this.name = in.readString();
        this.phoneNumber = in.readString();
        this.email = in.readString();
        this.flatNumber = in.readString();
        this.streetName = in.readString();
        this.societyName = in.readString();
        this.locality = in.readString();
        this.referralCode = in.readString();
        this.referenceBy = in.readString();
        this.amount = in.readInt();
        this.fcmToken = in.readString();
        this.isActive = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}