package com.example.railquest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentActivity extends AppCompatActivity {
    Button btnConfirmPayment;
    TextView txtBookingTrainName, txtBookingTrainNumber, txtBookingTier, txtBookingSeatsRemaining, txtBookingQuota, txtBookingStartTimeDate, txtBookingDestinationTimeDate, txtBookingTravelDuration, txtBookingStartStation, txtBookingDestinationStation, txtBookingPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);
        txtBookingTrainName = findViewById(R.id.txtBookingTrainName);
        txtBookingTrainNumber = findViewById(R.id.txtBookingTrainNumber);
        txtBookingTier = findViewById(R.id.txtBookingTier);
        txtBookingSeatsRemaining = findViewById(R.id.txtBookingSeatsRemaining);
        txtBookingQuota = findViewById(R.id.txtBookingQuota);
        txtBookingStartTimeDate = findViewById(R.id.txtBookingStartTimeDate);
        txtBookingDestinationTimeDate = findViewById(R.id.txtBookingEndTimeDate);
        txtBookingTravelDuration = findViewById(R.id.txtBookingTimeDuration);
        txtBookingStartStation = findViewById(R.id.txtBookingStartStation);
        txtBookingDestinationStation = findViewById(R.id.txtBookingDestinationStation);
        txtBookingPrice = findViewById(R.id.txtBookingPrice);

        btnConfirmPayment.setOnClickListener(view -> confirmPaymentAndBook());

        txtBookingTrainName.setText(getIntent().getStringExtra("trainName"));
        txtBookingTrainNumber.setText(getIntent().getStringExtra("trainNumber"));
        txtBookingTier.setText(getIntent().getStringExtra("bookingTier"));
        txtBookingSeatsRemaining.setText(getIntent().getStringExtra("availableSeats"));
        txtBookingQuota.setText(getIntent().getStringExtra("bookingQuota"));
        txtBookingStartTimeDate.setText(getIntent().getStringExtra("startTimeDate"));
        txtBookingDestinationTimeDate.setText(getIntent().getStringExtra("endTimeDate"));
        txtBookingTravelDuration.setText(getIntent().getStringExtra("timeDuration"));
        txtBookingStartStation.setText(getIntent().getStringExtra("boardingStation"));
        txtBookingDestinationStation.setText(getIntent().getStringExtra("destinationStation"));
        txtBookingPrice.setText(getIntent().getStringExtra("seatPrice"));
    }

    private void confirmPaymentAndBook() {
        // upload details to realtime database
    }
}