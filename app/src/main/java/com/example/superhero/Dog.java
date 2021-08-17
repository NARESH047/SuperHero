package com.example.superhero;

import java.net.URL;


public class Dog {

    private String mHeight, mWeight;

    private int mId;

    private String mName;

    private String mBreed_group, mBred_for;


    private String mLife_span, mImageId;

    private String mOrigin, mCountryCode, mTemperament;

    private URL mImageUrl;

    public Dog(int id, String name, String breed_group, String bred_for, String origin, String temperament, String life_span, String weight, String height, String imageId, URL imageUrl, String countryCode) {
        mName = name;
        mId=id;
        mBred_for = bred_for;
        mBreed_group = breed_group;
        mImageId = imageId;
        mImageUrl = imageUrl;
        mLife_span = life_span;
        mOrigin = origin;
        mTemperament =temperament;
        mWeight = weight;
        mHeight = height;
        mCountryCode = countryCode;
    }


    public int getId() {
        return mId;
    }


    public String getName() {
        return mName;
    }

    public String getBred_for() {
        if(mBred_for == null|| mBred_for == ""){
            mBred_for = "COMMON";
        }
        return mBred_for;
    }


    public String getBreedGroup() {
        if(mBreed_group == null || mBreed_group == ""){
            mBreed_group = "CANNINE";
        }
        return mBreed_group;
    }

    public String ImageId() {
        return mImageId;
    }

    public URL getImageUrl() {
        return mImageUrl;
    }

    public String getLife_span() {
        return mLife_span;
    }

    public String getOrigin() {
        if (mOrigin == null || mOrigin == "") {
            if(mCountryCode!=null && mCountryCode!=""){
                mOrigin = mCountryCode;
            } else mOrigin = "Unknown";
        }
        return mOrigin;
    }

    public String getTemperament() {
        return mTemperament;
    }

    public String getHeight() {
        return mHeight;
    }
    public  String getWeight(){
        return mWeight;
    }
}
