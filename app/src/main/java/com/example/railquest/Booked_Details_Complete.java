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

        // Set dummy data (replace with actual data)
        txtBookedTrainName.setText("Kyq Gtnr Express");
        txtBookedTrainNumber.setText("15077");
        txtBookedTier.setText("3 Tier AC");
        txtBookedStartTimeDate.setText("8:00 PM, 30 Apr");
        txtBookedDestinationTimeDate.setText("9:13 PM, 1 May");
        txtBookedTravelDuration.setText("25h 13m");
        txtBookedStartStation.setText("Kamakhya (KYQ)");
        txtBookedDestinationStation.setText("Basti (BST)");
        txtBookedPNRNumber.setText("6434199000");
        txtBookedBaseFair.setText("Rs 3200");
        txtBookedAdditionalFare.setText("Rs 25");
        txtBookedTotalFair.setText("Rs 3225");

        List<String> travellerName= new ArrayList<>();
        travellerName.add("Vaibhav");
        travellerName.add("Dishant");
        List<String> travellerGenderAge= new ArrayList<>();
        travellerGenderAge.add("21");
        travellerGenderAge.add("22");
        List<String> travellerBookedStatus= new ArrayList<>();
        travellerBookedStatus.add("CNF/A1/B19");
        travellerBookedStatus.add("CNF/A1/B20");
        List<String> travellerCurrentStatus= new ArrayList<>();
        travellerCurrentStatus.add("CNF/A1/B19");
        travellerCurrentStatus.add("CNF/A1/B20");

        BookedTravellerListCustomAdapter adapter = new BookedTravellerListCustomAdapter(this, travellerName, travellerGenderAge, travellerBookedStatus, travellerCurrentStatus);
        recViewTravellerList.setAdapter(adapter);
        recViewTravellerList.setLayoutManager(new LinearLayoutManager(this));

    }
}
