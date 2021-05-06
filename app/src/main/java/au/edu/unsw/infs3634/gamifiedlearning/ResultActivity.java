package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;

public class ResultActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "";
    public static final int USER_RESULT = 0;
    public static final int BAD_RESULT = 2;
    public static final int GOOD_RESULT = 4;
    FirebaseAuth mAuth;
    String uid;
    private ResultDatabase resultDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //set up intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int userScore = extras.getInt("USER_SCORE");
        String title = extras.getString("INTENT_MESSAGE");
        resultDatabase = Room.databaseBuilder(getApplicationContext(), ResultDatabase.class, "ResultDatabase").build();

        //get logged int user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        //set up GUI
        setTitle(title + " Quiz Results");
        TextView tvHeading = findViewById(R.id.tvHeading);
        TextView tvResult = findViewById(R.id.tvResult);
        TextView tvPrompt = findViewById(R.id.tvPrompt);
        Button btModule = findViewById(R.id.btModule);

        //set up result and prompt values
        tvResult.setText(String.valueOf(userScore) + "/5");
        tvPrompt.setText(determinePrompt(userScore));

        //set up button click listener
        btModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertResult(userScore, title);
                launchModuleActivity(title);
            }
        });
        resultDatabase.close();
    }

    //determine prompt based of score
    public String determinePrompt(int userScore) {
        String prompt = "";
        if (userScore <= BAD_RESULT) {
            prompt = "Not your best result, go back to the readings and try again!";
        } else if (userScore <= GOOD_RESULT) {
            prompt = "Great start! Go back to the readings to cement your knowledge.";
        } else {
            prompt = "Full mark! Go back to your readings soon to secure your learning";
        }

        return prompt;
    }

    public void insertResult(int userResult, String title) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(calendar.getTime());

        QuizResult quizResult = new QuizResult(uid, title, userResult, date);
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                resultDatabase.quizResultDao().insertQuizResult(quizResult);
            }
        });
    }

    public void launchModuleActivity(String title) {
        Intent intent = new Intent(this, ModuleActivity.class);
        intent.putExtra(ModuleActivity.INTENT_MESSAGE, title);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //back button does not go back to quiz activity
    }

}
