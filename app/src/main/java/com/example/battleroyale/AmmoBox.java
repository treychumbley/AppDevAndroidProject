package com.example.battleroyale;

import android.widget.ImageView;

class AmmoBox extends Object{
        private int ammo = 0;
        public AmmoBox(float xCoordinate, float yCoordinate){
            objectType = 2;
            xBuffer = 130;
            yBuffer = 80;
            width = 150;
            height = 108;
            image = R.drawable.ammo_box;
            xLocation = xCoordinate;
            yLocation = yCoordinate;
        }

        public int setAmmo(Weapon weapon) {
            if (weapon.weaponType == 1)
                ammo = 6;
            else
                ammo = 10;
            return ammo;
        }
    }
