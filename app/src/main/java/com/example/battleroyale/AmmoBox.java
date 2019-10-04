package com.example.battleroyale;

class AmmoBox extends Object{
        private int ammo = 0;
        public AmmoBox(){
            objectType = 1;
        }

        public int setAmmo(Weapon weapon) {
            if (weapon.weaponType == 1)
                ammo = 6;
            else
                ammo = 10;
            return ammo;
        }
    }
