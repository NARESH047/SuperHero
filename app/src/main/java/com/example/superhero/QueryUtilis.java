package com.example.superhero;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryUtilis {

    public static final String HERO_REQUEST_URL =  "https://akabab.github.io/superhero-api/api/all.json";

    public static ArrayList<SuperHero> extractHeros(String jsonUrl) {
        Log.v("QueryUtilis", "Test: extractHeros");


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(createUrl(jsonUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }


        ArrayList<SuperHero> superHeroes = new ArrayList<>();

        try {

            JSONArray baseJsonResponse = new JSONArray(jsonResponse);


            for (int i = 0; i < baseJsonResponse.length(); i++) {

                JSONObject currentHeroData = baseJsonResponse.getJSONObject(i);

                String name = currentHeroData.getString("name");
                JSONObject powerStats = currentHeroData.getJSONObject("powerstats");
                int power = powerStats.getInt("power");
                int intelligence = powerStats.getInt("intelligence");
                int speed = powerStats.getInt("speed");
                int durability = powerStats.getInt("power");
                int combat = powerStats.getInt("combat");
                JSONObject biography = currentHeroData.getJSONObject("biography");
                String fullName = biography.getString("fullName");
                String birthPlace = biography.getString("placeOfBirth");
                String publisher = biography.getString("publisher");
                JSONObject appearance = currentHeroData.getJSONObject("appearance");
                String gender = appearance.getString("gender");
                String race = appearance.getString("race");
                JSONArray heightArray = appearance.getJSONArray("height");
                String height = heightArray.getString(1);
                JSONArray weightArray = appearance.getJSONArray("weight");
                String weight =  weightArray.getString(1);
                JSONObject connections = currentHeroData.getJSONObject("connections");
                String groupAffiliation = connections.getString("groupAffiliation");

                JSONObject images = currentHeroData.getJSONObject("images");
                URL imageUrl = createUrl(images.getString("md"));
//                Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());

                SuperHero superHero = new SuperHero(power, name, race, fullName, gender, imageUrl, intelligence, speed, durability, combat, birthPlace, publisher, groupAffiliation+".", height, weight);

                superHeroes.add(superHero);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }

        return superHeroes;
    }

    static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e("LOG_TAG", "Error with creating URL", exception);
            return null;
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if(url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 );
            urlConnection.setConnectTimeout(15000 );
            urlConnection.connect();
            int code = urlConnection.getResponseCode();
            if(code==200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

        } catch (IOException e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

//    public void getImage(URL imageUrl){
//        try {
//            Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}

