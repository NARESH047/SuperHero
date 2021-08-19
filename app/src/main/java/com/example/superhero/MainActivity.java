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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import static com.example.superhero.QueryUtilis.HERO_REQUEST_URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<SuperHero>> {
    private static SuperHero currentSuperHero;
    HeroAdapter adapter;
    private TextView mEmptyStateTextView;
    private ListView heroListView;
    SearchView searchView;
    Parcelable state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
//        searchView = (SearchView) findViewById(R.id.search_bar);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                MainActivity.this.adapter.getFilter().filter(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                MainActivity.this.adapter.getFilter().filter(newText);
//                return true;
//            }
//        });

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

        mEmptyStateTextView.setText("No internet connection");
        View layoutBottom = findViewById(R.id.layoutBottom);
        layoutBottom.setVisibility(View.VISIBLE);
        ProgressBar Loading = (ProgressBar) findViewById(R.id.loading_spinner);
        if(Loading != null) {
            Loading.setVisibility(View.GONE);
        }


        if(adapter!=null){
            adapter.clear();
        }


        adapter = new HeroAdapter(MainActivity.this, superHeroes);


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


}


