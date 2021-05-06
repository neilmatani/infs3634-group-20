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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView viewName, viewEmail;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        this.setTitle(getResources().getString(R.string.app_name));

        viewName = findViewById(R.id.userName);
        viewEmail = findViewById(R.id.userEmail);
        logOut = findViewById(R.id.confirmDetails);

        loadUserInformation();

        //logout function
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this, UserActivity.class));
            }
        });
        

    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            finish();
            Intent intent = new Intent (this, UserActivity.class);
            startActivity(intent);
        }
    }

    //get user details for Firebase Authentication
    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        String displayName = user.getDisplayName();
        String displayEmail = user.getEmail();

        viewName.setText(displayName);
        viewEmail.setText(displayEmail);
    }
}