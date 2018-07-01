package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float correctAnswers = 0;
    float totalQuestions = 5;
    boolean qOneAnswered = false;
    boolean qTwoAnswered = false;
    boolean qThreeAnswered = false;
    boolean qFourAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Checks the correctness of question one and updates the score.
     */
    public void questionOneAnswered(View v) {
        if (v.getId() == R.id.q_one_a) {
            correctAnswers += 1;
        } else {
            if (qOneAnswered) {
                correctAnswers -= 1;
            }
        }
        qOneAnswered = true;
    }

    /**
     * Checks the correctness of question two and updates the score.
     */
    public void questionTwoAnswered(View v) {
        switch (v.getId()) {
            case R.id.q_two_a:
                correctAnswers += 0.50;
                break;
            case R.id.q_two_d:
                correctAnswers += 0.50;
                break;
            default:
                if (qTwoAnswered) {
                    correctAnswers -= 1;
                }
                break;
        }
        qTwoAnswered = true;
    }

    /**
     * Checks the correctness of question three and updates the score.
     */
    public void questionThreeAnswered(View v) {
        if (v.getId() == R.id.q_three_b) {
            correctAnswers += 1;
        } else {
            if (qThreeAnswered) {
                correctAnswers -= 1;
            }
        }
        qThreeAnswered = true;
    }

    /**
     * Checks the correctness of question four and updates the score.
     */
    public void questionFourAnswered(View v) {
        if (v.getId() == R.id.q_four_d) {
            correctAnswers += 1;
        } else {
            if (qFourAnswered) {
                correctAnswers -= 1;
            }
        }
        qFourAnswered = true;
    }

    /**
     * Clears the checked boxes in question two when the resetQuiz method is called.
     */
    private void uncheckAllChildren(ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View v = vg.getChildAt(i);
            if (v instanceof CheckBox) {
                ((CheckBox) v).setChecked(false);
            }
        }
    }

    /**
     * Activates the submit button
     * Checks the correctness of question five and updates the score.
     * Displays the total score by the calling the displayScore method
     */
    public void submitAnswersTapped(View v) {
        EditText qFive = findViewById(R.id.q_five_answer);
        if (qFive.getText().toString().equalsIgnoreCase("Herbicide")) {
            correctAnswers += 1;
        }
        Log.v("Five Answer: ", qFive.getText().toString());

        displayScore();

    }

    /**
     * Calculates the score percentage and creates a toast to display the score.
     */
    private void displayScore() {
        Log.v("Correct Answers", Float.toString(correctAnswers));
        float score = correctAnswers / totalQuestions * 100;
        score = Math.round(score);
        Log.v("Score: ", Float.toString(score * 100) + "%");
        String scoreMessage = "Score: " + score + "%";
        Toast.makeText(MainActivity.this, scoreMessage, Toast.LENGTH_LONG).show();
        resetQuiz();
    }

    /**
     * Clears all answers and resets the quiz questions.
     */
    private void resetQuiz() {
        RadioGroup one = findViewById(R.id.q_one_group);
        one.clearCheck();

        RadioGroup three = findViewById(R.id.q_three_group);
        three.clearCheck();

        RadioGroup four = findViewById(R.id.q_four_group);
        four.clearCheck();

        LinearLayout checkboxes = findViewById(R.id.checkbox_question);
        uncheckAllChildren(checkboxes);

        EditText qFive = findViewById(R.id.q_five_answer);
        qFive.getText().clear();

        correctAnswers = 0;
        qOneAnswered = false;
        qTwoAnswered = false;
        qThreeAnswered = false;
        qFourAnswered = false;
    }

}