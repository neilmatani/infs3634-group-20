//functions solely as title screen
// Menu Activity holds cardview
package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "dd7eb4843b4241f588f4db05924abda6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.app_name));

        //title screen appears for 2 seconds then launches menu activity
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        launchMenuActivity();
    }

    private void launchMenuActivity() {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}