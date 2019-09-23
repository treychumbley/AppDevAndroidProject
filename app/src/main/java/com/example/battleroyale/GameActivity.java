package com.example.battleroyale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import java.lang.Math;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class GameActivity extends AppCompatActivity {

    private static final float MAX_BUG_SPEED_DP_PER_S = 300f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Left Joystick
        final JoystickView joystickLeft = (JoystickView) findViewById(R.id.joystickView_left);

        //Creat a string for ImageView Label
        final String IMAGEVIEW_TAG = "icon bitmap";
        ImageView imageView = new ImageView(this);


        //Player Object Image
        final ImageView playerView = findViewById(R.id.playerImage);


        //playerView.setImageBitmap();

        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onMove(int angle, int strength) {
                float playerX = (float) Math.cos(angle);
                float playerY = (float) Math.sin(angle);


                if (joystickLeft.isEnabled()) {

                    playerView.setTranslationX(playerX);
                    playerView.setTranslationY(playerY);
                }

            }
        });


        //mTextViewAngleRight = (TextView) findViewById(R.id.textView_angle_right);
        //mTextViewStrengthRight = (TextView) findViewById(R.id.textView_strength_right);
        //mTextViewCoordinateRight = findViewById(R.id.textView_coordinate_right);

        final JoystickView joystickRight = (JoystickView) findViewById(R.id.joystickView_right);
        joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onMove(int angle, int strength) {
                /*TextView mTextViewAngleRight.setText(angle + "°");
                TextView mTextViewStrengthRight;
                mTextViewStrengthRight.setText(strength + "%");
                TextView mTextViewCoordinateRight;
                mTextViewCoordinateRight.setText(
                        String.format("x%03d:y%03d",
                                joystickRight.getNormalizedX(),
                                joystickRight.getNormalizedY())*/
            }
        });
    }
}
