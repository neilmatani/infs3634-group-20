package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editName, editEmail, editPassword, editConfirmPassword;
    Button confirmDetails;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.confirmPassword);

        mAuth = FirebaseAuth.getInstance();
        this.setTitle(getResources().getString(R.string.app_name));

       findViewById(R.id.confirmDetails).setOnClickListener(this);
       confirmDetails = findViewById(R.id.confirmDetails);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirmDetails:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String confirmPassword = editConfirmPassword.getText().toString().trim();

        //ensure fields are filled
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            editName.setError("Details are missing!");
            return;
        }

        //ensure email is in appropriate format
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please enter a valid email!");
            editEmail.requestFocus();
            return;
        }

        //ensure passwords match
        if(!password.equals(confirmPassword)){
            editConfirmPassword.setError("Password do not match!");
            return;

        }

        //ensure password is larger than 6 figures
        if(password.length() < 6){
            editPassword.setError("Minimum length of password should be 6 characters!");
            editPassword.requestFocus();
            return;
        }

        //Firebase register user with email and password function
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                    user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                            }
                        }
                    });
                    Toast.makeText(SignupActivity.this, "User Registration Successful!", Toast.LENGTH_SHORT).show();
                    confirmDetails.setBackgroundColor(Color.GREEN);
                    confirmDetails.setText("Registration Successful!");
                    finish();
                    Intent intent = new Intent(SignupActivity.this, MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else{
                    Log.w("SignupActivity.java", "createUserWithEmailAndPassword:failure", task.getException());
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(SignupActivity.this, "Email is already registered!", Toast.LENGTH_SHORT).show();
                        confirmDetails.setBackgroundColor(Color.RED);
                        confirmDetails.setText("User email in use!");
                    }else{
                        Toast.makeText(SignupActivity.this, "User Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                        confirmDetails.setBackgroundColor(Color.RED);
                        confirmDetails.setText("Please Try Again!");
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        //back button does not go back to quiz activity
    }
}