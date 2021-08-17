package com.example.superhero;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class DogAdapter extends ArrayAdapter<Dog> {

    public DogAdapter(Context context, List<Dog> dogs) {
        super(context, 0, dogs);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Dog currentDog = getItem(position);

        TextView idView = (TextView) listItemView.findViewById(R.id.id);

        GradientDrawable idCircle = (GradientDrawable) idView.getBackground();
        int idColor =     ContextCompat.getColor(getContext(), getidColor(currentDog.getId()));

        idView = (TextView) listItemView.findViewById(R.id.id);
        idView.setText(String.valueOf(currentDog.getId()));

        idCircle.setColor(idColor);


        TextView name = (TextView) listItemView.findViewById(R.id.name);

        name.setText(currentDog.getName());

        TextView breedGroup = (TextView) listItemView.findViewById(R.id.breed_group);
        breedGroup.setText(currentDog.getBreedGroup());


        TextView bredFor = (TextView) listItemView.findViewById(R.id.bred_for);

        bredFor.setText(currentDog.getBred_for());

        TextView origin = (TextView) listItemView.findViewById(R.id.origin);
        origin.setText(currentDog.getOrigin());

        return listItemView;
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

