package com.example.superhero;

import java.net.URL;


public class SuperHero {

    private String mHeight, mWeight;
    private int mIntelligence, mSpeed, mDurability, mCombat;

    private int mPower;

    private String mName = "null";

    private String mRace = "null";


    private String mFullName = "null", mBirthPlace = "null", mPublisher = "null", mGroupAffiliation = "null";

    private String mGender= "null";

    private URL mImageUrl;

    public SuperHero(int power, String name, String race, String fullName, String gender, URL imageUrl, int intelligence, int speed, int durability, int combat, String birthPlace, String publisher, String groupAffiliation, String height, String weight) {
        mPower = power;
        mName = name;
        mRace = race;
        mFullName = fullName;
        mGender = gender;
        mImageUrl = imageUrl;
        mIntelligence = intelligence;
        mSpeed = speed;
        mDurability = durability;
        mCombat = combat;
        mBirthPlace = birthPlace;
        mPublisher = publisher;
        mGroupAffiliation = groupAffiliation;
        mHeight = height;
        mWeight = weight;
    }


    public int getPower() {
        return mPower;
    }


    public String getName() {
        return mName;
    }

    public String getRace() {
        if(mRace == null){
            mRace = "unknown";
        }
        return mRace;
    }


    public String getFullName() {
        if(mFullName.equals("null")){
            mFullName = "unknown";
            return mFullName;
        }
        return mFullName;
    }

    public String getGender() {
        if(mGender == null){
            mGender = "unknown";
        }
        return mGender;
    }

    public URL getImageUrl() {
        return mImageUrl;
    }

    public int getStrength() {
        return mCombat;
    }

    public int getDurability() {
        return mDurability;
    }

    public int getIntelligence() {
        return mIntelligence;
    }

    public String getBirthPlace() {
        if(mBirthPlace == null){
            mBirthPlace = "unknown";
        }
        return mBirthPlace;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public String getGroupAffiliation() {
        return mGroupAffiliation;
    }

    public String getHeightAndWeight() {
        if (mWeight == "0 cm" && mHeight == "0 kg") {
            return "Not known";
        }
        else if (mWeight == "0 kg") {
            return mHeight;
        }
        else if (mHeight == "0 cm") {
            return mWeight;
        } else {
            return mHeight + ", " + mWeight;
        }
    }
}
