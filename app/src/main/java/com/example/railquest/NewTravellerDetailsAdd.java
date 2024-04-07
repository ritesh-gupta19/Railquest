package com.example.railquest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NewTravellerDetailsAdd extends AppCompatActivity {
    private EditText edtTxtPassengerName, edtTxtPassengerAge;
    private RadioGroup rgPassengerGender;
    private Spinner spinnerBerthPreference, spinnerNationality;
    private Button btnSavePassengerDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_traveller_details_add);

        // Initialize views
        edtTxtPassengerName = findViewById(R.id.edtTxtPassengerName);
        edtTxtPassengerAge = findViewById(R.id.edtTxtPassengerAge);
        rgPassengerGender = findViewById(R.id.rgPassengerGender);
        spinnerBerthPreference = findViewById(R.id.spinnerBerthPreference);
        spinnerNationality = findViewById(R.id.spinnerNationality);
        btnSavePassengerDetails = findViewById(R.id.btnSavePassengerDetails);

        // Set click listener for the save button
        btnSavePassengerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePassengerDetails();
            }
        });
    }

    private void savePassengerDetails() {
        // Get input values
        String passengerName = edtTxtPassengerName.getText().toString().trim();
        String passengerAgeStr = edtTxtPassengerAge.getText().toString().trim();

        // Check if name field is empty
        if (passengerName.isEmpty()) {
            Toast.makeText(this, "Please enter passenger name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if age field is empty
        if (passengerAgeStr.isEmpty()) {
            Toast.makeText(this, "Please enter passenger age", Toast.LENGTH_SHORT).show();
            return;
        }

        int passengerAge;
        try {
            passengerAge = Integer.parseInt(passengerAgeStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedGenderId = rgPassengerGender.getCheckedRadioButtonId();
        RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
        String passengerGender = selectedGenderRadioButton.getText().toString();

        String berthPreference = spinnerBerthPreference.getSelectedItem().toString();
        String nationality = spinnerNationality.getSelectedItem().toString();

        // Perform validation
        if (passengerAge <= 0 || passengerAge > 150) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new intent to return data to the parent activity
        Intent returnIntent = new Intent();
        returnIntent.putExtra("passengerName", passengerName);
        returnIntent.putExtra("passengerAge", passengerAge);
        returnIntent.putExtra("passengerGender", passengerGender);
        returnIntent.putExtra("berthPreference", berthPreference);
        returnIntent.putExtra("nationality", nationality);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
