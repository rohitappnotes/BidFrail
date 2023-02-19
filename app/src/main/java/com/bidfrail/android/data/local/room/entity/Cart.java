package com.bidfrail.android.data.local.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "cart")
public class Cart {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int subServiceId;

    @ColumnInfo(name = "service_id")
    private String serviceId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "main_price")
    private String mainPrice;

    @ColumnInfo(name = "offer_price")
    private String offerPrice;

    @ColumnInfo(name = "highlight_tag")
    private String highlightTag;

    @ColumnInfo(name = "highlight_tag_color")
    private String highlightTagColor;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "total_price")
    public int totalPrice;

    @ColumnInfo(name = "created_at")
    public Date createdAt;

    @ColumnInfo(name = "updated_at")
    public Date updatedAt;

    public Cart(int subServiceId, String serviceId, String name, String description, String image, String mainPrice, String offerPrice, String highlightTag, String highlightTagColor, int quantity, int totalPrice, Date createdAt, Date updatedAt) {
        this.subServiceId = subServiceId;
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.mainPrice = mainPrice;
        this.offerPrice = offerPrice;
        this.highlightTag = highlightTag;
        this.highlightTagColor = highlightTagColor;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(int subServiceId) {
        this.subServiceId = subServiceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
