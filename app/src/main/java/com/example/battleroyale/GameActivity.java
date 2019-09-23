package com.example.battleroyale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.controlwear.virtual.joystick.android.JoystickView;


public class GameActivity extends AppCompatActivity {

    private static final float MAX_BUG_SPEED_DP_PER_S = 300f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView mTextViewAngleLeft = (TextView) findViewById(R.id.joystickView_left);
        //TextView mTextViewStrengthLeft = (TextView) findViewById(R.id.joystickView_left);

        final JoystickView joystickLeft = (JoystickView) findViewById(R.id.joystickView_left);
        final ImageView playerView = findViewById(R.id.playerImage);
        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onMove(int angle, int strength) {

                //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(30, 30);
                //playerView.setLayoutParams(layoutParams);

                if (joystickLeft.isEnabled()) {

                    playerView.setTranslationX(angle);
                    playerView.setTranslationY(angle);
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
