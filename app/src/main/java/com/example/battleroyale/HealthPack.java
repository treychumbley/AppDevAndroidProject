package com.example.battleroyale;

public class HealthPack extends Object {
    private int health = 15;
    public HealthPack(float xCoordinate, float yCoordinate){
        objectType = 3;
        xLocation = xCoordinate;
        yLocation = yCoordinate;
    }

    public int addHealth(){
        return health;
    }
}
