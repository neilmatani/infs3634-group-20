package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ModuleActivity extends AppCompatActivity  {
    public static final String INTENT_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        //receive module name
        Intent intent = getIntent();
        String title = intent.getStringExtra(INTENT_MESSAGE);

        //initialise GUI features
        setTitle(title + " Module");
        Module thisModule = new Module();
        ArrayList<Module> moduleArrayList = new ArrayList<Module>();
        moduleArrayList = thisModule.returnModuleArray();

        for (Module module : moduleArrayList) {
            if (module.getTitle().equals(title)) {
                thisModule = module;
            }
        }
        TextView tvModuleTitle = findViewById(R.id.tvTitle);
        TextView tcDescription = findViewById(R.id.tvDescription);
        ImageView ivPhoto = findViewById(R.id.ivPhoto);
        Button btReading = findViewById(R.id.btReading);
        Button btNotes = findViewById(R.id.btNotes);
        Button btQuiz = findViewById(R.id.btQuiz);
        Button btQuizHistory = findViewById(R.id.btQuizHistory);
        tvModuleTitle.setText(title);
        tcDescription.setText(thisModule.getDescription());
        ivPhoto.setImageResource(thisModule.getImage());

        //add click listeners for buttons
        btReading.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchReadingActivity(title);
            }
        });

        btNotes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNotesActivity(title);
            }
        });

        btQuiz.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchQuizActivity(title);
            }
        });

        btQuizHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHistoryActivity(title);
            }
        });

    }
    public void launchHistoryActivity(String title) {
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra(HistoryActivity.INTENT_MESSAGE, title);
        startActivity(intent);
    }
    public void launchReadingActivity (String title) {
        Intent intent = new Intent(this, ReadingActivity.class);
        intent.putExtra(ReadingActivity.INTENT_MESSAGE, title);
        startActivity(intent);
    }

    public void launchNotesActivity (String title) {
        Intent intent = new Intent(this, NotesActivity.class);
        intent.putExtra(NotesActivity.INTENT_MESSAGE, title);
        startActivity(intent);
    }

    public void launchQuizActivity (String title) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.INTENT_MESSAGE, title);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
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