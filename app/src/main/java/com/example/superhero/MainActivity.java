package com.example.superhero;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.superhero.QueryUtilis.HERO_REQUEST_URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<SuperHero>> {
    private static SuperHero currentSuperHero;
    HeroAdapter adapter;
    private TextView mEmptyStateTextView;
    private ListView heroListView;
    Parcelable state;
    ArrayList<SuperHero> superHeroesForDisplay;
    ArrayList<SuperHero> superHeroesAll;


//    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected) {

            heroListView = (ListView) findViewById(R.id.list);
            mEmptyStateTextView = (TextView) findViewById(R.id.emptyView);
            heroListView.setEmptyView(mEmptyStateTextView);

            LoaderManager loaderManager = getLoaderManager();


            loaderManager.initLoader(1, null, this);

        }
        else{
            ProgressBar Loading = (ProgressBar) findViewById(R.id.loading_spinner);
            Loading.setVisibility(View.GONE);
            TextView internet = (TextView) findViewById(R.id.noInternet);
            internet.setTextSize(24);
            internet.setTextColor(Color.parseColor("#00FFFF"));
            internet.setText("No internet connection");
        }
    }

    @Override
    public Loader<List<SuperHero>> onCreateLoader(int id, Bundle args) {

        return new HeroLoader(this, HERO_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<SuperHero>> loader, List<SuperHero> superHeroes) {
        final ArrayList<SuperHero> superHeroesFinal = (ArrayList<SuperHero>) superHeroes;
        superHeroesAll = superHeroesFinal;
        mEmptyStateTextView.setText("No hero found");
        View layoutBottom = findViewById(R.id.layoutBottom);
        layoutBottom.setVisibility(View.VISIBLE);
        ProgressBar Loading = (ProgressBar) findViewById(R.id.loading_spinner);
        if(Loading != null) {
            Loading.setVisibility(View.GONE);
        }
        if(adapter!=null){
            adapter.clear();
        }
        superHeroesForDisplay = superHeroesAll;
        adapter = new HeroAdapter(MainActivity.this, superHeroesForDisplay);
        heroListView.setAdapter(adapter);
        if(state!=null){
            heroListView.onRestoreInstanceState(state);
        }
        heroListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                currentSuperHero = adapter.getItem(position);
                Intent itemIntent = new Intent(MainActivity.this, SelectedItem.class);
                startActivity(itemIntent);

            }
        });
    }
    public static SuperHero getCurrentSuperHero(){
        return  currentSuperHero;
    }

    @Override
    public void onLoaderReset(Loader<List<SuperHero>> loader) {
        adapter.clear();
    }

    @Override
    protected void onPause() {
        state = heroListView.onSaveInstanceState();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1) {
                    ArrayList<SuperHero> results = new ArrayList<SuperHero>();
                    for (SuperHero x : superHeroesAll) {
                        if (x.getName().contains(newText)) {
                            results.add(x);
                        }
                    }
                    superHeroesForDisplay.clear();
                    superHeroesForDisplay.addAll(results);
                    adapter.notifyDataSetChanged();
                }
                return false;

            }
        });
//        getLoaderManager().restartLoader(1, null, MainActivity.this);
        return true;

    }


}


