package com.example.superhero;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static com.example.superhero.QueryUtilis.HERO_REQUEST_URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<SuperHero>>, Serializable {
    private static SuperHero currentSuperHero;
    HeroAdapter adapter;
    private TextView mEmptyStateTextView;
    private ListView heroListView;
    Parcelable state;
    ArrayList<SuperHero> superHeroesAll;
    String searchStr;
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
            Loading.setVisibility(GONE);
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
        superHeroesAll = (ArrayList<SuperHero>) superHeroes;
        mEmptyStateTextView.setText("Check superhero name");
        View layoutBottom = findViewById(R.id.layoutBottom);
        layoutBottom.setVisibility(View.VISIBLE);
        ProgressBar Loading = (ProgressBar) findViewById(R.id.loading_spinner);
        if(Loading != null) {
            Loading.setVisibility(GONE);
        }
        if(adapter!=null){
            adapter = null;
        }
        adapter = new HeroAdapter(superHeroes, this);
        heroListView.setAdapter(adapter);
        if(state!=null){
            heroListView.onRestoreInstanceState(state);
        }

    }


    @Override
    public void onLoaderReset(Loader<List<SuperHero>> loader) {
        adapter = new HeroAdapter(superHeroesAll, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.search_view){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public class HeroAdapter extends BaseAdapter implements Filterable {
        private List<SuperHero> superHeroList;
        private List<SuperHero> superHeroListFiltered;
        private Context context;

        public HeroAdapter(List<SuperHero> superHeroes, Context context) {
            superHeroList = superHeroes;
            superHeroListFiltered = superHeroes;
            this.context = context;

        }

        @Override
        public int getCount() {
            return superHeroListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.list_item,null);

            TextView powerView = (TextView) view.findViewById(R.id.power);

            GradientDrawable powerCircle = (GradientDrawable) powerView.getBackground();
            int powerColor = getpowerColor(superHeroListFiltered.get(position).getPower());
            powerView = (TextView) view.findViewById(R.id.power);
            powerView.setText(String.valueOf(superHeroListFiltered.get(position).getPower()));

            powerCircle.setColor(powerColor);

            TextView name = (TextView) view.findViewById(R.id.name);

            name.setText(superHeroListFiltered.get(position).getName());

            TextView family = (TextView) view.findViewById(R.id.family);
            family.setText(superHeroListFiltered.get(position).getRace());

            TextView fullNameTextView = (TextView) view.findViewById(R.id.full_name);
            String fullName = superHeroListFiltered.get(position).getFullName();
            if(fullName == null ||(fullName.length() == 0)){
                fullNameTextView.setText("Unknown");
            } else {
                fullNameTextView.setText(fullName);
            }

            TextView gender = (TextView) view.findViewById(R.id.gender);
            if(superHeroListFiltered.get(position).getGender() != null){
                gender.setText(superHeroListFiltered.get(position).getGender());
            }


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itemIntent = new Intent(MainActivity.this, SelectedItem.class);
                    itemIntent.putExtra("currentSuperHero", superHeroListFiltered.get(position));
                    startActivity(itemIntent);
                }
            });



            return view;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if(constraint == null || constraint.length() == 0) {
                        filterResults.count = superHeroList.size();
                        filterResults.values = superHeroList;
                    }else{
                        searchStr = constraint.toString().toLowerCase();
                        List<SuperHero> resultData = new ArrayList<>();
                        for(SuperHero superHero:superHeroList){
                            if(superHero.getName().toLowerCase().contains(searchStr)){
                                resultData.add(superHero);
                                filterResults.count = resultData.size();
                                filterResults.values = resultData;
                            }
                        }
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    ListView listView = findViewById(R.id.list);
                    TextView textView = findViewById(R.id.emptyView);

                    if(results.count>0) {
                        if(listView.getVisibility() == GONE){
                            listView.setVisibility(View.VISIBLE);
                        }
                        if(textView.getVisibility() == View.VISIBLE){
                                textView.setVisibility(GONE);
                        }
                        superHeroListFiltered = (List<SuperHero>) results.values;
                        notifyDataSetChanged();
                    } else {
                        listView.setVisibility(GONE);
                        textView.setVisibility(View.VISIBLE);
                    }

                }
            };
            return filter;
        }



        private int getpowerColor(double power) {
            int powerColorResourceId = 0;
            int powerFloor = (int) (power);
            if(powerFloor<=10) {
                powerColorResourceId = R.color.power1;
            }
            else if(powerFloor<=20  ) {
                powerColorResourceId = R.color.power2;
            }
            else if(powerFloor<=40 ) {
                powerColorResourceId = R.color.power3;
            }
            else if(powerFloor<=50 ) {
                powerColorResourceId = R.color.power4;
            }
            else if(powerFloor<=60  ) {
                powerColorResourceId = R.color.power5;
            }
            else if(powerFloor<=70  ) {
                powerColorResourceId = R.color.power6;
            }
            else if(powerFloor<=80  ) {
                powerColorResourceId = R.color.power7;
            }
            else if(powerFloor<=90  ) {
                powerColorResourceId = R.color.power8;
            }
            else if(powerFloor<=95  ) {
                powerColorResourceId = R.color.power9;
            }
            else if(powerFloor<=100  ) {
                powerColorResourceId = R.color.power10plus;
            }

            return ContextCompat.getColor(context, powerColorResourceId);
        }



    }

    @Override
    protected void onPause() {
        state = heroListView.onSaveInstanceState();
        super.onPause();
    }

}


