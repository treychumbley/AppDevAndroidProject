package com.example.battleroyale;

//initialize shotgun weapon
public class Shotgun extends Weapon {
    public Shotgun(float xCoordinate, float yCoordinate){
        initialAmmo = 15;
        damage = 35;
        firerate = .9;
        weaponType = 1;
        xBuffer = 200;
        yBuffer = 25;
        height = 130;
        width = 300;
        image = R.drawable.shotgun;
        xLocation = xCoordinate;
        yLocation = yCoordinate;
    }
}
