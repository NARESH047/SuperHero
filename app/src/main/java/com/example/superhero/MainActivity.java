package com.example.superhero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button openDictionaryButton = findViewById(R.id.openDictionaryButton);
        openDictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemIntent = new Intent(MainActivity.this, DogDictionaryActivity.class);
                startActivity(itemIntent);
            }
        });
        Button button = findViewById(R.id.openAnalysisButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemIntent = new Intent(MainActivity.this, DogAnalysis.class);
                startActivity(itemIntent);
            }
        });

    }
}
