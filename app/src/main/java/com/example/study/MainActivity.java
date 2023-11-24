// app/src/main/java/com.example.Study/MainActivity.java
package com.example.study;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void checkAnswer() {
        int selectedId = answerRadioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String answer = selectedRadioButton.getText().toString();

            if (answer.equals("Paris")) {
                // Correct answer
                showToast("Correct!");
            } else {
                // Incorrect answer
                showToast("Incorrect. Try again!");
            }
        } else {
            // No answer selected
            showToast("Please select an answer.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
