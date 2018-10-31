package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", "thevipulsharma").apply();

        String username = sharedPreferences.getString("username", "");
        Log.i("info", username);

        ArrayList<String> friends = new ArrayList<>();
        friends.add("Monica");
        friends.add("Chandler");
        friends.add("Joey");
        friends.add("Rachel");
        friends.add("Ross");
        friends.add("Phoebe");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends )).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newFriends = new ArrayList<>();
        try {
            newFriends = (ArrayList<String>)ObjectSerializer.deserialize(sharedPreferences.getString("friends",
                    ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("newFriends", newFriends.toString());
    }
}
