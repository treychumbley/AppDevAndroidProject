package com.example.battleroyale;

public class HealthPack extends Object {
    private int health = 15;
    public HealthPack(float xCoordinate, float yCoordinate){
        objectType = 3;
        xBuffer = 150;
        yBuffer = 125;
        height = 152;
        width = 200;
        image = R.drawable.med_kit;
        xLocation = xCoordinate;
        yLocation = yCoordinate;
    }

    public int addHealth(){
        return health;
    }
}
