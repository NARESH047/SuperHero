package com.example.superhero;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

public class SelectedItem extends AppCompatActivity {

    Dog dog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clicked_item);
        dog = MainActivity.getCurrentDog();
        DogAsyncTask task = new DogAsyncTask();
        task.execute();
        if(dog != null) {
            TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
            nameTextView.setText(dog.getName());

            TextView bredGroupTextView = findViewById(R.id.bredGroupTextView);
            String bredGroup = dog.getBreedGroup();
            if(bredGroup !=null) {
                bredGroupTextView.setText(bredGroup);
            }
            TextView originTextView = findViewById(R.id.originTextView);
            String origin = dog.getOrigin();
            if(origin !=null) {
                originTextView.setText(origin);
            }
            TextView heightTextView = findViewById(R.id.heightTextView);
            String height = dog.getHeight();
            if(height!=null) {
                heightTextView.setText(height);
            }
            TextView weightTextView = findViewById(R.id.weightTextView);
            String weight = dog.getWeight();
            if(weight!=null) {
                weightTextView.setText(weight);
            }
            TextView lifespanTextView = findViewById(R.id.lifespanTextView);
            String lifespan = dog.getLife_span();
            if(lifespan!=null) {
                lifespanTextView.setText(lifespan);
            }
            TextView temperamantTextView = findViewById(R.id.temperamantTextView);
            String temperament = dog.getTemperament();
            if(temperament!=null) {
                temperamantTextView.setText(temperament+".");
            }
            TextView bredForTextView = findViewById(R.id.bredForTextView);
            bredForTextView.setText(String.valueOf(dog.getBred_for())+".");
            bredForTextView.setTextColor(getResources().getColor(getidColor(dog.getId())));
        }
        ImageView selectedImageView = (ImageView) findViewById(R.id.selectedImageView);

        selectedImageView.setBackgroundColor(getResources().getColor(getidColor(dog.getId())));
    }


    private class DogAsyncTask extends AsyncTask<URL, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(URL... urls) {
            // Create URL object
            URL imageUrl = (dog.getImageUrl());

            try {
                Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
                return Bitmap.createScaledBitmap(bmp,301, 233, true);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Bitmap currentDogImage) {
            if (currentDogImage == null) {
                return;
            }
            ProgressBar Loading = (ProgressBar) findViewById(R.id.loading_spinner_selectedImageView);
            if(Loading != null) {
                Loading.setVisibility(View.GONE);
            }
            ImageView DogImage = (ImageView) findViewById(R.id.selectedImageView);
            DogImage.setImageBitmap(currentDogImage);
        }

}

    private int getidColor(int id) {
        int idColorResourceId = 0;
        int idFloor = id;
        if(idFloor == 0 || idFloor%6 ==0 ) {
            idColorResourceId = R.color.id1;
        }
        else if(idFloor%5 ==0) {
            idColorResourceId = R.color.id2;
        }
        else if(idFloor%4 ==0) {
            idColorResourceId = R.color.id3;
        }
        else if(idFloor%3 ==0) {
            idColorResourceId = R.color.id4;
        }
        else if(idFloor%2 ==0) {
            idColorResourceId = R.color.id5;
        }
        else {
            idColorResourceId = R.color.id6;
        }

        return idColorResourceId;
    }
}