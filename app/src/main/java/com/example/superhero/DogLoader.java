package com.example.superhero;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class DogLoader extends AsyncTaskLoader<List<Dog>> {


    private static final String LOG_TAG = DogLoader.class.getName();


    private String mUrl;

    public DogLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }


    @Override
    public List<Dog> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        List<Dog> dogs = QueryUtilis.extractDogs(mUrl);
        return dogs;
    }


}
