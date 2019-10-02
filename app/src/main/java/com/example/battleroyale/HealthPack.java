package com.example.battleroyale;

public class HealthPack extends Object {
    private int health = 15;
    public HealthPack(){
        objectType = 2;
    }

    public int addHealth(){
        return health;
    }
}
