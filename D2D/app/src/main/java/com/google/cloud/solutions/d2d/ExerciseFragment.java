package com.google.cloud.solutions.d2d;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExerciseFragment extends Fragment
{
    int selectionNum;
    String selection;
    TextView view1;
    TextView view2;
    TextView view3;
    TextView view4;
    TextView input_cardio_minutes;
    TextView input_distance;
    TextView input_average_heart_rate;
    TextView input_calories_burnt;
    TextView input_sets;
    TextView input_reps;
    TextView input_weight;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    String userID = user.getUid();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View v = inflater.inflate(R.layout.fragment_exercise, null);
        final String [] values = {"Cardio", "Abs", "Biceps", "Calves", "Deltoid", "Forearms", "Gluteus", "Hamstrings", "Infraspinatus", "Lower Back",
                "Middle Back", "Quadriceps", "Side Abs", "Tiabalis Anterior", "Triceps",  "Upper Back"};
        final String [] valuesXML = {"", "abs", "biceps", "calves", "deltoid", "forearms", "gluteus", "hamstrings", "infraspinatus", "lower_back",
                "middle_back", "quadriceps", "side_abs", "tiabalis_anterior", "triceps", "upper_back"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> spinner, View container, int position, long id)
            {
                selectionNum = position;
                selection = values[position];
                view1 = v.findViewById(R.id.textView1);
                view2 = v.findViewById(R.id.textView2);
                view3 = v.findViewById(R.id.textView3);
                view4 = v.findViewById(R.id.textView4);
                if("Cardio".compareTo(selection) == 0)
                {
                    v.findViewById(R.id.input_sets).setVisibility(View.INVISIBLE);
                    v.findViewById(R.id.input_reps).setVisibility(View.INVISIBLE);
                    v.findViewById(R.id.input_weight).setVisibility(View.INVISIBLE);
                    v.findViewById(R.id.input_cardio_minutes).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.input_distance).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.input_calories_burnt).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.input_average_heart_rate).setVisibility(View.VISIBLE);

                    view1.setText("Enter the number of minutes:");
                    view2.setText("Enter the total distance:");
                    view3.setText("Enter the average heart rate:");
                    view4.setText("Enter the number of calories burnt:");
                    view4.setVisibility(View.VISIBLE);
                }
                else
                {
                    v.findViewById(R.id.input_sets).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.input_reps).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.input_weight).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.input_cardio_minutes).setVisibility(View.INVISIBLE);
                    v.findViewById(R.id.input_distance).setVisibility(View.INVISIBLE);
                    v.findViewById(R.id.input_calories_burnt).setVisibility(View.INVISIBLE);
                    v.findViewById(R.id.input_average_heart_rate).setVisibility(View.INVISIBLE);

                    view1.setText("Enter the number of sets:");
                    view2.setText("Enter the number of reps:");
                    view3.setText("Enter the number of sets:");
                    view4.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }
        };

        // Setting ItemClick Handler for Spinner Widget
        spinner.setOnItemSelectedListener(itemSelectedListener);

        Button button = (Button) v.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View x)
            {
                submitData(v, valuesXML);
            }
        });

        return v;
    }
    public static String getCurrentDate()
    {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);

        return month + "_" + day + "_" + year;
    }
    public void submitData(View v, String[] valuesXML)
    {
        if(valuesXML[selectionNum].compareTo("") == 0)
        {
            input_cardio_minutes = (TextView) v.findViewById(R.id.input_cardio_minutes);
            input_distance = (TextView) v.findViewById(R.id.input_distance);
            input_average_heart_rate = (TextView) v.findViewById(R.id.input_average_heart_rate);
            input_calories_burnt = (TextView) v.findViewById(R.id.input_calories_burnt);
            if(input_cardio_minutes.getText().toString().compareTo("") != 0 && input_distance.getText().toString().compareTo("") != 0
                    && input_average_heart_rate.getText().toString().compareTo("") != 0 && input_calories_burnt.getText().toString().compareTo("") != 0)
            {
                try
                {
                    int cardio_minutes = Integer.parseInt(input_cardio_minutes.getText().toString());
                    int distance = Integer.parseInt(input_distance.getText().toString());
                    int average_heart_rate = Integer.parseInt(input_average_heart_rate.getText().toString());
                    int calories_burnt = Integer.parseInt(input_calories_burnt.getText().toString());

                    //throw DB
                    if(cardio_minutes != 0 && distance != 0 && average_heart_rate != 0 && calories_burnt != 0)
                    {
                        rootRef.child("Users/" + userID + "/Dates").child(getCurrentDate()).child("input_cardio_minutes").setValue(cardio_minutes);
                        rootRef.child("Users/" + userID + "/Dates").child(getCurrentDate()).child("input_distance").setValue(distance);
                        rootRef.child("Users/" + userID + "/Dates").child(getCurrentDate()).child("input_average_heart_rate").setValue(average_heart_rate);
                        rootRef.child("Users/" + userID + "/Dates").child(getCurrentDate()).child("input_calories_burnt").setValue(calories_burnt);
                    }
                    else
                        Toast.makeText(getContext(), "What else?", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), "Formatting error", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            input_sets = (TextView) v.findViewById(R.id.input_sets);
            input_reps = (TextView) v.findViewById(R.id.input_reps);
            input_weight = (TextView) v.findViewById(R.id.input_weight);
            if(input_sets.getText().toString().compareTo("") != 0 && input_reps.getText().toString().compareTo("") != 0
                    && input_weight.getText().toString().compareTo("") != 0)
            {
                try
                {
                    int sets = Integer.parseInt(input_sets.getText().toString());
                    int reps = Integer.parseInt(input_reps.getText().toString());
                    int weight = Integer.parseInt(input_weight.getText().toString());

                    //throw DB
                    if(sets != 0 && reps != 0 && weight != 0)
                    {
                        rootRef.child("Users/" + userID + "/Dates").child(getCurrentDate()).child(valuesXML[selectionNum]).setValue("[" + sets + "," + reps + "," + weight + "]");
                    }
                    else
                        Toast.makeText(getContext(), "What else?", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), "Formatting error", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}