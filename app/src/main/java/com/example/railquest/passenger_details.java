package com.example.railquest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class passenger_details extends AppCompatActivity {

    private ConstraintLayout constraintLayoutPassengerDetails;
    private Button buttonAddPassenger;
    private Button buttonBookTrain;

    private int passengerCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);

        constraintLayoutPassengerDetails = findViewById(R.id.constraintLayoutPassengerDetails);
        buttonAddPassenger = findViewById(R.id.buttonAddPassenger);
        buttonBookTrain = findViewById(R.id.buttonBookTrain);

        // Add passenger fields for the first passenger
        addPassengerFields();

        // Add passenger fields when "Add Passenger" button is clicked
        buttonAddPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPassengerFields();
            }
        });

        // Handle booking when "Book Train" button is clicked
        buttonBookTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookTrain();
            }
        });
    }

    // Method to add passenger fields dynamically
    private void addPassengerFields() {
        // Inflate passenger details layout
        View passengerView = getLayoutInflater().inflate(R.layout.activity_passenger_details, null);

        // Set IDs for views in the passenger layout
        passengerView.findViewById(R.id.textViewPassengerTitle).setId(View.generateViewId());
        passengerView.findViewById(R.id.editTextPassengerName).setId(View.generateViewId());
        passengerView.findViewById(R.id.editTextPassengerAge).setId(View.generateViewId());
        passengerView.findViewById(R.id.spinnerPassengerGender).setId(View.generateViewId());

        // Set constraints for views in the passenger layout
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayoutPassengerDetails);

        // Set constraints for passenger title
        constraintSet.connect(passengerView.findViewById(R.id.textViewPassengerTitle).getId(), ConstraintSet.TOP,
                constraintLayoutPassengerDetails.getId(), ConstraintSet.TOP);
        if (passengerCount > 1) {
            constraintSet.connect(passengerView.findViewById(R.id.textViewPassengerTitle).getId(), ConstraintSet.START,
                    constraintLayoutPassengerDetails.getId(), ConstraintSet.START);
            constraintSet.connect(passengerView.findViewById(R.id.textViewPassengerTitle).getId(), ConstraintSet.END,
                    constraintLayoutPassengerDetails.getId(), ConstraintSet.END);
        }

        // Set constraints for passenger name
        constraintSet.connect(passengerView.findViewById(R.id.editTextPassengerName).getId(), ConstraintSet.TOP,
                passengerView.findViewById(R.id.textViewPassengerTitle).getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(passengerView.findViewById(R.id.editTextPassengerName).getId(), ConstraintSet.START,
                constraintLayoutPassengerDetails.getId(), ConstraintSet.START);
        constraintSet.connect(passengerView.findViewById(R.id.editTextPassengerName).getId(), ConstraintSet.END,
                constraintLayoutPassengerDetails.getId(), ConstraintSet.END);

        // Set constraints for passenger age
        constraintSet.connect(passengerView.findViewById(R.id.editTextPassengerAge).getId(), ConstraintSet.TOP,
                passengerView.findViewById(R.id.editTextPassengerName).getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(passengerView.findViewById(R.id.editTextPassengerAge).getId(), ConstraintSet.START,
                constraintLayoutPassengerDetails.getId(), ConstraintSet.START);
        constraintSet.connect(passengerView.findViewById(R.id.editTextPassengerAge).getId(), ConstraintSet.END,
                constraintLayoutPassengerDetails.getId(), ConstraintSet.END);

        // Set constraints for passenger gender spinner
        constraintSet.connect(passengerView.findViewById(R.id.spinnerPassengerGender).getId(), ConstraintSet.TOP,
                passengerView.findViewById(R.id.editTextPassengerAge).getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(passengerView.findViewById(R.id.spinnerPassengerGender).getId(), ConstraintSet.START,
                constraintLayoutPassengerDetails.getId(), ConstraintSet.START);
        constraintSet.connect(passengerView.findViewById(R.id.spinnerPassengerGender).getId(), ConstraintSet.END,
                constraintLayoutPassengerDetails.getId(), ConstraintSet.END);

        // Apply constraints
        constraintSet.applyTo(constraintLayoutPassengerDetails);

        // Increment passenger count
        passengerCount++;
    }

    // Method to handle booking
    private void bookTrain() {
        // Implement your booking logic here
        // This method should collect passenger details, calculate the price, and initiate the booking process.
    }
}
