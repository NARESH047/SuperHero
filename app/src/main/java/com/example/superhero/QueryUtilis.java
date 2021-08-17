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

    public static final String HERO_REQUEST_URL =  "https://api.thedogapi.com/v1/breeds";

    public static ArrayList<Dog> extractDogs(String jsonUrl) {

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(createUrl(jsonUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Dog> dogs = new ArrayList<>();

        try {

            JSONArray baseJsonResponse = new JSONArray(jsonResponse);

            for (int i = 0; i < baseJsonResponse.length(); i++) {

                JSONObject currentDogData = baseJsonResponse.getJSONObject(i);

                int id = currentDogData.getInt("id");
                String name = currentDogData.optString("name");
                String breed_group;
                breed_group = currentDogData.optString("breed_group");
                String bred_for = currentDogData.optString("bred_for");
                String origin = currentDogData.optString("origin");
                String countryCode = currentDogData.optString("country_code");
                String temperament = currentDogData.optString("temperament");
                String life_span = currentDogData.optString("life_span");

                JSONObject weightObject = currentDogData.getJSONObject("weight");
                String weight = weightObject.optString("metric");

                JSONObject heightObject = currentDogData.getJSONObject("height");
                String height = heightObject.optString("metric");

                JSONObject imageObject = currentDogData.getJSONObject("image");
                String imageId = imageObject.optString("id");
                URL imageUrl = createUrl(imageObject.optString("url"));

                Dog dog = new Dog(id, name, breed_group, bred_for, origin, temperament, life_span, weight, height, imageId, imageUrl, countryCode);

                dogs.add(dog);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }

        return dogs;
    }

    static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
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
            urlConnection.setRequestProperty("x-api-key", "cfad93c5-b405-4113-bfd7-e28283b336e5");
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

