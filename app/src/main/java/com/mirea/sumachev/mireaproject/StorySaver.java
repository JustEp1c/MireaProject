package com.mirea.sumachev.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StorySaver {

    private SharedPreferences savedStories;
    private Gson gson;

    public StorySaver(Context context) {
        savedStories = context.getSharedPreferences("storySaver", MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveStories(ArrayList<Story> stories) {

        SharedPreferences.Editor editor = savedStories.edit();
        editor.putString("savedStories", gson.toJson(stories));
        editor.apply();

    }

    public ArrayList<Story> loadStories() {

        String savedStoriesString = savedStories.getString("savedStories", "empty");
        Type savedStoriesType = new TypeToken<ArrayList<Story>>(){}.getType();
        ArrayList<Story> stories = gson.fromJson(savedStoriesString, savedStoriesType);

        if (stories != null) {
            return stories;
        }
        else return new ArrayList<>();
    }
}
