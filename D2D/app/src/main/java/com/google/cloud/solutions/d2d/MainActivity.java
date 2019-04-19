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

public class MainActivity extends AppCompatActivity {

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
        //int age = Integer.parseInt(userAgeField.getText().toString());
        //updateUserAge("4cXSk9TdjYMFCZXAvliMKnNHNry1");

//        String refTemp = firebase.database().ref("Users/" + "4cXSk9TdjYMFCZXAvliMKnNHNry1" + "/Age");
//
//        int NEWAGE = 10;
//        refTemp.update ({"Age": NEWAGE });

        rootRef.child("Users").child("4cXSk9TdjYMFCZXAvliMKnNHNry1").child("Dates").child("blah").setValue(69);
    }

    public void submitNewUser(View view) {
        String userName = userNameField.getText().toString();
        int age = Integer.parseInt(userAgeField.getText().toString());
        String bday = userBdayField.getText().toString();

        writeNewUser(user,"4cXSk9TdjYMFCZXAvliMKnNHNry1");
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

        bdayRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                userBdayField.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String text = "Lol reading from the database didn't work";
                userBdayField.setText(text);
            }
        });

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
