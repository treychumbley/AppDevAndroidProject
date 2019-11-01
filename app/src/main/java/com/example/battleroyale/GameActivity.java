package com.example.battleroyale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class GameActivity extends AppCompatActivity{

    //Text view initialization for username reference
    private static final String FILE_NAME = "example.txt";

    TextView textView3;

    TextView textView7;

    String text;

    FileInputStream fis;

    ImageView playerIcon;
    ImageView bullet1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //sets game to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_game);

        playerIcon = findViewById(R.id.playerIcon);
        bullet1 = findViewById(R.id.bulletView1);
        bullet1.setVisibility(bullet1.GONE);

//
        //Initialize profile name to show up in game through textview
        textView3 = findViewById(R.id.textView3);



        Intent resultsIntent = getIntent();
        ArrayList<String> results = resultsIntent.getStringArrayListExtra("Results");

        textView7 = findViewById(R.id.textView7);

        textView7.setText("UI" + "\n" + results);

        if (results == null){
            textView7.setVisibility(View.INVISIBLE);
        }

        try {
            fis = openFileInput(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();


        while (true){
            try {
                if (!((text = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(text).append("\n");
            textView7.setVisibility(View.VISIBLE);
        }

            if (((sb) == null)){
                sb.append("Player");
            }



        textView3.setText("User: " + sb.toString());

        //Left Joystick
        final JoystickView joystickLeft = findViewById(R.id.joystickView_left);
        //Create movement listener
        joystickLeft.setOnTouchListener(handleMove);
        // Right Joystick
        final JoystickView joystickRight = findViewById(R.id.joystickView_right);
        //Create rotation listener
        joystickRight.setOnTouchListener(handleShoot);


        //HashMap  to keep track of objects on the screen
        HashMap<Object,ImageView> objectList = new HashMap<Object,ImageView>();


        //Testing for presentation
        Shotgun shotgun = new Shotgun(500f, 500f);
        Pistol pistol = new Pistol(310f,300f);
        Player player = new Player(150f,150f,"User1");
        AmmoBox ammoBox = new AmmoBox(0f,0f);
        HealthPack healthPack = new HealthPack(500F,50f);

        addObjectToScreen(pistol,objectList);
        addObjectToScreen(shotgun, objectList);
        addObjectToScreen(player, objectList);
        addObjectToScreen(ammoBox, objectList);
        addObjectToScreen(healthPack,objectList);

        player.pickUpAmmoBox(ammoBox);



        Log.i("testCollision", "Player colliding with ammoBox is " + checkCollision(ammoBox,player));
        Log.i("testCollision", "Player colliding with healthPack is " + checkCollision(healthPack,player));
        Log.i("testCollision", "Player colliding with ammoBox is " + checkCollision(shotgun,player));
        Log.i("testCollision","Player colliding with pistol is " + checkCollision(pistol,player));

        //gameStatus(objectList);
        Log.i("testCollision",player.toString());


    }




    private View.OnTouchListener handleMove = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final float x = event.getX();
            final float y = event.getY();

            if (event.getAction() == MotionEvent.ACTION_MOVE) {

                final float buttonCenterX = ((float) v.getWidth()) / 2;
                final float buttonCenterY = ((float) v.getHeight()) / 2;
                final float deltaX = x - buttonCenterX;
                final float deltaY = y - buttonCenterY;

                playerIcon.setX(playerIcon.getX() + deltaX * 0.1f);
                playerIcon.setY(playerIcon.getY() + deltaY * 0.1f);

                float PlayerX = playerIcon.getX();
                float PlayerY = playerIcon.getY();

                DisplayMetrics display = getResources().getDisplayMetrics();

                final float topBound = -90;
                final float bottomBound = display.heightPixels - 375f;
                final float rightBound = display.widthPixels - 150f;
                final float leftBound = -85f;


                if (PlayerX < leftBound) {
                    playerIcon.setX(leftBound);
                }
                if (PlayerX > rightBound) {
                    playerIcon.setX(rightBound);
                }
                if (PlayerY < topBound) {
                    playerIcon.setY(topBound);
                }
                if (PlayerY > bottomBound) {
                    playerIcon.setY(bottomBound);
                }
            }
            return false;
        }

    };

    private View.OnTouchListener handleShoot = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final float x = event.getX();
            final float y = event.getY();

            final float buttonCenterX = ((float) v.getWidth()) / 2;
            final float buttonCenterY = ((float) v.getHeight()) / 2;

            final float deltaX = x - buttonCenterX;
            final float deltaY = y - buttonCenterY;

            float bulletX = bullet1.getX();
            float bulletY = bullet1.getY();

            float bulletWidth = bullet1.getWidth();
            float bulletHeight = bullet1.getHeight();

            float translationX = bulletX + deltaX ;
            float translationY = bulletY + deltaY ;

            bullet1.setX(playerIcon.getX() + (bulletWidth * .85f));
            bullet1.setY(playerIcon.getY() + (bulletHeight * 1.1f));



            if (event.getAction() == MotionEvent.ACTION_MOVE) {

                ObjectAnimator bulletStream1X = ObjectAnimator.ofFloat(bullet1,"translationX", translationX);
                bulletStream1X.setDuration(1000);

                ObjectAnimator bulletStream1Y = ObjectAnimator.ofFloat(bullet1,"translationY", translationY);
                bulletStream1Y.setDuration(1000);

                bulletStream1X.start();
                bulletStream1Y.start();

                bullet1.setVisibility(bullet1.VISIBLE);



                DisplayMetrics display = getResources().getDisplayMetrics();

                final float topBound = -90;
                final float bottomBound = display.heightPixels - 375f;
                final float rightBound = display.widthPixels - 150f;
                final float leftBound = -85f;

                if (bulletX < leftBound || bulletX > rightBound || bulletY < topBound || bulletY > bottomBound) {

                    bullet1.setVisibility(View.GONE);

                }
            }
            return false;
        }
    };

        public ConstraintLayout callLayout(){
            final ConstraintLayout layout = findViewById(R.id.gameLayout);
            return layout;
        }

        //adds imageView for object
        public void addObjectToScreen(Object object, HashMap objectList){
            final ImageView image = new ImageView(this);
            image.setBackgroundResource(object.getImage());

            image.setX(object.getXLocation());
            image.setY(object.getYLocation());
            callLayout().addView(image);


            image.getLayoutParams().width = object.getWidth();
            image.getLayoutParams().height = object.getHeight();
            //found from https://stackoverflow.com/questions/44614271/android-resize-imageview-programmatically
            image.requestLayout();
            image.invalidate();

            objectList.put(object,image);
        }

        public boolean checkCollision(Object object, Object playerObject){
            boolean Collision = false;
            float xDifference = playerObject.getXLocation() - object.getXLocation();
            float yDifference = playerObject.getYLocation() - object.getYLocation();
            float PlayerXDifference = object.getXLocation() - playerObject.getXLocation();
            float PlayerYDifference = object.getYLocation() - playerObject.getYLocation();

            if(xDifference <= object.xBuffer && xDifference >= 0 && yDifference <= object.yBuffer && yDifference >= 0)
                Collision = true;
            else if (PlayerXDifference <= playerObject.getxBuffer() && PlayerXDifference >= 0 && PlayerYDifference <= playerObject.getyBuffer() && PlayerYDifference >= 0)
                Collision = true;

            return Collision;
        }

        public void collisionType(Object object, Player player, HashMap<Object,ImageView> objectList){

            if (object.getObjectType() == 2){
                try{
                    player.pickUpAmmoBox((AmmoBox) object);
                    Log.i("testCollision","Got ammo");
                    callLayout().removeView(objectList.get(object));
                } catch(Exception e){
                    Log.i("testCollision", "Unable to add ammo.");
                }
            } else if (object.getObjectType() == 1){
                try{
                    player.pickUpWeapon((Weapon) object);
                    Log.i("testCollision","Weapon was picked up");
                    callLayout().removeView(objectList.get(object));
                } catch (Exception e){
                    Log.i("testCollision","Unable to pick up weapon");
                }
            } else if (object.getObjectType() == 3){
                try{
                    player.pickUpHealthPack((HealthPack)object);
                    Log.i("testCollision","Picked up health");
                    callLayout().removeView(objectList.get(object));
                }catch (Exception e){
                    Log.i("testCollision", "Unable to pick up health");
                }
            }
        }

        public void gameStatus(HashMap<Object,ImageView> objectList){
            for (Object i : objectList.keySet()){
                if(i.getObjectType() == 0){
                    for (Object j : objectList.keySet()){
                        if(checkCollision(j,i)){
                            collisionType(j,(Player) i,objectList);
                        }
                    }
                }
            }
        }


    }

