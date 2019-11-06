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

    // User inputted name
    private EditText myEditText;

    // Counter for prompts
    private static int promptCounter;

    // List containing preferences
    private static ArrayList<String> results;

    // Theme variables
    private TextView themePrompt;
    private Button theme1;
    private Button theme2;
    private Button theme3;

    // Alliance variables
    private TextView teamPrompt;
    private Button team1;
    private Button team2;
    private Button team3;

    private Button button;
    private String buttonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        //User inputted name attribute
        myEditText = findViewById(R.id.edit_text);

        //Prompt for profile creation
        promptCounter = 0;

        //List to store profile preferences
        results = new ArrayList<>();

        //Theme attributes
        themePrompt = findViewById(R.id.themePrompt);
        theme1 = findViewById(R.id.theme1);
        theme2 = findViewById(R.id.theme2);
        theme3 = findViewById(R.id.theme3);

        //Team attributes
        teamPrompt = findViewById(R.id.teamPrompt);
        team1 = findViewById(R.id.team1);
        team2 = findViewById(R.id.team2);
        team3 = findViewById(R.id.team3);

        //Variable visibility modification
        theme1.setVisibility(View.INVISIBLE);
        theme2.setVisibility(View.INVISIBLE);
        theme3.setVisibility(View.INVISIBLE);
        themePrompt.setVisibility(View.INVISIBLE);

        team1.setVisibility(View.INVISIBLE);
        team2.setVisibility(View.INVISIBLE);
        team3.setVisibility(View.INVISIBLE);
        teamPrompt.setVisibility(View.INVISIBLE);

    }

    //Method that stores inputted name into results list and reveals the second prompt of selections
    private void secondPrompt(String previousInput){
        results.add(previousInput);
        team1.setVisibility(View.VISIBLE);
        team2.setVisibility(View.VISIBLE);
        team3.setVisibility(View.VISIBLE);
        teamPrompt.setVisibility(View.VISIBLE);

    }

    public void saveText(View v) {
        String text = myEditText.getText().toString();
        FileOutputStream fos = null;

        theme1.setVisibility(View.VISIBLE);
        theme2.setVisibility(View.VISIBLE);
        theme3.setVisibility(View.VISIBLE);
        themePrompt.setVisibility(View.VISIBLE);

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            //myEditText.getText().clear();
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

    public void loadText(View v) {

        FileInputStream fis = null;

        theme1.setVisibility(View.VISIBLE);
        theme2.setVisibility(View.VISIBLE);
        theme3.setVisibility(View.VISIBLE);
        themePrompt.setVisibility(View.VISIBLE);

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }

            myEditText.setText(sb.toString());

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

    public void createProfile(View view){

        button = (Button) view;
        buttonText = button.getText().toString();

        // Checks if any of the visible buttons are clicked in order to increment the counter
        if (view.getId() == button.getId()){
            promptCounter++;
        }

        // Make button click show next prompt & add text of button clicked to list
        if (promptCounter == 1) {
            secondPrompt("Theme: " + buttonText);
        }

        if (promptCounter == 2){
            results.add("\n" + "Team: " + buttonText);

            // Goes to the game page along with the list of user preferences
            Intent gameIntent = new Intent(this, GameActivity.class);
            gameIntent.putStringArrayListExtra("Results", results);
            startActivity(gameIntent);
        }
    }

}
