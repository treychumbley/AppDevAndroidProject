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
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Math;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class GameActivity extends AppCompatActivity {

    //Text view initialization for username reference
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        addObjectToScreen(callLayout(),creatShotgunImage());

        //testing
        Player player1 = new Player(45,45,"Player1");
        Pistol pistol = new Pistol();
        Shotgun shotgun = new Shotgun();
        HealthPack healthPack = new HealthPack();
        AmmoBox ammoBox = new AmmoBox();
        Log.i("TestPlay",player1.toString());
        player1.takeDamage(33);
        Log.i("TestPlay",player1.toString());
        player1.pickUpWeapon(pistol);
        player1.takeDamage(33);
        Log.i("TestPlay",player1.toString());
        player1.pickUpHealthPack(healthPack);
        player1.pickUpAmmoBox(ammoBox);
        player1.takeDamage(33);
        Log.i("TestPlay",player1.toString());
        player1.pickUpWeapon(shotgun);
        player1.pickUpAmmoBox(ammoBox);
        player1.takeDamage(33);
        Log.i("TestPlay",player1.toString());

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
        public ImageView createAmmoImage(){

            final ImageView ammo = new ImageView(this);

            ammo.setBackgroundResource(R.drawable.ammo_box);

            return ammo;
        }

        public ImageView createPistolImage(){
            final ImageView pistol = new ImageView(this);

            pistol.setBackgroundResource(R.drawable.pistol);

            return pistol;
        }

        public ImageView creatShotgunImage(){
            final ImageView shotgun = new ImageView(this);

            shotgun.setBackgroundResource(R.drawable.shotgun);

            return shotgun;
        }

        public ImageView createHealthPackImage(){
            final ImageView healthPack = new ImageView(this);

            healthPack.setBackgroundResource(R.drawable.med_kit);

            return healthPack;
        }

        public void addObjectToScreen(ConstraintLayout layout, ImageView imageView){
            layout.setX(0);
            layout.setY(0);
            //ammo.requestLayout();
//            ammo.getLayoutParams().height = 20;
//            ammo.getLayoutParams().width = 20;
//            ammo.setScaleType(ImageView.ScaleType.FIT_XY);
            layout.addView(imageView);
        }

    }

