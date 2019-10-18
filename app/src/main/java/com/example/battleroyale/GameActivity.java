package com.example.battleroyale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.Math;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class GameActivity extends AppCompatActivity {

    //Text view initialization for username reference
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //sets game to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_game);


        // commented code caused program to crash

        //Profile User Name reference from profile page
//        Intent profileNameIntent = getIntent();
//        String userProfileName = profileNameIntent.getStringExtra("User Name");
//        textView4 = findViewById(R.id.userProfileName);
//        textView4.setText(userProfileName);

        //Left Joystick
        final JoystickView joystickLeft = findViewById(R.id.joystickView_left);
        //Create movement listener
        joystickLeft.setOnTouchListener(handleTouch);

        // Right Joystick
        final JoystickView joystickRight = findViewById(R.id.joystickView_right);
        //Create rotation listener
        joystickRight.setOnTouchListener(handleRotate);



        createShotgunImage(0f,0f);
        createShotgunImage(150f,0f);



        Player player = new Player(90f,90f,"User1");
        AmmoBox ammoBox = new AmmoBox(90f,90f);
        HealthPack healthPack = new HealthPack(0f,0f);

        Log.i("testCollision", "Player colliding with ammoBox is " + checkCollision(player,ammoBox));
        Log.i("testCollision", "Player colliding with healthPack is " + checkCollision(player,healthPack));

    }


    private View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final float x = event.getX();
            final float y = event.getY();

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                Log.i("click", x + "");
                Log.i("click", y + "");

                final float buttonCenterX = ((float) v.getWidth()) / 2;
                final float buttonCenterY = ((float) v.getHeight()) / 2;
                final float deltaX = x - buttonCenterX;
                final float deltaY = y - buttonCenterY;

                Log.i("button ", "deltaX " + deltaX);
                Log.i("button ", "deltaY " + deltaY);

                final ImageView playerIcon = findViewById(R.id.playerImage);
                playerIcon.setX(playerIcon.getX() + deltaX * 0.5f);
                playerIcon.setY(playerIcon.getY() + deltaY * 0.5f);
            }


            return false;
        }

    };
    private View.OnTouchListener handleRotate = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final float x = event.getX();
            final float y = event.getY();

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                final ImageView playerIcon = findViewById(R.id.playerImage);
                final float buttonCenterX = ((float) v.getWidth()) / 2;
                final float buttonCenterY = ((float) v.getHeight()) / 2;
                final float deltaX = x - buttonCenterX;
                final float deltaY = y - buttonCenterY;
                //final float buttonRotation =


                //playerIcon.setRotationX(playerIcon.getRotationX() + deltaX * 0.2f);

                Log.i("rotation", playerIcon.getRotation() + " ");
                Log.i("rotationX", playerIcon.getRotationX() + " ");
                Log.i("rotationY", playerIcon.getRotationY() + " ");

                double playerRotation = Math.atan2(v.getRotationX() - buttonCenterY, v.getRotationY() - buttonCenterX);
//                playerIcon.setRotation(playerIcon.getRotation() + (90f*.2f));
                playerIcon.setRotation((float) playerRotation);
                double dx = buttonCenterX - v.getRotationX();
                double dy = buttonCenterY - v.getRotationY();


                Log.i("rotation ", playerIcon.getRotation() + " ");
                Log.i("rotationX", playerIcon.getRotationX() + " ");
                Log.i("rotationY", playerIcon.getRotationY() + " ");
            }

            return false;
        }
    };
//        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
////            @RequiresApi(api = Build.VERSION_CODES.Q)
////            @Override
////            public void onMove(int angle, int strength) {
////                float playerX = (float) Math.cos(angle);
////                float playerY = (float) Math.sin(angle);
////
////
////                if (joystickLeft.isEnabled()) {
////
////                    playerView.setTranslationX(playerX);
////                    playerView.setTranslationY(playerY);
////                }
////
////            }
////        });


        //mTextViewAngleRight = (TextView) findViewById(R.id.textView_angle_right);
        //mTextViewStrengthRight = (TextView) findViewById(R.id.textView_strength_right);
        //mTextViewCoordinateRight = findViewById(R.id.textView_coordinate_right);

//        final JoystickView joystickRight = (JoystickView) findViewById(R.id.joystickView_right);
//        joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
//            @SuppressLint("DefaultLocale")
//            @Override
//            public void onMove(int angle, int strength) {
//                /*TextView mTextViewAngleRight.setText(angle + "°");
//                TextView mTextViewStrengthRight;
//                mTextViewStrengthRight.setText(strength + "%");
//                TextView mTextViewCoordinateRight;
//                mTextViewCoordinateRight.setText(
//                        String.format("x%03d:y%03d",
//                                joystickRight.getNormalizedX(),
//                                joystickRight.getNormalizedY())*/
//            }
//        });

        public ConstraintLayout callLayout(){
            final ConstraintLayout layout = findViewById(R.id.gameLayout);
            return layout;
        }

        //adds Ammo Box to Screen at random location
        public void createAmmoImage(float xLoc, float yLoc){

            final ImageView ammo = new ImageView(this);
            final int height = 108;
            final int width = 150;

            ammo.setBackgroundResource(R.drawable.ammo_box);

            addObjectToScreen(callLayout(), ammo, width,height,xLoc,yLoc);
        }

        public void createPistolImage(float xLoc, float yLoc){
            final ImageView pistol = new ImageView(this);
            final int height = 110;
            final int width = 200;

            pistol.setBackgroundResource(R.drawable.pistol);

            addObjectToScreen(callLayout(), pistol, width,height,xLoc,yLoc);
        }

        public void createShotgunImage(float xLoc, float yLoc){
            final ImageView shotgun = new ImageView(this);
            final int height = 138;
            final int width = 300;

            shotgun.setBackgroundResource(R.drawable.shotgun);

            addObjectToScreen(callLayout(), shotgun, width,height,xLoc,yLoc);
        }

        public void createHealthPackImage(float xLoc, float yLoc){
            final ImageView healthPack = new ImageView(this);

            healthPack.setBackgroundResource(R.drawable.med_kit);

            addObjectToScreen(callLayout(), healthPack, 100,100,xLoc,yLoc);
        }

        public void addObjectToScreen(ConstraintLayout layout, ImageView imageView, int width, int height, float xLocation, float yLocation){
            imageView.setX(xLocation);
            imageView.setY(yLocation);
            layout.addView(imageView);


            imageView.getLayoutParams().width = width;
            imageView.getLayoutParams().height = height;
            //found from https://stackoverflow.com/questions/44614271/android-resize-imageview-programmatically
            imageView.requestLayout();
            imageView.invalidate();
        }

        public boolean checkCollision(Object object1, Object object2){
            boolean Collision = false;
            if(object1.getXLocation() == object2.getXLocation() && object1.getYLocation() == object2.getYLocation())
                Collision = true;
            return Collision;
        }

    }

