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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class NutritionFragment extends Fragment
{
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    String uid = user.getUid();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nutrition, null);
    }

    public void submit_Cardio(View view)
    {
        EditText cardio_minutes_entry = (EditText)getView().findViewById(R.id.enterCardioMinutes);
        Button submit = (Button)getView().findViewById(R.id.submit_button);
        if(cardio_minutes_entry.getText().toString() != "") {
            try {
                int cardio_minutes = Integer.parseInt(cardio_minutes_entry.getText().toString());
                //throw DB
                rootRef.child("Users/" + uid + "/Dates").child(getCurrentDate()).child("cardio_minutes").setValue(cardio_minutes);
            } catch (Exception e) {
                // This will toast to the screen "You done fucked up!"
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
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