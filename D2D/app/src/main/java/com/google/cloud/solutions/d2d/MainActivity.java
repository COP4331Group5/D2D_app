package com.google.cloud.solutions.d2d;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

   Button getButton;
   Button sendButton;
   TextView userNameField;
   TextView userBdayField;
   TextView userPlanField;

   public static class User {
       public String name;
       // Age usually won't be above 127 so byte type can help save some space
       public byte age;
       public float bmi;
       // Java has a Date class that can be used to create date variables but I don't know how it
       // would play with Firebase date types so I'm leaving it as a string for now and we can
       // parse through it to convert it to a Java date type
       public String bday;
       public String height;
       public String login;
       public String password;
       public int userID;
       public int weight;
       public int nutPlan;
   }




   private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
   private DatabaseReference userRef = rootRef.child("user");
   private DatabaseReference bdayRef = rootRef.child("user").child("bday");
   private DatabaseReference planRef = rootRef.child("user").child("bday").child("nutPlan");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements
        getButton = (Button)findViewById(R.id.getButton);
        sendButton= (Button)findViewById(R.id.sendButton);
        userNameField = (TextView)findViewById(R.id.userNameField);
        userBdayField = (TextView)findViewById(R.id.userBdayField);
        userPlanField = (TextView)findViewById(R.id.userPlanField);
    }
}