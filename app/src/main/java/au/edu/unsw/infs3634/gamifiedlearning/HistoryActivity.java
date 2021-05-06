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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.Spinner;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;

public class HistoryActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "";
    private ResultDatabase resultDatabase;
    FirebaseAuth mAuth;
    String uid;
    private TextView tvFirstResult;
    private TextView tvFirstDate;
    private TextView tvSecondResult;
    private TextView tvSecondDate;
    private TextView tvThirdResult;
    private TextView tvThirdDate;
    private TextView tvHeading;
    private Button btLeaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //setup intent and retrieve title
        Intent intent = getIntent();
        String title = intent.getStringExtra(INTENT_MESSAGE);

        //retrieve current logged in user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        //set up GUI
        setTitle("Quiz History");
        tvFirstResult = findViewById(R.id.tvFirstResult);
        tvFirstDate = findViewById(R.id.tvFirstDate);
        tvSecondResult = findViewById(R.id.tvSecondResult);
        tvSecondDate = findViewById(R.id.tvSecondDate);
        tvThirdResult = findViewById(R.id.tvThirdResult);
        tvThirdDate = findViewById(R.id.tvThirdDate);
        tvHeading = findViewById(R.id.tvHeading);
        btLeaderboard = findViewById((R.id.btLeaderboard));
        tvHeading.setText("Your Top Results for " + title + " Quiz");

        //retrieve DB list
        resultDatabase = Room.databaseBuilder(getApplicationContext(), ResultDatabase.class, "ResultDatabase").build();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<QuizResult> rawData = (ArrayList<QuizResult>)resultDatabase.quizResultDao().getResults(uid, title);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tvFirstResult.setText(String.valueOf(rawData.get(0).getResult()));
                            tvFirstDate.setText(rawData.get(0).getResultDate());
                        } catch (IndexOutOfBoundsException e){
                            tvFirstResult.setText("No Result Yet");
                            tvFirstDate.setText("");
                        } try {
                            tvSecondResult.setText(String.valueOf(rawData.get(1).getResult()));
                            tvSecondDate.setText(rawData.get(1).getResultDate());
                        } catch (IndexOutOfBoundsException e){
                            tvSecondResult.setText("No Result Yet");
                            tvSecondDate.setText("");
                        } try {
                            tvThirdResult.setText(String.valueOf(rawData.get(2).getResult()));
                            tvThirdDate.setText(rawData.get(2).getResultDate());
                        } catch (IndexOutOfBoundsException e){
                            tvThirdResult.setText("No Result Yet");
                            tvThirdDate.setText("");
                        }
                    }
                });

            }
        });
        resultDatabase.close();

        btLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLeaderboardActivity(title);
            }
        });
    }

    public void launchLeaderboardActivity(String title) {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        intent.putExtra(LeaderboardActivity.INTENT_MESSAGE, title);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent menuIntent = new Intent(this, MenuActivity.class);
                startActivity(menuIntent);
                return true;
            case R.id.menu_profile:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
        }
        return true;
    }

}