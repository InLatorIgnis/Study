package com.example.study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private TextView scoreTextView; // Add this
    private RadioGroup answerRadioGroup;
    private Button submitButton;
    private Button mainMenuButton; // Add this
    private List<Question> questionBank = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int noCorrect = 0;
    private List<Integer> scoreHistory = new ArrayList<>(); // Add this
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMenuButton = findViewById(R.id.mainMenuButton); // Add this
        mainMenuButton.setVisibility(View.GONE); // Add this
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
         submitButton = findViewById(R.id.submitButton);
        scoreTextView.setVisibility(View.INVISIBLE);
        retrieveScoreHistory();
        // Question i
        List<AnswerOption> answerOptions1 = new ArrayList<>();
        answerOptions1.add(new AnswerOption("Sant"));
        answerOptions1.add(new AnswerOption("Falskt", true));
        questionBank.add(new Question("UDP-segment innehåller sekvensnummer.", answerOptions1));

        // Question ii
        List<AnswerOption> answerOptions2 = new ArrayList<>();
        answerOptions2.add(new AnswerOption("Sant"));
        answerOptions2.add(new AnswerOption("Falskt", true));
        questionBank.add(new Question("En dator får sin Ethernetadress från en DHCP-server.", answerOptions2));

        // Question iii
        List<AnswerOption> answerOptions3 = new ArrayList<>();
        answerOptions3.add(new AnswerOption("Sant"));
        answerOptions3.add(new AnswerOption("Falskt",true));
        questionBank.add(new Question("En router skickar endast vidare de paket som skall till ett annat nät.", answerOptions3));


// Question iv
        List<AnswerOption> answerOptions4 = new ArrayList<>();
        answerOptions4.add(new AnswerOption("Sant",true));
        answerOptions4.add(new AnswerOption("Falskt"));
        questionBank.add(new Question("Ett ICMP skickas alltid tillbaka hela vägen till avsändaren.", answerOptions4));


// Question v
        List<AnswerOption> answerOptions5 = new ArrayList<>();
        answerOptions5.add(new AnswerOption("Sant",true));
        answerOptions5.add(new AnswerOption("Falskt"));
        questionBank.add(new Question("FDM är en analog multiplexeringsteknik som kombinerar analoga signaler.", answerOptions5));


// Question vi
        List<AnswerOption> answerOptions6 = new ArrayList<>();
        answerOptions6.add(new AnswerOption("Sant",true));
        answerOptions6.add(new AnswerOption("Falskt"));
        questionBank.add(new Question("Jitter innebär att fördröjningen mellan en sändare och mottagare varierar så att det tar olika tid för paketen att komma fram.", answerOptions6));


// Question vii
        List<AnswerOption> answerOptions7 = new ArrayList<>();
        answerOptions7.add(new AnswerOption("Sant"));
        answerOptions7.add(new AnswerOption("Falskt",true));
        questionBank.add(new Question("Inom köteorin anger µ medeltiden mellan två betjäningar.", answerOptions7));


// Question viii
        List<AnswerOption> answerOptions8 = new ArrayList<>();
        answerOptions8.add(new AnswerOption("Sant"));
        answerOptions8.add(new AnswerOption("Falskt",true));
        questionBank.add(new Question("LEO-satelliter används till exempel för GPS.", answerOptions8));


// Question ix
        List<AnswerOption> answerOptions9 = new ArrayList<>();
        answerOptions9.add(new AnswerOption("Sant",true));
        answerOptions9.add(new AnswerOption("Falskt"));
        questionBank.add(new Question("LINUX-kommandot if config (som användes i laboration) visar vilka nätverkskort som datorn har och vilka Ethernet- respektive IPadresser som de har.", answerOptions9));


// Question x
        List<AnswerOption> answerOptions10 = new ArrayList<>();
        answerOptions10.add(new AnswerOption("Sant"));
        answerOptions10.add(new AnswerOption("Falskt",true));
        questionBank.add(new Question("ARP gör det möjligt för en dator att ta reda på Ethernetadressen för vilken annan dator som helst på Internet.", answerOptions10));


    displayQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        // Clear previous answer options
        answerRadioGroup.clearCheck();
        answerRadioGroup.removeAllViews();

        // Get the current question
        Question temp = questionBank.get(currentQuestionIndex);

        // Set question text
        questionTextView.setText(temp.getQuestionText());

        // Add radio buttons for each answer option
        for (AnswerOption answerOption : temp.getAnswerOptionsList()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answerOption.getAnswerOptionText());
            answerRadioGroup.addView(radioButton);
        }
    }

    private void showToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void checkAnswer() {
        int selectedId = answerRadioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String answer = selectedRadioButton.getText().toString();

            // Check if the selected answer is correct
            for (AnswerOption answerOption : questionBank.get(currentQuestionIndex).getAnswerOptionsList()) { // första halvan kan vara onödig
                 {
                     if(answerOption.getAnswerOptionText().equals(answer) && answerOption.getIsCorrect()) {
                         // Correct answer
                         noCorrect++;
                     }
                        // Load next question
                        currentQuestionIndex++;
                        if (currentQuestionIndex < questionBank.size()) {
                            displayQuestion();
                        } else {
                            endQuiz();
                        }

                    }
                    return;
                }

        } else {
            // No answer selected
            showToast("Please select an answer.");
        }
    }


    private void displayScoreHistory() { // Add this
        StringBuilder scoreHistoryText = new StringBuilder("Score History:\n");
        for (int i = 0; i < scoreHistory.size(); i++) {
            scoreHistoryText.append("Attempt ").append(i + 1).append(": ").append(scoreHistory.get(i)).append("\n");
        }
        scoreTextView.setText(scoreHistoryText.toString());

        mainMenuButton.setOnClickListener(new View.OnClickListener() { // Add this
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }



    private void endQuiz() {
        // Clear the screen
        questionTextView.setVisibility(View.GONE);
        answerRadioGroup.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        mainMenuButton.setVisibility(View.VISIBLE);

        // Display the score history
        showToast("Quiz completed!"+" "+noCorrect+"/"+questionBank.size()+" correct answers");
        scoreHistory.add(noCorrect);
        displayScoreHistory();

        // Make the scoreTextView visible
        saveScoreHistory();
        scoreTextView.setVisibility(View.VISIBLE);

    }

    private void saveScoreHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("ScoreHistory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i=0; i<scoreHistory.size(); i++) {
            editor.putInt("Score"+i, scoreHistory.get(i));
        }
        editor.putInt("ScoreSize", scoreHistory.size());
        editor.apply();
    }

    private void retrieveScoreHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("ScoreHistory", MODE_PRIVATE);
        int size = sharedPreferences.getInt("ScoreSize", 0);
        for(int i=0; i<size; i++) {
            scoreHistory.add(sharedPreferences.getInt("Score"+i, 0));
        }
    }
}

