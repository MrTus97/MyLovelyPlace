package com.tuphanthanh.mylovelyplace.data.model;

import android.text.TextUtils;

public class Place {
    private String placeId;

    public String getCategoryId() {
        return categoryId;
    }

    private String categoryId;
    private byte[] placeImage;
    private String placeName;
    private String placeAddress;
    private String placeDecription;
    private double placeLat;
    private double placeLong;


    public Place(Builder builder){
        this.categoryId = builder.categoryId;
        this.placeId = builder.placeId;
        this.placeImage = builder.placeImage;
        this.placeName = builder.placeName;
        this.placeAddress = builder.placeAddress;
        this.placeDecription = builder.placeDecription;
        this.placeLat = builder.placeLat;
        this.placeLong = builder.placeLong;
    }

    public String getPlaceId() {
        return placeId;
    }

    public Place setPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public byte[] getPlaceImage() {
        return placeImage;
    }

    public Place setPlaceImage(byte[] placeImage) {
        this.placeImage = placeImage;
        return this;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Place setPlaceName(String placeName) {
        this.placeName = placeName;
        return this;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public Place setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
        return this;
    }

    public String getPlaceDecription() {
        return placeDecription;
    }

    public Place setPlaceDecription(String placeDecription) {
        this.placeDecription = placeDecription;
        return this;
    }

    public double getPlaceLat() {
        return placeLat;
    }

    public Place setPlaceLat(double placeLat) {
        this.placeLat = placeLat;
        return this;
    }

    public double getPlaceLong() {
        return placeLong;
    }

    public Place setPlaceLong(double placeLong) {
        this.placeLong = placeLong;
        return this;
    }

    public static class Builder{
        private String placeId;
        private String categoryId;

        public Builder setCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        private byte[] placeImage;
        private String placeName;
        private String placeAddress;
        private String placeDecription;
        private double placeLat;
        private double placeLong;

        public Builder setPlaceId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public Builder setPlaceImage(byte[] placeImage) {
            this.placeImage = placeImage;
            return this;
        }


        public Builder setPlaceName(String placeName) {
            this.placeName = placeName;
            return this;
        }


        public Builder setPlaceAddress(String placeAddress) {
            this.placeAddress = placeAddress;
            return this;
        }


        public Builder setPlaceDecription(String placeDecription) {
            this.placeDecription = placeDecription;
            return this;
        }


        public Builder setPlaceLat(double placeLat) {
            this.placeLat = placeLat;
            return this;
        }


        public Builder setPlaceLong(double placeLong) {
            this.placeLong = placeLong;
            return this;
        }
        public Place build(){
            return new Place(this);
        }
    }
    public static boolean validateInput(String placeName,String placeAddress,String placeDecription,String categoryId){
        return (TextUtils.isEmpty(placeName) ||
                TextUtils.isEmpty(placeAddress) ||
                TextUtils.isEmpty(placeDecription)||
                TextUtils.isEmpty(categoryId)) ? false:true;
    }
}
