package com.google.cloud.solutions.d2d;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String uid = user.getUid();
    // Database instances used to get to specific fields of the database
   // private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());
    }

    private boolean loadFragment(Fragment fragment)
    {
        if(fragment != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        Fragment fragment = null;

        switch (menuItem.getItemId())
        {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_exercise:
                fragment = new ExerciseFragment();
                break;
            case R.id.navigation_nutrition:
                fragment = new NutritionFragment();
                break;
            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                break;
        }

        return loadFragment(fragment);
    }

    public void submit_Cardio(View view)
    {
        EditText cardio_minutes_entry = (EditText) findViewById(R.id.cardio_minutes_entry);

        if(cardio_minutes_entry.getText().toString() != "") {
            try {
                int cardio_minutes = Integer.parseInt(cardio_minutes_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("cardio_minutes").setValue(cardio_minutes);
            } catch (Exception e) {
                // This will toast to the screen "You done fucked up!"
                Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
                // return;
            }
        }
    }

    public static String getCurrentDate() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);

        return month + "_" + day + "_" + year;
    }
}
