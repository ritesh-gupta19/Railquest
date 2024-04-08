package com.example.railquest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Booked_Details_Complete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_details_complete);

        // Initialize views
        TextView txtBookedTrainName = findViewById(R.id.txtBookedTrainName);
        TextView txtBookedTrainNumber = findViewById(R.id.txtBookedTrainNumber);
        TextView txtBookedTier = findViewById(R.id.txtBookedTier);
        TextView txtBookedStartTimeDate = findViewById(R.id.txtBookedStartTimeDate);
        TextView txtBookedDestinationTimeDate = findViewById(R.id.txtBookedDestinationTimeDate);
        TextView txtBookedTravelDuration = findViewById(R.id.txtBookedTravelDuration);
        TextView txtBookedStartStation = findViewById(R.id.txtBookedStartStation);
        TextView txtBookedDestinationStation = findViewById(R.id.txtBookedDestinationStation);
        TextView txtBookedPNRNumber = findViewById(R.id.txtBookedPNRNumber);
        TextView txtBookedBaseFair = findViewById(R.id.txtBookedBaseFair);
        TextView txtBookedAdditionalFare = findViewById(R.id.txtBookedAdditionalFare);
        TextView txtBookedTotalFair = findViewById(R.id.txtBookedTotalFair);
        RecyclerView recViewTravellerList = findViewById(R.id.recViewTravellerList);

        Ticket ticket = (Ticket) getIntent().getSerializableExtra("ticket");

        // Set dummy data (replace with actual data)
        assert ticket != null;
        txtBookedTrainName.setText(ticket.getTrainName());
        txtBookedTrainNumber.setText(ticket.getTrainNumber());
        txtBookedTier.setText(ticket.getBookingTier());
        txtBookedStartTimeDate.setText(ticket.getStartTimeDate());
        txtBookedDestinationTimeDate.setText(ticket.getEndTimeDate());
        txtBookedTravelDuration.setText(ticket.getTimeDuration());
        txtBookedStartStation.setText(ticket.getBoardingStationName());
        txtBookedDestinationStation.setText(ticket.getDestinationStationName());
        txtBookedPNRNumber.setText(ticket.getPnr());
        txtBookedBaseFair.setText(ticket.getSeatPrice());
        txtBookedAdditionalFare.setText("0");
        txtBookedTotalFair.setText(ticket.getSeatPrice());

        List<String> travellerName= ticket.getTravellerName_age_gender();
        List<String> travellerGenderAge= ticket.getTravellerBerth();
        List<String> travellerBookedStatus= new ArrayList<>();
        List<String> travellerCurrentStatus= new ArrayList<>();

        List<String> travellerBerth = ticket.getTravellerBerth();

        for (int i=0; i< travellerName.size(); i++) {
            String bookedStatus = "CNF" + "/";
            switch (txtBookedTier.getText().toString()) {
                case "SL":
                    bookedStatus += "S1";
                    break;
                case "3A":
                    bookedStatus += "B1";
                    break;
                case "2A":
                    bookedStatus += "A1";
                    break;
                case "1A":
                    bookedStatus += "HS1";
                    break;
                default:
                    bookedStatus += "B2";
                    break;
            }
            bookedStatus += "/" + (i+1) + "/";

            String currentBerth = travellerBerth.get(i);

            if (currentBerth.equals("Lower")) {
                bookedStatus += "LB";
            } else if (currentBerth.equals("Middle")) {
                bookedStatus += "MB";
            } else if (currentBerth.equals("Upper")) {
                bookedStatus += "UB";
            } else if (currentBerth.equals("Side Lower")) {
                bookedStatus += "SLB";
            } else if (currentBerth.equals("Side Upper")) {
                bookedStatus += "SUB";
            } else {
                bookedStatus += "MB";
            }
            travellerBookedStatus.add(bookedStatus);
            travellerCurrentStatus.add(bookedStatus);
        }

        BookedTravellerListCustomAdapter adapter = new BookedTravellerListCustomAdapter(this, travellerName, travellerGenderAge, travellerBookedStatus, travellerCurrentStatus);
        recViewTravellerList.setAdapter(adapter);
        recViewTravellerList.setLayoutManager(new LinearLayoutManager(this));

    }
}
