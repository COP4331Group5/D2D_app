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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsFragment extends Fragment
{

    Button submitEditUserInformation;
    EditText submitEditName;
    EditText submitEditBday;
    EditText submitEditAge;
    EditText submitEditWeight;
    TextView titlePage;

    public void submitEditUser(View view) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        String name = submitEditName.getText().toString();
        String bday = submitEditBday.getText().toString();

        if(name.compareTo("") != 0 && bday.compareTo("") != 0 && submitEditWeight.getText().toString().compareTo("") != 0 && submitEditAge.getText().toString().compareTo("") != 0 )
        {
            int age = Integer.parseInt(submitEditAge.getText().toString());
            double weight = Double.parseDouble(submitEditWeight.getText().toString());

            try
            {
                if(name.compareTo("") != 0 && bday.compareTo("") != 0) {
                    rootRef.child("Users/" + userID).child("name").setValue(name);
                    rootRef.child("Users/" + userID).child("bday").setValue(age);
                    rootRef.child("Users/" + userID).child("age").setValue(bday);
                    rootRef.child("Users/" + userID).child("weight").setValue(weight);
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getContext(), "Fill in ALL fields", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, null);

        submitEditUserInformation = (Button)v.findViewById(R.id.btn_send_edit_info);

        titlePage = (TextView)v.findViewById(R.id.textView);

        submitEditName = (EditText)v.findViewById(R.id.editName);
        submitEditBday = (EditText)v.findViewById(R.id.editBday);
        submitEditAge = (EditText)v.findViewById(R.id.editAge);
        submitEditWeight = (EditText)v.findViewById(R.id.editWeight);

        submitEditUserInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEditUser(v);
            }
        });

        return v;
    }
}