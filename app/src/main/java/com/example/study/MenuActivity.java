package com.example.study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MenuActivity is an activity that shows the menu of the application.
 */
public class MenuActivity extends AppCompatActivity {

    // Button to start the quiz
    private Button startQuizButton;

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle
     * contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize the startQuizButton
        startQuizButton = findViewById(R.id.startQuizButton);

        // Set an onClick listener for the startQuizButton
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the startQuizButton has been clicked.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                // Create an intent to start the MainActivity
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                // Start the MainActivity
                startActivity(intent);
            }
        });

        Button deleteHistoryButton = findViewById(R.id.deleteHistoryButton);

        // Set an onClick listener for the deleteHistoryButton
        deleteHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraseSharedPreferences();
            }
        });
    }

    // Declare the method outside of the onCreate method
    public void eraseSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("ScoreHistory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Radera allt från SharedPreferences
        editor.apply();
        showToast("History deleted.");
    }
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}