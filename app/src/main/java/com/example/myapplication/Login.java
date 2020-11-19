package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.dashboard.DashboardFirstActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextInputLayout rollNumber, password;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        rollNumber = findViewById(R.id.roll_number);
        password = findViewById(R.id.password_box);
        loginBtn = findViewById(R.id.loginBtn);

    }

    private Boolean validateRollNumber() {
        String val = rollNumber.getEditText().getText().toString();

        if(val.length()!=9) {
            rollNumber.setError("Invalid Roll Number");
            return false;
        }
        else {
            rollNumber.setError(null);
            rollNumber.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }


    public void loginBtn(View view) {

        if (!validateRollNumber() | !validatePassword()) {
            return;
        }
        else {
            isUser();
        }

    }

    private void isUser() {
        final String userEnteredRollNumber = rollNumber.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUser = reference.orderByChild("rollNumber").equalTo(userEnteredRollNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    rollNumber.setError(null);
                    rollNumber.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredRollNumber).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {
                        rollNumber.setError(null);
                        rollNumber.setErrorEnabled(false);
                        String nameFromDB = snapshot.child(userEnteredPassword).child("name").getValue(String.class);
                        String rollNumberFromDB = snapshot.child(userEnteredPassword).child("rollNumber").getValue(String.class);
                        String phoneNumberFromDB = snapshot.child(userEnteredPassword).child("phoneNumber").getValue(String.class);
                        String eMailFromDB = snapshot.child(userEnteredPassword).child("eMail").getValue(String.class);

                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), DashboardFirstActivity.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("rollNumber",rollNumberFromDB);
                        intent.putExtra("eMail",eMailFromDB);
                        intent.putExtra("phoneNumber",phoneNumberFromDB);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);
                        finish();
                    }
                    else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else {
                    rollNumber.setError("No such roll number exist");
                    rollNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void registerUser(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
        finish();
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(),ForgPassCountryVerify.class));
    }

}