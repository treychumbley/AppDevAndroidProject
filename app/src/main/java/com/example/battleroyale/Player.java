package com.example.battleroyale;

import android.util.*;
//creates players
//keeps track of players health/location/ammo/current weapon

public class Player {
    private int health = 100;
    private int xLocation;
    private int yLocation;
    private int ammo;
    private String username;
    private boolean alive = true;
    private int kills = 0;

    //initialize player
    public Player(int xCoordinate, int yCoordinate, String userID){
        xLocation = xCoordinate;
        yLocation = yCoordinate;
        username = userID;
    }

    //check if player is alive
    public boolean checkStatus(int health){
        if (health <= 0){
            alive = false;
        }
        return alive;
    }


    //subtracts damage from health
    public boolean takeDamage(int damage){
        health = health - damage;
        return checkStatus(health);
    }

    @Override
    public String toString() {
        return "Player{" +
                "health=" + health +
                ", xLocation=" + xLocation +
                ", yLocation=" + yLocation +
                ", ammo=" + ammo +
                ", username='" + username + '\'' +
                ", alive=" + alive +
                ", kills=" + kills +
                '}';
    }


    public static void main(String[] args){
        Player player1 = new Player(45,45,"Player1");
        Log.i("Test",player1.toString());
        player1.takeDamage(33);
        Log.i("Test",player1.toString());
        player1.takeDamage(33);
        Log.i("Test",player1.toString());
        player1.takeDamage(33);
        Log.i("Test",player1.toString());
        player1.takeDamage(33);
        Log.i("Test",player1.toString());
    }
}
