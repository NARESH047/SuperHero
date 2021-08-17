package com.example.superhero;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import static com.example.superhero.QueryUtilis.HERO_REQUEST_URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Dog>> {
    private static Dog currentDog;
    DogAdapter adapter;
    private TextView mEmptyStateTextView;
    private ListView heroListView;

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
    public Loader<List<Dog>> onCreateLoader(int id, Bundle args) {

        return new DogLoader(this, HERO_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Dog>> loader, List<Dog> dogs) {

        mEmptyStateTextView.setText("No internet connection");

        ProgressBar Loading = (ProgressBar) findViewById(R.id.loading_spinner);
        if(Loading != null) {
            Loading.setVisibility(View.GONE);
        }


        if(adapter!=null){
            adapter.clear();
        }


        adapter = new DogAdapter(MainActivity.this, dogs);


        heroListView.setAdapter(adapter);


        heroListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                currentDog = adapter.getItem(position);
                Intent itemIntent = new Intent(MainActivity.this, SelectedItem.class);
                startActivity(itemIntent);

            }
        });
    }

    public static Dog getCurrentDog(){
        return currentDog;
    }

    @Override
    public void onLoaderReset(Loader<List<Dog>> loader) {
        adapter.clear();

    }

}


