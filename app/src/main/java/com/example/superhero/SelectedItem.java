package com.example.superhero;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedItem extends AppCompatActivity {

    SuperHero superHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clicked_item);
        superHero = MainActivity.getCurrentSuperHero();
        if(superHero != null) {
            TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
            nameTextView.setText(superHero.getName());

            TextView fullNameTV = findViewById(R.id.fullNameTextView);
            String fullName = superHero.getFullName();
            if(fullName !=null) {
                fullNameTV.setText(fullName);
            }
            TextView genderTV = findViewById(R.id.genderTextView);
            String gender = superHero.getGender();
            if(gender !=null) {
                genderTV.setText(gender);
            }
            TextView raceTv = findViewById(R.id.raceTextView);
            String race = superHero.getRace();
            if(race!=null) {
                raceTv.setText(race);
            }
            TextView birthPlaceTv = findViewById(R.id.birthPlaceTextView);
            String birthPlace = superHero.getBirthPlace();
            if(birthPlace!=null) {
                birthPlaceTv.setText(birthPlace);
            }
            TextView publisherTV = findViewById(R.id.publisherTextView);
            String publisher = superHero.getPublisher();
            if(publisher!=null) {
                publisherTV.setText(publisher);
            }
            TextView groupAffiliationTV = findViewById(R.id.groupAffiliationTextView);
            String groupAffiliation = superHero.getGroupAffiliation();
            if(groupAffiliation!=null) {
                groupAffiliationTV.setText(groupAffiliation);
            }
            TextView intelligenceTV = findViewById(R.id.intelligenceTextView);
            int intelligence = superHero.getIntelligence();
            intelligenceTV.setText(String.valueOf(intelligence));
            TextView speedTV = findViewById(R.id.speedTextView);
            int speed = superHero.getSpeed();
            speedTV.setText(String.valueOf(speed));
            TextView strengthTV = findViewById(R.id.strengthTextView);
            int strength = superHero.getStrength();
            strengthTV.setText(String.valueOf(strength));
            TextView durabilityTV = findViewById(R.id.durabilityTextView);
            int durability = superHero.getDurability();
            durabilityTV.setText(String.valueOf(durability));
            TextView powerTV = findViewById(R.id.powerTextVIew);
            String power = String.valueOf(superHero.getPower());
            powerTV.setText(power);
            TextView heightAndWeightTextView = findViewById(R.id.heightAndWeightTextView);
            String heightAndWeight = String.valueOf(superHero.getHeightAndWeight());
            heightAndWeightTextView.setText(heightAndWeight);
            ImageView HeroImage = (ImageView) findViewById(R.id.selectedImageView);
            HeroImage.setImageBitmap(superHero.getImage());
        }
    }

}