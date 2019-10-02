package com.example.battleroyale;

import android.util.*;
//creates players
//keeps track of players health/location/ammo/current weapon

public class Player {
    private int health = 100;
    private double xLocation;
    private double yLocation;
    private int ammo;
    private String username;
    private boolean alive = true;
    private int kills = 0;
    private Weapon weapon;

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


    public void pickUpWeapon(Weapon newWeapon){
        weapon = newWeapon;
        ammo = weapon.initialAmmo;
    }

    public void pickUpAmmoBox(AmmoBox ammoBox){
        ammo += ammoBox.setAmmo(weapon);
    }

    public void pickUpHealthPack(HealthPack healthPack){
        health += healthPack.addHealth();
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
                ", weapon=" + weapon +
                '}';
    }

}
