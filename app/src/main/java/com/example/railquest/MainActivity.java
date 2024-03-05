package com.example.railquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText userIdEditText;
    private EditText passwordEditText;
    private CheckedTextView checkedTextView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIdEditText = findViewById(R.id.editTextText);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        checkedTextView = findViewById(R.id.checkedTextView);
        submitButton = findViewById(R.id.button);
    }

    protected void onClickSubmit(View view) {
        String userID = userIdEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean isChecked = checkedTextView.isChecked();

        if (userID.length() == 0) {
            Toast.makeText(this, "Enter User ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() == 0) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isChecked) {
            Toast.makeText(this, "Agree to all Terms and Conditions.", Toast.LENGTH_SHORT).show();
            return;
        }

        // start to login.

    }
}