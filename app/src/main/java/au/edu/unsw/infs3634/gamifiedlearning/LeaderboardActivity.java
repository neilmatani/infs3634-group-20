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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class LeaderboardActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "";
    private ResultDatabase resultDatabase;
    String title;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        //setup intent and retrieve title
        Intent intent = getIntent();
        title = intent.getStringExtra(INTENT_MESSAGE);

        //setup GUI
        TextView tvFirstUser = findViewById(R.id.tvFirstUser);
        TextView tvFirstResult = findViewById(R.id.tvFirstResult);
        TextView tvSecondUser = findViewById(R.id.tvSecondUser);
        TextView tvSecondResult = findViewById(R.id.tvSecondResult);
        TextView tvThirdUser = findViewById(R.id.tvThirdUser);
        TextView tvThirdResult = findViewById(R.id.tvThirdResult);

        //get leader results from DB
        resultDatabase = Room.databaseBuilder(getApplicationContext(), ResultDatabase.class, "ResultDatabase").build();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<QuizResult> leaderArray = (ArrayList<QuizResult>)resultDatabase.quizResultDao().getLeaderResult(title);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tvFirstUser.setText(leaderArray.get(0).getUser());
                            tvFirstResult.setText(String.valueOf(leaderArray.get(0).getResult()));
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            tvFirstUser.setText("No Leader Yet!");
                            tvFirstResult.setText("");
                        } try {
                            tvSecondUser.setText(leaderArray.get(1).getUser());
                            tvSecondResult.setText(String.valueOf(leaderArray.get(1).getResult()));
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            tvSecondUser.setText("No Leader Yet!");
                            tvSecondResult.setText("");
                        } try {
                            tvThirdUser.setText(leaderArray.get(2).getUser());
                            tvThirdResult.setText(String.valueOf(leaderArray.get(2).getResult()));
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            tvThirdUser.setText("No Leader Yet!");
                            tvThirdResult.setText("");
                        }
                    }
                });
            }
        });
        resultDatabase.close();
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