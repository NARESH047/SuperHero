package com.example.superhero;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class HeroLoader extends AsyncTaskLoader<List<SuperHero>> {

    private String mUrl;

    public HeroLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }


    @Override
    public List<SuperHero> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        List<SuperHero> superHeroes = QueryUtilis.extractHeros(mUrl);
        return superHeroes;
    }


}
