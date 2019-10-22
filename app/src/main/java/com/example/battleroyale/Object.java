package com.example.battleroyale;

import android.widget.ImageView;

public class Object {
    public float xLocation;
    public float yLocation;
    public float xBuffer;
    public float yBuffer;
    public int height;
    public int width;
    public int image;
    public int objectType; //0 = player, 1 = Weapon, 2 = AmmoBox, 3 = HealthPack



    public float getXLocation(){
        return xLocation;
    }

    public float getYLocation(){
        return yLocation;
    }

    public int getImage(){return image;}

    public int getHeight() {return height;}

    public int getWidth() {return width;}

    public float getxBuffer(){return xBuffer;}

    public float getyBuffer(){return yBuffer;}

    public void updateLocation(float newX, float newY){
        xLocation = newX;
        yLocation = newY;
    }

}
