package com.example.superhero;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class HeroAdapter extends ArrayAdapter<SuperHero> implements Filterable {
    SuperHero currentSuperHero;
    View listItemView;

    public HeroAdapter(Context context, List<SuperHero> superHeroes) {
        super(context, 0, superHeroes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        currentSuperHero = getItem(position);

        TextView powerView = (TextView) listItemView.findViewById(R.id.power);

        GradientDrawable powerCircle = (GradientDrawable) powerView.getBackground();
        int powerColor = getpowerColor(currentSuperHero.getPower());
        powerView = (TextView) listItemView.findViewById(R.id.power);
        powerView.setText(String.valueOf(currentSuperHero.getPower()));

        powerCircle.setColor(powerColor);


        TextView name = (TextView) listItemView.findViewById(R.id.name);

        name.setText(currentSuperHero.getName());

        TextView family = (TextView) listItemView.findViewById(R.id.family);
        family.setText(currentSuperHero.getRace());


        TextView fullName = (TextView) listItemView.findViewById(R.id.full_name);

        fullName.setText(currentSuperHero.getFullName());

        TextView gender = (TextView) listItemView.findViewById(R.id.gender);
        if(currentSuperHero.getGender() != null){
            gender.setText(currentSuperHero.getGender());
        }

        return listItemView;
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

        return ContextCompat.getColor(getContext(), powerColorResourceId);
    }

}

