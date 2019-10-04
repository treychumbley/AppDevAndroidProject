package com.example.battleroyale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testing
        Player player1 = new Player(45,45,"Player1");
        Log.i("TestPlay",player1.toString());
        player1.takeDamage(33);
        Log.i("TestPlay",player1.toString());
        player1.takeDamage(33);
        Log.i("TestPlay",player1.toString());
        player1.takeDamage(33);
        Log.i("TestPlay",player1.toString());
        player1.takeDamage(33);
        Log.i("TestPlay",player1.toString());

    }

    public void goToGame(View view){
        Log.i("test", "goToGame!!!!!");
        Intent battleIntent = new Intent(this, GameActivity.class);
        startActivity(battleIntent);
    }

    public void goToSettings(View view){
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    public void getToProfile(View view) {
        Log.i("test", "goToProfile!!!!!");
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
    }
}
