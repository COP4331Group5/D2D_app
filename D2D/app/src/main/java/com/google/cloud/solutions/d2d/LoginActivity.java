package com.google.cloud.solutions.d2d;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.*;

public class LoginActivity extends AppCompatActivity
{

    private static final int MY_REQUEST_CODE = 777; //for sign in
    List<AuthUI.IdpConfig> providers;   //sign in options

    Button btn_sign_out;
    Button btn_send_new_info;
    EditText userNameField;
    EditText userBdayField;
    EditText userAgeField;
    EditText userWeightField;
    Button getButton;

    User currentUser;

    //Firebase
    //private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String userID;
    private DatabaseReference rootRef;

    public void submitNewUser(View view) {
        String name = userNameField.getText().toString();
        int age = Integer.parseInt(userAgeField.getText().toString());
        String bday = userBdayField.getText().toString();
        double weight = Double.parseDouble(userWeightField.getText().toString());

        currentUser = new User(name, bday, age, weight);
        rootRef.child("Users").child(userID).setValue(currentUser);

        launchMain();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_sign_out = (Button)findViewById(R.id.btn_sign_out);
        btn_send_new_info = (Button)findViewById(R.id.btn_send_new_info);

        getButton = (Button)findViewById(R.id.getButton);

        userNameField = (EditText)findViewById(R.id.enterNameEdit);
        userAgeField = (EditText)findViewById(R.id.enterAgeEdit);
        userBdayField = (EditText)findViewById(R.id.enterBdayEdit);
        userWeightField = (EditText)findViewById(R.id.enterWeightEdit);

        //Sign In
        providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                //new AuthUI.IdpConfig.GitHubBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build()
        );
        showSignInOptions();

        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(LoginActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_sign_out.setEnabled(true);
                                showSignInOptions();
                                userNameField.setText("");
                                userAgeField.setText("");
                                userBdayField.setText("");
                                userWeightField.setText("");

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this,""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.MyTheme)
                        .build(), MY_REQUEST_CODE
        );
    }

    //make Google sign-in
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK) {
                //Get User
                mAuth = FirebaseAuth.getInstance();
                user = mAuth.getCurrentUser();
                userID = user.getUid();
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("Users").hasChild(userID)) {
                            launchMain();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
            else {
                Toast.makeText(this, "" + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void launchMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rootRef = FirebaseDatabase.getInstance().getReference();

        //mAuth.addAuthStateListener(mAuthListener);


        /*
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
        });*/

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

    public void update_name()
    {
        currentUser.name = userNameField.getText().toString();
        rootRef.child("Users").child(userID).child("name").setValue(currentUser.name);

    }

    public void update_birthday()
    {
        currentUser.bday = userNameField.getText().toString();
        rootRef.child("Users").child(userID).child("bday").setValue(currentUser.bday);

    }

    public void update_age()
    {
        currentUser.age = Integer.parseInt(userAgeField.getText().toString());
        rootRef.child("Users").child(userID).child("age").setValue(currentUser.age);
    }

    public void update_weight()
    {
        currentUser.weight = Integer.parseInt(userWeightField.getText().toString());
        rootRef.child("Users").child(userID).child("weight").setValue(currentUser.weight);

    }
}