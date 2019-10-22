package com.example.battleroyale;

import android.widget.ImageView;

//initialize pistol weapon
public class Pistol extends Weapon {
    public Pistol(float xCoordinate, float yCoordinate){
        initialAmmo = 25;
        damage = 20;
        firerate = .5;
        weaponType = 0;
        xBuffer = 100;
        yBuffer = 50;
        height = 110;
        width = 200;
        image = R.drawable.pistol;
        xLocation = xCoordinate;
        yLocation = yCoordinate;
    }
}
