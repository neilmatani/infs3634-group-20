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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executors;

public class NotesActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "";
    private NotesDatabase notesDatabase;
    FirebaseAuth mAuth;
    String uid;
    String oldNote;
    Note currentNote;
    TextView tvNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //set up intent
        Intent intent = getIntent();
        String title = intent.getStringExtra(INTENT_MESSAGE);
        notesDatabase = Room.databaseBuilder(getApplicationContext(), NotesDatabase.class, "NotesDatabase").build();

        //get logged in user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();

        //set up GUI here
        setTitle(title + " Notes");
        Button btSave = findViewById(R.id.btSave);
        EditText etNote = findViewById(R.id.etNote);
        etNote.setHint("Type Your New Note Here");
        tvNote = findViewById(R.id.tvNote);

        //retrieve note from DB
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("uid and module" + uid + title);
                    currentNote = notesDatabase.notesDao().getNotes(uid, title);
                    oldNote = currentNote.getNote();
                    System.out.println("There was an old note " + oldNote);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etNote.setText(oldNote, TextView.BufferType.EDITABLE);
                            tvNote.setText(oldNote);
                        }
                    });
                } catch (NullPointerException e) {
                    System.out.println("No old note");
                    currentNote = null;
                    oldNote = "";
                }
            }
        });



        //when button clicked, save note to db
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNote = etNote.getText().toString().trim();
                System.out.println("New note sting is " + newNote);
                //check if user inputted string
                if (newNote.isEmpty()) {
                    Toast.makeText(NotesActivity.this, "Please Enter Note to Save", Toast.LENGTH_SHORT).show();
                //if there is string in note
                } else {
                    Note newNoteObject = new Note(uid, title, newNote);
                    System.out.println("New note is " + newNoteObject.getUser() + newNoteObject.getModule() + newNoteObject.getNote());

                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            //if there was no previous note
                            if (oldNote.equals("")) {
                                System.out.println("Note is empty");
                            } else {
                                System.out.println("There is current note " + currentNote.getUser() + currentNote.getModule() + currentNote.getNote());
                                notesDatabase.notesDao().deleteNote(currentNote);
                                try {
                                    notesDatabase.notesDao().getNotes(currentNote.getUser(), currentNote.getModule());
                                } catch (NullPointerException e) {
                                    System.out.println("No currentNote in database");
                                }
                            }
                            notesDatabase.notesDao().insertNote(newNoteObject);
                            try {
                                notesDatabase.notesDao().getNotes(newNoteObject.getUser(), newNoteObject.getModule());
                                System.out.println("New note from DB is " + newNoteObject.getNote());
                            } catch (NullPointerException e) {
                                System.out.println("No NewNote in database");
                            }
                        }
                    });
                    //update textView
                    tvNote.setText(newNoteObject.getNote());

                    //make toast to update user
                    Toast.makeText(NotesActivity.this, "Note Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void launchModuleActivity(String title) {
        Intent intent = new Intent(this, ModuleActivity.class);
        intent.putExtra(ModuleActivity.INTENT_MESSAGE, title);
        startActivity(intent);
    }

    //set up menu
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);

        return true;
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