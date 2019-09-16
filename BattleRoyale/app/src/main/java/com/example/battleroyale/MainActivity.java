package com.example.battleroyale;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final TextView mTextViewAngleLeft = (TextView) findViewById(R.id.textView_angle_left);
//        final TextView mTextViewStrengthLeft = (TextView) findViewById(R.id.textView_strength_left);
//
//        JoystickView joystickLeft = (JoystickView) findViewById(R.id.joystickView_left);
//        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
//            @Override
//            public void onMove(int angle, int strength) {
//                mTextViewAngleLeft.setText(angle + "°");
//                mTextViewStrengthLeft.setText(strength + "%");
//            }
//        });
//
//        final TextView mTextViewAngleRight = (TextView) findViewById(R.id.textView_angle_right);
//        final TextView mTextViewStrengthRight = (TextView) findViewById(R.id.textView_strength_right);
//        final TextView mTextViewCoordinateRight = findViewById(R.id.textView_coordinate_right);
//
//        final JoystickView joystickRight = (JoystickView) findViewById(R.id.joystickView_right);
//        joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
//            @SuppressLint("DefaultLocale")
//            @Override
//            public void onMove(int angle, int strength) {
//                mTextViewAngleRight.setText(angle + "°");
//                mTextViewStrengthRight.setText(strength + "%");
//                mTextViewCoordinateRight.setText(
//                        String.format("x%03d:y%03d",
//                                joystickRight.getNormalizedX(),
//                                joystickRight.getNormalizedY())
//                );
//            }
//        });

    }
}
