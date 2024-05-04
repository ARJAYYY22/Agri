package com.example.agricare.commons;

import android.content.Context;

import com.example.agricare.models.Agriculture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {

    public String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

    public List<Agriculture> getCrops(Context context, String language){
        String jsonFileString = new JsonParser().getJsonFromAssets(context, "pananim_"+language+".json");
        Gson gson = new Gson();
        Type agricultureType =new TypeToken<List<Agriculture>>() { }.getType();
        return gson.fromJson(jsonFileString, agricultureType);
    }

    public List<Agriculture> getLiveStock(Context context, String language){
        String jsonFileString = new JsonParser().getJsonFromAssets(context, "livestock_"+language+".json");
        Gson gson = new Gson();
        Type agricultureType =new TypeToken<List<Agriculture>>() { }.getType();
        return gson.fromJson(jsonFileString, agricultureType);
    }

    public List<Agriculture> getFish(Context context, String language){
        String jsonFileString = new JsonParser().getJsonFromAssets(context, "fish_"+language+".json");
        Gson gson = new Gson();
        Type agricultureType =new TypeToken<List<Agriculture>>() { }.getType();
        return gson.fromJson(jsonFileString, agricultureType);
    }
}
