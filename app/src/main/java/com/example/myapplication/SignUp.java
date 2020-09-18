package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dashboard.DashboardFirstActivity;
import com.example.myapplication.userdata.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regRollNumber, regEmail, regPhoneNumber, regPassword;
    Button regButton, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.full_name);
        regRollNumber = findViewById(R.id.roll_number);
        regEmail = findViewById(R.id.e_mail);
        regPhoneNumber = findViewById(R.id.phone_number);
        regPassword = findViewById(R.id.password_box);
        regButton = findViewById(R.id.signup_btn);
        regToLoginBtn = findViewById(R.id.login_btn);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("user");
                if (!validateName() | !validateRollNumber() | !validateEmail() | !validatePhoneNumber() | !validatePassword()) {
                    return;
                }
                String name = regName.getEditText().getText().toString();
                String rollNumber = regRollNumber.getEditText().getText().toString();
                String eMail = regEmail.getEditText().getText().toString();
                String phoneNumber = regPhoneNumber.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                User user = new User(name, rollNumber, eMail, phoneNumber, password);
                reference.child(rollNumber).setValue(user);

                Intent intent = new Intent(getApplicationContext(), DashboardFirstActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }


    private Boolean validateName() {
        String val1 = regName.getEditText().getText().toString().trim();

        if (val1.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateRollNumber() {
        String val2 = regRollNumber.getEditText().getText().toString();

        if(val2.length()!=9) {
            regRollNumber.setError("Invalid Roll Number");
            return false;
        }
        else {
            regRollNumber.setError(null);
            regRollNumber.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val3 = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[gla]+\\.+[a][c]+\\.+[i][n]+";

        if (val3.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else if(!val3.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        }
        else {
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNumber() {
        String val4 = regPhoneNumber.getEditText().getText().toString();
        String mobileVal = "^(?:(?:\\+|0{0,2})91(\\s*[\\ -]\\s*)?|[0]?)?[6789]\\d{9}|(\\d[ -]?){10}\\d$";

        if (val4.isEmpty()) {
            regPhoneNumber.setError("Field cannot be empty");
            return false;
        }
        else if(!val4.matches(mobileVal)) {
            regPhoneNumber.setError("Wrong Phone Number ");
            return false;
        }
        else {
            regPhoneNumber.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val5 = regPassword.getEditText().getText().toString();
        String passwordVal = "^" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{4,}" + "$";

        if (val5.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!val5.matches(passwordVal)) {
            regPassword.setError("Password is too weak ");
            return false;
        }
        else {
            regPassword.setError(null);
            return true;
        }

    }

    public void loginUser(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


}