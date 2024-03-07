package com.example.railquest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private EditText mobileNumber;
    private EditText emailId;
    private EditText name;
    private EditText password;
    private EditText confirmPassword;
    private EditText firstName;
    private EditText dateOfBirth;
    private EditText resedentialAddress;
    private EditText country;
    private EditText pinCode;
    private EditText city;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mobileNumber = findViewById(R.id.editTextPhone);
        emailId = findViewById(R.id.editTextTextEmailAddress);
        name = findViewById(R.id.editTextText2);
        password = findViewById(R.id.editTextTextPassword2);
        confirmPassword = findViewById(R.id.editTextTextPassword4);
        firstName = findViewById(R.id.editTextText3);
        dateOfBirth = findViewById(R.id.editTextDate2);
        resedentialAddress = findViewById(R.id.editTextText7);
        country = findViewById(R.id.editTextText8);
        pinCode = findViewById(R.id.editTextNumber);
        city = findViewById(R.id.editTextText9);
        button = findViewById(R.id.button3);

        setFieldListeners();
    }

    private void setFieldListeners() {
        mobileNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateMobileNumber();
                }
            }
        });

        emailId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateEmail();
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validatePassword();
                }
            }
        });

        confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateConfirmPassword();
                }
            }
        });

        dateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateDOB();
                }
            }
        });

        pinCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validatePinCode();
                }
            }
        });
    }

    private void validateMobileNumber() {
        String mobile_number = mobileNumber.getText().toString();
        Pattern pattern_mobile = Pattern.compile("^(\\+?91|0)?[6789]\\d{9}$");
        Matcher matcher_mobile = pattern_mobile.matcher(mobile_number);
        if (!matcher_mobile.matches()) {
            mobileNumber.setError("Invalid mobile number");
        }
    }

    private void validateEmail() {
        String email_id = emailId.getText().toString();
        Pattern pattern_email = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher_email = pattern_email.matcher(email_id);
        if (!matcher_email.matches()) {
            emailId.setError("Invalid email address");
        }
    }

    private void validatePassword() {
        String pass_word = password.getText().toString();
        Pattern pattern_password = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher_password = pattern_password.matcher(pass_word);
        if (!matcher_password.matches()) {
            password.setError("The password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character");
        }
    }

    private void validateConfirmPassword() {
        String confirm_password = confirmPassword.getText().toString();
        String pass_word = password.getText().toString();
        if (!confirm_password.equals(pass_word)) {
            confirmPassword.setError("Passwords do not match");
        }
    }

    private void validateDOB() {
        String dobString = dateOfBirth.getText().toString();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // This will not allow 31st of February, for instance

        try {
            Date dob = sdf.parse(dobString);
            assert dob != null;
            if (dob.after(new Date())) {
                dateOfBirth.setError("Date of birth cannot be in the future");
            }
        } catch (ParseException e) {
            dateOfBirth.setError("Invalid date format. Please enter date in dd/MM/yyyy format");
        }
    }

    private void validatePinCode() {
        String pinCodeString = pinCode.getText().toString();
        Pattern pattern_pinCode = Pattern.compile("^\\d{6}$");
        Matcher matcher_pinCode = pattern_pinCode.matcher(pinCodeString);
        if (!matcher_pinCode.matches()) {
            pinCode.setError("Invalid PIN code. PIN code must be 6 digits.");
        }
    }

    public void onClickSubmit(View v) {
        // Implement the submit logic here...

        // Redirect to Sign In page
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}
