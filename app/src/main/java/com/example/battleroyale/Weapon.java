package com.example.battleroyale;

//creates weapons
//super class for pistol, shotgun (add more later)
//variables: initial ammo, damage, fire rate
public class Weapon extends Object {
    public int initialAmmo;
    public int damage;
    public double firerate;
    public int weaponType; // 0 = Pistol, 1 = Shotgun

    public Weapon(){
        objectType = 1;
    }
}
