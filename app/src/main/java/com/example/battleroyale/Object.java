package com.example.battleroyale;

public class Object {
    public float xLocation;
    public float yLocation;
    public float xBuffer;
    public float yBuffer;
    public int objectType; //0 = player, 1 = Weapon, 2 = AmmoBox, 3 = HealthPack

    public float getXLocation(){
        return xLocation;
    }

    public float getYLocation(){
        return yLocation;
    }

    public void updateLocation(float newX, float newY){
        xLocation = newX;
        yLocation = newY;
    }

}
