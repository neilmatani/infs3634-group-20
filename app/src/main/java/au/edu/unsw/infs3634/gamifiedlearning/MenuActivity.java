//This activity holds cardview of main menu
package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ModuleAdapter mAdapter;
    FirebaseAuth mAuth;
    String uid;
    private ResultDatabase resultDatabase;
    int[] averageQuizResult = new int[3];
    ArrayList<Module> modules = new ArrayList<Module>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        System.out.println("Activity created");

        //get logged in user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        //setup Recyclerview and adapter
        setTitle("Home");
        mRecyclerView = findViewById(R.id.rvMenu);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //get user quiz results and set them to progress bar
        resultDatabase = Room.databaseBuilder(getApplicationContext(), ResultDatabase.class, "ResultDatabase").build();
        modules= Module.returnModuleArray();
        getAverageQuizResults();
        resultDatabase.close();

        ModuleAdapter.RecyclerViewClickListener listener = new ModuleAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, String title) {
               launchModuleActivity(title);
            }
        };

        mAdapter = new ModuleAdapter(modules, listener);
        mRecyclerView.setAdapter(mAdapter);

    }
    //launches module activity
    private void launchModuleActivity (String title) {
        finish();
        Intent intent = new Intent(this, ModuleActivity.class);
        intent.putExtra(ModuleActivity.INTENT_MESSAGE, title);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        //back button does not go back to quiz activity
    }

    //options menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.sort_alphabetical:
                mAdapter.sort(ModuleAdapter.SORT_METHOD_ALPHABETICAL);
                return true;
            case R.id.sort_desc_avg:
               mAdapter.sort(ModuleAdapter.SORT_METHOD_DESC_AVG);
                return true;
            case R.id.sort_asc_avg:
                mAdapter.sort(ModuleAdapter.SORT_METHOD_ASC_AVG);
                return true;
            case R.id.menu_home:
                finish();
                Intent menuIntent = new Intent(this, MenuActivity.class);
                startActivity(menuIntent);
                return true;
            case R.id.menu_profile:
                finish();
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
        }
        return true;
    }

    //quiz averages for progress bar
    public void getAverageQuizResults () {

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final int[] rawData = new int[3];
                try {
                    rawData[0] = resultDatabase.quizResultDao().getAveragePoliticsQuizResult(uid);
                    rawData[1] = resultDatabase.quizResultDao().getAveragePandemicQuizResult(uid);
                    rawData[2] = resultDatabase.quizResultDao().getAverageAustraliaQuizResult(uid);
                } catch (NullPointerException e) {
                    rawData[0] = 0;
                    rawData[0] = 0;
                    rawData[0] = 0;
                }
                modules.get(0).setProgressBar(rawData[0]);
                modules.get(1).setProgressBar(rawData[1]);
                modules.get(2).setProgressBar(rawData[2]);
            }
        });
    }



}