package com.example.railquest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Booking_Page_User_Details_Trip extends AppCompatActivity {
    private EditText edtTxtEmail, edtTxtPhoneNumber;
    private TextView txtBookingTrainName, txtBookingTrainNumber, txtBookingTier,
            txtBookingSeatsRemaining, txtBookingQuota, txtBookingStartTimeDate,
            txtBookingDestinationTimeDate, txtBookingTravelDuration, txtBookingStartStation,
            txtBookingDestinationStation;
    private RecyclerView recViewTravellerList;
    List<String> travellerName_age;
    List<String> travellerBerth;

    // Define a constant for request code
    private static final int REQUEST_CODE_ADD_TRAVELLER = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page_user_details_trip);

        // Find views by their IDs
        Button btnAddNewTraveller = findViewById(R.id.btnAddNewTraveller);
        Button btnProceedToPayment = findViewById(R.id.btnProceedToPayment);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPhoneNumber = findViewById(R.id.edtTxtPhoneNumber);
        txtBookingTrainName = findViewById(R.id.txtBookingTrainName);
        txtBookingTrainNumber = findViewById(R.id.txtBookingTrainNumber);
        txtBookingTier = findViewById(R.id.txtBookingTier);
        txtBookingSeatsRemaining = findViewById(R.id.txtBookingSeatsRemaining);
        txtBookingQuota = findViewById(R.id.txtBookingQuota);
        txtBookingStartTimeDate = findViewById(R.id.txtBookingStartTimeDate);
        txtBookingDestinationTimeDate = findViewById(R.id.txtBookingDestinationTimeDate);
        txtBookingTravelDuration = findViewById(R.id.txtBookingTravelDuration);
        txtBookingStartStation = findViewById(R.id.txtBookingStartStation);
        txtBookingDestinationStation = findViewById(R.id.txtBookingDestinationStation);
        recViewTravellerList = findViewById(R.id.recViewTravellerList);

        travellerName_age = new ArrayList<>();
        travellerBerth = new ArrayList<>();

        // Set onClick listeners for buttons
        btnAddNewTraveller.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewTravellerDetailsAdd.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_TRAVELLER); // Start activity for result
        });

        btnProceedToPayment.setOnClickListener(v -> {
            // Validate email and phone number
            if (validateEmail() && validatePhoneNumber()) {
                // Add your logic for proceeding to payment here
                Toast.makeText(Booking_Page_User_Details_Trip.this, "Proceeding to payment...", Toast.LENGTH_SHORT).show();
            }
        });

        // Set sample data for the first card (you can replace it with dynamic data)
        txtBookingTrainName.setText("Kyq Gtnr Express");
        txtBookingTrainNumber.setText("15077");
        txtBookingTier.setText("3 Tier AC");
        txtBookingSeatsRemaining.setText("Available 44");
        txtBookingQuota.setText("General Quota");
        txtBookingStartTimeDate.setText("8:00 PM, 30 Apr");
        txtBookingDestinationTimeDate.setText("9:13 PM, 1 May");
        txtBookingTravelDuration.setText("25h 13m");
        txtBookingStartStation.setText("Kamakhya (KYQ)");
        txtBookingDestinationStation.setText("Basti (BST)");
    }

    // Handle the result from the child activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_TRAVELLER && resultCode == RESULT_OK) {
            if (data != null) {
                // Get the data from the intent
                String passengerName = data.getStringExtra("passengerName");
                int passengerAge = data.getIntExtra("passengerAge", 0);
                String passengerGender = data.getStringExtra("passengerGender");
                String berthPreference = data.getStringExtra("berthPreference");
                String nationality = data.getStringExtra("nationality");

                travellerName_age.add(passengerName + ", " + passengerAge + ", (" + passengerGender + ")");
                travellerBerth.add(berthPreference);

                updateTravellerDetails();

                // Display the data in the UI or update the corresponding adapter
                // For example, you can update a TextView with the new user details
                String travelerDetails = "Name: " + passengerName +
                        "\nAge: " + passengerAge +
                        "\nGender: " + passengerGender +
                        "\nBerth Preference: " + berthPreference +
                        "\nNationality: " + nationality;
//                Log.d("now", "onActivityResult: "+ travelerDetails);
                Toast.makeText(this, travelerDetails, Toast.LENGTH_LONG).show();
                // Update TextView or any other UI component with travelerDetails
            }
        }
    }

    private void updateTravellerDetails() {
        TravellerListCustomAdapter travellerListCustomAdapter = new TravellerListCustomAdapter(this, travellerName_age, travellerBerth);
        recViewTravellerList.setAdapter(travellerListCustomAdapter);
        recViewTravellerList.setLayoutManager(new LinearLayoutManager(this));
    }

    // Method to validate email input
    private boolean validateEmail() {
        String email = edtTxtEmail.getText().toString().trim();

        if (email.isEmpty()) {
            edtTxtEmail.setError("Email is required");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtTxtEmail.setError("Please enter a valid email address");
            return false;
        } else {
            edtTxtEmail.setError(null);
            return true;
        }
    }

    // Method to validate phone number input
    private boolean validatePhoneNumber() {
        String phoneNumber = edtTxtPhoneNumber.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            edtTxtPhoneNumber.setError("Phone number is required");
            return false;
        } else if (phoneNumber.length() != 10) {
            edtTxtPhoneNumber.setError("Please enter a valid phone number");
            return false;
        } else {
            edtTxtPhoneNumber.setError(null);
            return true;
        }
    }
}
