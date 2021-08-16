package com.example.superhero;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class HeroLoader extends AsyncTaskLoader<List<SuperHero>> {


    private static final String LOG_TAG = HeroLoader.class.getName();


    private String mUrl;

    public HeroLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "Test: onStartLoading");

        forceLoad();
    }


    @Override
    public List<SuperHero> loadInBackground() {
        Log.v(LOG_TAG, "Test: loadInBackground");

        if (mUrl == null) {
            return null;
        }

        List<SuperHero> superHeroes = QueryUtilis.extractHeros(mUrl);
        return superHeroes;
    }


}
