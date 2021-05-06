package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "";
    public ArrayList<Question> questions;
    public ArrayList<Answer> answers;
    TextView tvQuestionNum;
    TextView tvQuestion;
    RadioGroup rgAnswers;
    RadioButton rbAnswerA;
    RadioButton rbAnswerB;
    RadioButton rbAnswerC;
    RadioButton rbAnswerD;
    String userAnswer = "";
    String realAnswer = "";
    Button btSubmit;
    int i;
    int userScore;
    String title = "";
    public static final int FIRST_QUESTION = 0;
    public static final int LAST_QUESTION = 5;
    public static final int CORRECT_ANS = 1;
    public static final int INCORRECT_ANS = 2;
    public static final int NO_ANS = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //set up intent
        Intent intent = getIntent();
        title = intent.getStringExtra(INTENT_MESSAGE);

        //set up quiz questions corresponding to module title and shuffle
        Question thisQuiz = new Question();
        Answer thisAnswer = new Answer();
        questions = (ArrayList<Question>) thisQuiz.getQuestions(title).clone();
        Collections.shuffle(questions);
        answers = (ArrayList<Answer>) thisAnswer.getAnswers(title).clone();

        //set up GUI
        setTitle(title + " Quiz");
        tvQuestionNum = findViewById(R.id.tvQuestionNum);
        tvQuestion = findViewById(R.id.tvQuestion);
        //etAnswer = findViewById(R.id.etAnswer);
        rgAnswers = findViewById(R.id.rgAnswers);
        rbAnswerA = findViewById(R.id.rbAnswerA);
        rbAnswerB = findViewById(R.id.rbAnswerB);
        rbAnswerC = findViewById(R.id.rbAnswerC);
        rbAnswerD = findViewById(R.id.rbAnswerD);
        btSubmit = findViewById(R.id.btSubmit);

        //set up first question
        i = FIRST_QUESTION;
        userScore = 0;
        displayQuizQuestion(i);

        //radio group listener
        rgAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) findViewById(checkedId);
                userAnswer = selected.getText().toString();
            }
        });

            //button click listener
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userResult = checkAnswer(userAnswer);
                //if checkAnswer == CORRECT_ANS, user moves onto next question and gains point
                if (userResult == CORRECT_ANS) {
                    userScore++;
                    i++;
                //if user does not answer, stay on question
                } else if (userResult == NO_ANS) {
                    return;
                //if checkAnswer == INCORRECT_ANS, user does not gain point
                } else {
                    i++;
                }

                //check if that was the last question
                if (i == LAST_QUESTION) {
                    launchResultActivity();
                } else {
                    rbAnswerA.setChecked(false);
                    rbAnswerB.setChecked(false);
                    rbAnswerC.setChecked(false);
                    rbAnswerD.setChecked(false);
                    userAnswer = "";
                    displayQuizQuestion(i);
                }

            }
        });

    }

    public void displayQuizQuestion(int i) {
        //get Question
        tvQuestionNum.setText("Question " + String.valueOf(i + 1));
        tvQuestion.setText(questions.get(i).getQuestionText());

        //get associated answers
        for (int j = 0; j < LAST_QUESTION; j++) {
            if(questions.get(i).getQuestionID() == answers.get(j).getId()) {
                rbAnswerA.setText(answers.get(j).getAnswerA());
                rbAnswerB.setText(answers.get(j).getAnswerB());
                rbAnswerC.setText(answers.get(j).getAnswerC());
                rbAnswerD.setText(answers.get(j).getAnswerD());
                //set real answer
                realAnswer = answers.get(j).getAnswerCorrect();
            }
        }
    }

    public int checkAnswer (String userAnswer) {
        //if user entered nothing
        if (userAnswer.equals("")) {
            Toast.makeText(QuizActivity.this, "Please select an Answer!", Toast.LENGTH_SHORT).show();
            return NO_ANS;
        //if user entered right answer
        } else if (userAnswer.equals(realAnswer)) {
            Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            return CORRECT_ANS;
        //if user entered wrong answer
        } else {
            Toast.makeText(QuizActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
            return INCORRECT_ANS;
        }
    }

    public void launchResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        Bundle extras = new Bundle();
        extras.putString("INTENT_MESSAGE", title);
        extras.putInt("USER_SCORE", userScore);
        intent.putExtras(extras);
        startActivity(intent);
    }
}