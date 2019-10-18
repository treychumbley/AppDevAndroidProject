package com.example.battleroyale;

//initialize pistol weapon
public class Pistol extends Weapon {
    public Pistol(float xCoordinate, float yCoordinate){
        initialAmmo = 25;
        damage = 20;
        firerate = .5;
        weaponType = 0;
        xBuffer = 100;
        yBuffer = 50;
        xLocation = xCoordinate;
        yLocation = yCoordinate;
    }
}
