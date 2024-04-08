package com.example.railquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    private EditText userIdEditText;
    private EditText passwordEditText;
    private CheckedTextView checkedTextView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userIdEditText = findViewById(R.id.editTextText);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        checkedTextView = findViewById(R.id.checkedTextView);
        submitButton = findViewById(R.id.button);

        setFieldListeners();
    }

    private void setFieldListeners() {
        userIdEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateUserID();
                }
            }
        });

        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validatePassword();
                }
            }
        });
    }

    private boolean validateUserID() {
        String userID = userIdEditText.getText().toString();
        if (userID.length() == 0) {
            userIdEditText.setError("Enter User ID");
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        String password = passwordEditText.getText().toString();
        Pattern pattern_password = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher_password = pattern_password.matcher(password);
        if (!matcher_password.matches()) {
            passwordEditText.setError("The password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character");
            return false;
        }
        return true;
    }

    public void onClickSubmit(View v) {
        // Validate all fields
        boolean isValid = true;

        if(!validateUserID()) {
            isValid = false;
        }

        if (!validatePassword()) {
            isValid = false;
        }

        String username = userIdEditText.getText().toString();
        String password = passwordEditText.getText().toString();
//        if (DBHandler.verifyUserCredentials(username, password, this) != 1) return;

        verifyUserDetails(this, username, password);

    }
    
    private void verifyUserDetails(Context context, String username, String password) {
        DBHandler.fetchUsers(new DBHandler.UserFetchListener() {
            @Override
            public void onUsersFetched(List<UserInfo> userInfoList) {
                for (UserInfo userInfo: userInfoList) {
                    if (userInfo.getName().equals(username)) {
                        if (userInfo.getPassword().equals(password)) {
                            Toast.makeText(SignIn.this, "Validated", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, firstEntryPage.class); // Replace NextActivity with the name of your next activity
                            context.startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onError(String errorMessage) {
                // Highlight incorrect fields
                userIdEditText.requestFocus();
                passwordEditText.requestFocus();
                // Show error message or Toast indicating validation errors
                Toast.makeText(context, "Please fill all compulsory fields correctly", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
