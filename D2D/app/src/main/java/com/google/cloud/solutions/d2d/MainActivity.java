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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;
    Button getButton;
    Button sendButton;
    EditText userNameField;
    EditText userBdayField;
    EditText userAgeField;

    // Database instances used to get to specific fields of the database
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userRef = rootRef.child("Users").child("Adam").child("Name");
    private DatabaseReference bdayRef = rootRef.child("Users").child("Adam").child("Birthday");
//    private DatabaseReference planRef = rootRef.child("Users").child("Adam").child("nutritionPlan").child("0").child("0");

    private void writeNewUser(String userID, String name, int age, String bday) {
        MainActivity.User user = new MainActivity.User(name, age, bday);

        rootRef.child("Users").child(userID).setValue(user);
    }

    public void submitNewUser(View view) {
        String userName = userNameField.getText().toString();
        int age = Integer.parseInt(userAgeField.getText().toString());
        String bday = userBdayField.getText().toString();

        writeNewUser("888", userName, age, bday);
    }

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

    public static class User {
        public String Name;
        public int Age;
        public String Birthday;

        public User()
        {

        }

        public User(String name, int age, String bday) {
            this.Name = name;
            this.Age = age;
            this.Birthday = bday;
        }
    }

}
