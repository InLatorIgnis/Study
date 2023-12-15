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

    // Declare variables for UI elements
    private TextView questionTextView;
    private TextView scoreTextView;
    private RadioGroup answerRadioGroup;
    private Button submitButton;
    private Button mainMenuButton;

    // Declare variables for quiz logic
    private final List<Question> questionBank = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int noCorrect = 0;
    private final List<Integer> scoreHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        mainMenuButton = findViewById(R.id.mainMenuButton);
        mainMenuButton.setVisibility(View.GONE);
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        scoreTextView.setVisibility(View.INVISIBLE);

        // Retrieve score history
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
        answerOptions3.add(new AnswerOption("Falskt", true));
        questionBank.add(new Question("En router skickar endast vidare de paket som skall till ett annat nät.", answerOptions3));


// Question iv
        List<AnswerOption> answerOptions4 = new ArrayList<>();
        answerOptions4.add(new AnswerOption("Sant", true));
        answerOptions4.add(new AnswerOption("Falskt"));
        questionBank.add(new Question("Ett ICMP skickas alltid tillbaka hela vägen till avsändaren.", answerOptions4));


// Question v
        List<AnswerOption> answerOptions5 = new ArrayList<>();
        answerOptions5.add(new AnswerOption("Sant", true));
        answerOptions5.add(new AnswerOption("Falskt"));
        questionBank.add(new Question("FDM är en analog multiplexeringsteknik som kombinerar analoga signaler.", answerOptions5));


// Question vi
        List<AnswerOption> answerOptions6 = new ArrayList<>();
        answerOptions6.add(new AnswerOption("Sant", true));
        answerOptions6.add(new AnswerOption("Falskt"));
        questionBank.add(new Question("Jitter innebär att fördröjningen mellan en sändare och mottagare varierar så att det tar olika tid för paketen att komma fram.", answerOptions6));


// Question vii
        List<AnswerOption> answerOptions7 = new ArrayList<>();
        answerOptions7.add(new AnswerOption("Sant"));
        answerOptions7.add(new AnswerOption("Falskt", true));
        questionBank.add(new Question("Inom köteorin anger µ medeltiden mellan två betjäningar.", answerOptions7));


// Question viii
        List<AnswerOption> answerOptions8 = new ArrayList<>();
        answerOptions8.add(new AnswerOption("Sant"));
        answerOptions8.add(new AnswerOption("Falskt", true));
        questionBank.add(new Question("LEO-satelliter används till exempel för GPS.", answerOptions8));


// Question ix
        List<AnswerOption> answerOptions9 = new ArrayList<>();
        answerOptions9.add(new AnswerOption("Sant", true));
        answerOptions9.add(new AnswerOption("Falskt"));
        questionBank.add(new Question("LINUX-kommandot if config (som användes i laboration) visar vilka nätverkskort som datorn har och vilka Ethernet- respektive IPadresser som de har.", answerOptions9));


// Question x
        List<AnswerOption> answerOptions10 = new ArrayList<>();
        answerOptions10.add(new AnswerOption("Sant"));
        answerOptions10.add(new AnswerOption("Falskt", true));
        questionBank.add(new Question("ARP gör det möjligt för en dator att ta reda på Ethernetadressen för vilken annan dator som helst på Internet.", answerOptions10));


        displayQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the submit button is clicked.
             * @param v The view that was clicked.
             */
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

    /**
     * Checks if the selected answer is correct. This method is called when the user clicks the "Submit" button.
     * If no answer is selected, a Toast message prompts the user to choose an answer.
     * If an answer is selected, it checks if it is correct, loads the next question,
     * and ends the quiz if there are no more questions.
     */
    private void checkAnswer() {
        int selectedId = answerRadioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String answer = selectedRadioButton.getText().toString();

            // Check if the selected answer is correct
            for (AnswerOption answerOption : questionBank.get(currentQuestionIndex).getAnswerOptionsList()) {
                if (answerOption.getIsCorrect() && answer.equals(answerOption.getAnswerOptionText())) {
                    // Correct answer
                    noCorrect++;
                    break;  // Break out of the loop since we found the correct answer
                }
            }

            // Load next question
            currentQuestionIndex++;
            if (currentQuestionIndex < questionBank.size()) {
                displayQuestion();
            } else {
                endQuiz();
            }
        } else {
            // No answer selected
            showToast("Please select an answer.");
        }
    }


    /**
     * Displays the score history on the screen. This method is called after the quiz ends
     * to show the user their past scores. The scores are retrieved from the scoreHistory list
     * and displayed in a suitable format.
     */
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


    /**
     * Called when the quiz ends. This method clears the screen, displays the score history,
     * and makes the scoreTextView visible.
     */
    private void endQuiz() {
        // Clear the screen
        questionTextView.setVisibility(View.GONE);
        answerRadioGroup.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        mainMenuButton.setVisibility(View.VISIBLE);

        // Display the score history
        showToast("Quiz completed!" + " " + noCorrect + "/" + questionBank.size() + " correct answers");
        scoreHistory.add(noCorrect);
        displayScoreHistory();

        // Make the scoreTextView visible
        saveScoreHistory();
        scoreTextView.setVisibility(View.VISIBLE);

    }

    /**
     * Saves the score history into shared preferences. The key for each score is "Score"
     * concatenated with its index, and the size of the score history is saved with the key "ScoreSize".
     */
    private void saveScoreHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("ScoreHistory", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < scoreHistory.size(); i++) {
            editor.putInt("Score" + i, scoreHistory.get(i));
        }
        editor.putInt("ScoreSize", scoreHistory.size());
        editor.apply();
    }

    /**
     * Retrieves the score history from shared preferences. The scores are retrieved using
     * the keys "Score0", "Score1", etc., up to the size retrieved with the key "ScoreSize".
     */
    private void retrieveScoreHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("ScoreHistory", MODE_PRIVATE);
        int size = sharedPreferences.getInt("ScoreSize", 0);
        scoreHistory.clear(); // Clear the list before adding retrieved scores
        for (int i = 0; i < size; i++) {
            scoreHistory.add(sharedPreferences.getInt("Score" + i, 0));
        }
    }

    /**
     * Displays a  Toast-message on the screen. This method is used to provide feedback to the user.
     *
     * @param message Meddelandet som ska visas i Toast.
     */
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

