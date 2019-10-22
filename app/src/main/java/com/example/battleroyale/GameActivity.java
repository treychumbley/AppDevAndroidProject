package com.example.battleroyale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

        //Left Joystick
        final JoystickView joystickLeft = findViewById(R.id.joystickView_left);
        //Create movement listener
        joystickLeft.setOnTouchListener(handleTouch);

        // Right Joystick
        final JoystickView joystickRight = findViewById(R.id.joystickView_right);
        //Create rotation listener
        joystickRight.setOnTouchListener(handleRotate);



        Pistol pistol = new Pistol(0f,0f);
        addObjectToScreen(pistol);

        Shotgun shotgun = new Shotgun(1200f, 0f);
        addObjectToScreen(shotgun);



        Player player = new Player(90f,90f,"User1");
        AmmoBox ammoBox = new AmmoBox(0f,1500f);
        HealthPack healthPack = new HealthPack(1200f,1500f);

        addObjectToScreen(ammoBox);
        addObjectToScreen(healthPack);

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

        //adds imageView for object
        public void addObjectToScreen(Object object){
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
        }

        public boolean checkCollision(Object object, Object playerObject){
            boolean Collision = false;
            if(playerObject.getXLocation() - object.getXLocation() <= object.xBuffer && playerObject.getXLocation() - object.getXLocation() >= 0 && playerObject.getYLocation() - object.getYLocation() <= object.yBuffer && playerObject.getYLocation() - object.getYLocation() >= 0)
                Collision = true;
            return Collision;
        }

    }

