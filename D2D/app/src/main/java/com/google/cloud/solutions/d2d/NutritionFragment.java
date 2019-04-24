package com.google.cloud.solutions.d2d;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class NutritionFragment extends Fragment
{
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String uid = user.getUid();

    Button submitNutrition;

    EditText calories_consumed_entry;
    EditText carbs_consumed_entry;
    EditText sugars_consumed_entry;
    EditText proteins_consumed_entry;
    EditText fats_consumed_entry;
    EditText fiber_consumed_entry;
    EditText water_consumed_entry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nutrition, null);

        submitNutrition = v.findViewById(R.id.submit_data);

        calories_consumed_entry = v.findViewById(R.id.calories_consumed);
        carbs_consumed_entry = v.findViewById(R.id.carbs_consumed);
        sugars_consumed_entry = v.findViewById(R.id.sugars_consumed);
        proteins_consumed_entry = v.findViewById(R.id.proteins_consumed);
        fats_consumed_entry = v.findViewById(R.id.fats_consumed);
        fiber_consumed_entry = v.findViewById(R.id.fiber_consumed);
        water_consumed_entry = v.findViewById(R.id.water_consumed);

        submitNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_nutrition_data(v);
            }
        });


        return v;
    }

    public void submit_nutrition_data(View view)
    {

        int calories_consumed, carbs_consumed, sugar_consumed, proteins_consumed, fats_consumed, fiber_consumed, water_consumed;

        if(calories_consumed_entry.getText().toString() != ""){
            try{
                calories_consumed = Integer.parseInt(calories_consumed_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("calories_consumed").setValue(calories_consumed);
            }
            catch(Exception e)
            {
                // This will toast to the screen "You done fucked up!"
                // Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
                // return;
            }
        }
        if(carbs_consumed_entry.getText().toString() != ""){
            try{
                carbs_consumed = Integer.parseInt(carbs_consumed_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("carbs_consumed").setValue(carbs_consumed);
            }
            catch(Exception e)
            {
                // This will toast to the screen "You done fucked up!"
                // Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
                // return;
            }
        }
        if(sugars_consumed_entry.getText().toString() != ""){
            try{
                sugar_consumed = Integer.parseInt(sugars_consumed_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("sugar_consumed").setValue(sugar_consumed);
            }
            catch(Exception e)
            {
                // This will toast to the screen "You done fucked up!"
                // Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
                // return;
            }
        }
        if(proteins_consumed_entry.getText().toString() != ""){
            try{
                proteins_consumed = Integer.parseInt(proteins_consumed_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("proteins_consumed").setValue(proteins_consumed);
            }
            catch(Exception e)
            {
                // This will toast to the screen "You done fucked up!"
                // Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
                // return;
            }
        }
        if(fats_consumed_entry.getText().toString() != ""){
            try{
                fats_consumed = Integer.parseInt(fats_consumed_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("fats_consumed").setValue(fats_consumed);
            }
            catch(Exception e)
            {
                // This will toast to the screen "You done fucked up!"
                // Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
                // return;
            }
        }
        if(fiber_consumed_entry.getText().toString() != ""){
            try{
                fiber_consumed = Integer.parseInt(fiber_consumed_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("fiber_consumed").setValue(fiber_consumed);
            }
            catch(Exception e)
            {
                // This will toast to the screen "You done fucked up!"
                // Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
                // return;
            }
        }
        if(water_consumed_entry.getText().toString() != ""){
            try{
                water_consumed = Integer.parseInt(water_consumed_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("water_consumed").setValue(water_consumed);
            }
            catch(Exception e)
            {
                // This will toast to the screen "You done fucked up!"
                // Toast.makeText(getApplicationContext(), "Incorrect format", Toast.LENGTH_SHORT).show();
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