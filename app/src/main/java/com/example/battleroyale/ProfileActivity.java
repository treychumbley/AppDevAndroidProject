package com.example.battleroyale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";

    //Name
    EditText mEditText;

    //Counter for prompts
    private int promptCounter;

    //List containing preferences
    ArrayList<String> results;

    //Theme variables
    private TextView textView4;
    private Button button;
    private Button button2;
    private Button button3;

    //Alliance variables
    private TextView textView5;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Name display
        TextView userProfileName = findViewById(R.id.userProfileName);

        mEditText = findViewById(R.id.edit_text);

        promptCounter = 0;

        results = new ArrayList<>();

        textView4 = findViewById(R.id.textView4);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        textView5 = findViewById(R.id.textView5);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        //Variable visibility modification
        button.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);

        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);

    }

    public void thirdPrompt(String previousInput){
        results.add(previousInput);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);

    }

    public void save(View v) {
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;

        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            //mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void load(View v) {
        FileInputStream fis = null;

        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }

            mEditText.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void handleClick(View view){
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (view.getId() == button.getId()){
            promptCounter++;
        }

        // Make button click show next prompt & add text of button clicked to list
        if (promptCounter == 1) {
            thirdPrompt("Theme: " + buttonText);
        }
        if (promptCounter == 2){
            results.add("\n" + "Team: " + buttonText);

            Intent gameIntent = new Intent(this, GameActivity.class);
            gameIntent.putStringArrayListExtra("Results", results);
            startActivity(gameIntent);
        }
    }

}
