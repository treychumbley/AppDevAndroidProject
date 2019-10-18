package com.example.battleroyale;

import android.util.*;
//creates players
//keeps track of players health/location/ammo/current weapon

public class Player extends Object{
    private int health = 100;
    private int ammo;
    private String username;
    private boolean alive = true;
    private int kills = 0;
    private Weapon weapon;

    //initialize player
    public Player(float xCoordinate, float yCoordinate, String userID){
        xLocation = xCoordinate;
        yLocation = yCoordinate;
        username = userID;
        objectType = 0;
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
        if (health > 100)
            health = 100;
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
