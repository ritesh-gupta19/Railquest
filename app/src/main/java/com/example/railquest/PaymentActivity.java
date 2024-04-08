package com.example.railquest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Objects;

public class PaymentActivity extends AppCompatActivity {
    Button btnConfirmPayment;
    TextView txtBookingTrainName, txtBookingTrainNumber, txtBookingTier, txtBookingQuota, txtBookingStartTimeDate, txtBookingDestinationTimeDate, txtBookingTravelDuration, txtBookingStartStation, txtBookingDestinationStation, txtBookingPrice;
    List<String> travellerName_age_gender;
    List<String> travellerBerth;

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
        txtBookingQuota.setText(getIntent().getStringExtra("bookingQuota"));
        txtBookingStartTimeDate.setText(getIntent().getStringExtra("startTimeDate"));
        txtBookingDestinationTimeDate.setText(getIntent().getStringExtra("endTimeDate"));
        txtBookingTravelDuration.setText(getIntent().getStringExtra("timeDuration"));
        txtBookingStartStation.setText(getIntent().getStringExtra("boardingStation"));
        txtBookingDestinationStation.setText(getIntent().getStringExtra("destinationStation"));
        travellerName_age_gender = getIntent().getStringArrayListExtra("travellerName_age_gender");
        travellerBerth = getIntent().getStringArrayListExtra("travellerBerth");

        int totalPrice = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("seatPrice")).replaceAll("[^0-9]", "")) * travellerName_age_gender.size();
        txtBookingPrice.setText(String.valueOf(totalPrice));
    }

    public List<String> getTravellerName_age_gender() {
        return travellerName_age_gender;
    }

    public List<String> getTravellerBerth() {
        return travellerBerth;
    }

    private void confirmPaymentAndBook() {
        // upload details to realtime database
        DBHandler.bookTickets(this);

    }
}