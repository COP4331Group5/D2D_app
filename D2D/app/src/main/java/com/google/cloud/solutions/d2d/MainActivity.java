package com.google.cloud.solutions.d2d;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button getButton;
    Button sendButton;
    EditText userNameField;
    EditText userBdayField;
    EditText userAgeField;

    // Database instances used to get to specific fields of the database
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference userRef = rootRef.child("Users").child("Boii").child("Name");
    private DatabaseReference dateRef = rootRef.child("Users").child("Boii").child("Dates");
//    private DatabaseReference planRef = rootRef.child("Users").child("Adam").child("nutritionPlan").child("0").child("0");

    User user;
    private void createUser(String name, int age, String bday) {
        user = new User(name, age, bday);
    }


    private void writeNewUser(User user, String userID/*String userID, String name, int age, String bday*/) {
        //User user = new User(name, age, bday);
       // Map<String, Object> userValues = user.toMap();

       // Map<String, Object> userUpdates = new HashMap<>();
       // userUpdates.put("/Users/" + userID + "/age/", userValues);

       // rootRef.updateChildren(userUpdates);

        rootRef.child("Users").child(userID).setValue(user);
    }

    public void updateUserAge(String userID) {
        int newAge = Integer.parseInt(userAgeField.getText().toString());
        
        // Check for age validity
        Map<String,Object> farts = user.updateAge(newAge);

        Map<String, Object> fart = new HashMap<>();
        fart.put("/Users/" + userID + "/age", farts);

        rootRef.updateChildren(fart);
    }


    public void press(View view) {
        //int NEWAGE = ;
        //updateUserAge("4cXSk9TdjYMFCZXAvliMKnNHNry1");
        //updateUserAge("Boiii");

        //String refTemp = firebase.database().ref("Users/" + "4cXSk9TdjYMFCZXAvliMKnNHNry1" + "/Age");

        //refTemp.update ({"Age": NEWAGE });

        //rootRef.child("Users").child("Boii").child("Dates");

        String str = "varname";


        //rootRef.child("Users").child("Boiii").child(str).setValue("cool");
        create_new_date();
        //update_name();
        //update_age();
        //update_weight();
        //update_birthday();


    }

    public void update_name()
    {
        rootRef.child("Users").child("Boiii").child("name").setValue((userNameField.getText().toString()));

    }

    public void update_age()
    {
        rootRef.child("Users").child("Boiii").child("age").setValue(Integer.parseInt(userAgeField.getText().toString()));

    }

    public void update_weight()
    {
        rootRef.child("Users").child("Boiii").child("weight").setValue(Integer.parseInt(userBdayField.getText().toString()));

    }


    public void update_birthday()
    {
        rootRef.child("Users").child("Boiii").child("name").setValue((userBdayField.getText().toString()));

    }

    public void update_average_heart_rate()
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("average_heart_rate").setValue(Integer.parseInt(userBdayField.getText().toString()));
    }

    public void update_calories_burnt()
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("calories_burnt").setValue(Integer.parseInt(userBdayField.getText().toString()));
    }

    public void update_cardio_minutes()
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("cardio_minutes").setValue(Integer.parseInt(userBdayField.getText().toString()));
    }

    public void update_distance()
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("distance").setValue(Integer.parseInt(userBdayField.getText().toString()));
    }

    public void update_abs(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("abs").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_biceps(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("biceps").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_calves(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("calves").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_deltoid(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("deltoid").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_forearms(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("forearms").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_gluteus(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("gluteus").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_hamstrings(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("hamstrings").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_infraspinatus(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("infraspinatus").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_lower_back(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("lower_back").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_middle_back(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("middle_back").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_quadriceps(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("quadriceps").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_side_abs(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("side_abs").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_tibalis_anterior(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("tibalis_anterior").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_triceps(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("triceps").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void update_upper_back(View view)
    {
        String weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "";
        int sets = 0;
        int reps = 1;
        int weight = 10;
        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child(weekday).child("upper_back").setValue("["+ sets + "," + reps +"," + weight +"]");
    }

    public void create_new_date()
    {

        rootRef.child("Users/Boiii/Dates").child(getCurrentDate()).child("");
    }

    public String getCurrentDate()
    {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);

        String current_date = month + "_" + day + "_" + year;

        return current_date;
    }





    public void submitNewUser(View view) {
        String userName = userNameField.getText().toString();
        int age = Integer.parseInt(userAgeField.getText().toString());
        String bday = userBdayField.getText().toString();

        writeNewUser(user,"Boi");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements
        getButton = (Button)findViewById(R.id.getButton);
        sendButton= (Button)findViewById(R.id.sendButton);
        userNameField = (EditText)findViewById(R.id.enterNameEdit);
        userAgeField = (EditText)findViewById(R.id.enterAgeEdit);
        userBdayField = (EditText)findViewById(R.id.enterBdayEdit);
    }


    @Override
    protected void onStart() {
        super.onStart();
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                userNameField.setText(text);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String text = "Lol reading from the database didn't work";
                userNameField.setText(text);
            }
        });

//        bdayRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String text = dataSnapshot.getValue(String.class);
//                userBdayField.setText(text);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                String text = "Lol reading from the database didn't work";
//                userBdayField.setText(text);
//            }
//        });

//        planRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String plan = dataSnapshot.getValue(Integer.class).toString();
//                userPlanField.setText(plan);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                String text = "Lol reading from the database did not work";
//                userPlanField.setText(text);
//            }
//        });
    }

    public static class User {
        public String Name;
        public int Age;
        public String Birthday;

        public User() {
            // Default constructor for calls to DataSnapshot
        }


        public User(String name, int age, String bday) {
            this.Name = name;
            this.Age = age;
            this.Birthday = bday;
        }

        @Exclude
        public Map<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("name", Name);
            result.put("age", Age);
            result.put("birthday", Birthday);

            return result;
        }

        @Exclude
        public Map<String, Object> updateAge(int age) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("age", age);

            return result;

        }
    }
}
