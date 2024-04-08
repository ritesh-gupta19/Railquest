package com.example.railquest;

import android.content.Intent;
import android.os.Bundle;

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
    ArrayList<String> travellerName_age_gender;
    ArrayList<String> travellerBerth;

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
        txtBookingTrainName = findViewById(R.id.txtBookedTrainName);
        txtBookingTrainNumber = findViewById(R.id.txtBookedTrainNumber);
        txtBookingTier = findViewById(R.id.txtBookedTier);
        txtBookingSeatsRemaining = findViewById(R.id.txtBookingSeatsRemaining);
        txtBookingQuota = findViewById(R.id.txtBookingQuota);
        txtBookingStartTimeDate = findViewById(R.id.txtBookedStartTimeDate);
        txtBookingDestinationTimeDate = findViewById(R.id.txtBookedDestinationTimeDate);
        txtBookingTravelDuration = findViewById(R.id.txtBookedTravelDuration);
        txtBookingStartStation = findViewById(R.id.txtBookedStartStation);
        txtBookingDestinationStation = findViewById(R.id.txtBookedDestinationStation);
        recViewTravellerList = findViewById(R.id.recViewTravellerList);

        travellerName_age_gender = new ArrayList<>();
        travellerBerth = new ArrayList<>();

        // Set onClick listeners for buttons
        btnAddNewTraveller.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewTravellerDetailsAdd.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_TRAVELLER); // Start activity for result
        });

        btnProceedToPayment.setOnClickListener(v -> {
            // Validate email and phone number
            if (validateEmail() && validatePhoneNumber() && !travellerName_age_gender.isEmpty()) {
                // Add your logic for proceeding to payment here
                Toast.makeText(Booking_Page_User_Details_Trip.this, "Proceeding to payment...", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, PaymentActivity.class);

                // add string extras
                intent.putExtra("trainName", getIntent().getStringExtra("trainName"));
                intent.putExtra("trainNumber", getIntent().getStringExtra("trainNumber"));
                intent.putExtra("startTimeDate", getIntent().getStringExtra("startTimeDate"));
                intent.putExtra("endTimeDate", getIntent().getStringExtra("endTimeDate"));
                intent.putExtra("timeDuration", getIntent().getStringExtra("timeDuration"));
                intent.putExtra("boardingStation", getIntent().getStringExtra("boardingStation"));
                intent.putExtra("destinationStation", getIntent().getStringExtra("destinationStation"));
                intent.putExtra("bookingTier", getIntent().getStringExtra("bookingTier"));
                intent.putExtra("bookingQuota", getIntent().getStringExtra("bookingQuota"));
                intent.putExtra("seatPrice", getIntent().getStringExtra("seatPrice"));
                intent.putExtra("travellerName_age_gender", travellerName_age_gender);
                intent.putExtra("travellerBerth", travellerBerth);

                startActivity(intent);
            } else if (travellerName_age_gender.isEmpty()) {
                Toast.makeText(this, "Enter a Traveler", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please fill all Details", Toast.LENGTH_SHORT).show();
            }
        });

        // get data from previous intent

        txtBookingTrainName.setText(getIntent().getStringExtra("trainName"));
        txtBookingTrainNumber.setText(getIntent().getStringExtra("trainNumber"));
        txtBookingTier.setText(getIntent().getStringExtra("bookingTier"));
        txtBookingSeatsRemaining.setText("Available " + getIntent().getStringExtra("availableSeats"));
        txtBookingQuota.setText(getIntent().getStringExtra("bookingQuota"));
        txtBookingStartTimeDate.setText(getIntent().getStringExtra("startTimeDate"));
        txtBookingDestinationTimeDate.setText(getIntent().getStringExtra("endTimeDate"));
        txtBookingTravelDuration.setText(getIntent().getStringExtra("timeDuration"));
        txtBookingStartStation.setText(getIntent().getStringExtra("boardingStation"));
        txtBookingDestinationStation.setText(getIntent().getStringExtra("destinationStation"));
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

                travellerName_age_gender.add(passengerName + ", " + passengerAge + ", (" + passengerGender + ")");
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
//                Toast.makeText(this, travelerDetails, Toast.LENGTH_LONG).show();
                // Update TextView or any other UI component with travelerDetails
            }
        }
    }

    private void updateTravellerDetails() {
        TravellerListCustomAdapter travellerListCustomAdapter = new TravellerListCustomAdapter(this, travellerName_age_gender, travellerBerth);
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
