package com.example.railquest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityListTrain extends AppCompatActivity {

    private RecyclerView trainDetailsListRecView;
    private ArrayList<TrainDetails> trainDetailsArray;
    ArrayList<TrainDetails> trainDet;
    TrainDetails clickedTrainDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_train);

        trainDetailsListRecView = findViewById(R.id.recyclerViewBookingsList);
        trainDet = new ArrayList<>();
        trainDetailsArray = (ArrayList<TrainDetails>) getIntent().getSerializableExtra("trainDetailsArray");

        TrainClassPaletteItem[] trainClassPaletteItems = new TrainClassPaletteItem[4];
        trainClassPaletteItems[0]= new TrainClassPaletteItem("3A", "Rs 3529", "109", "Updated 2 mins ago");
        trainClassPaletteItems[1]= new TrainClassPaletteItem("2A", "Rs 2425", "56", "Updated 2 mins ago");
        trainClassPaletteItems[2]= new TrainClassPaletteItem("1A", "Rs 1329", "84", "Updated 2 mins ago");
        trainClassPaletteItems[3]= new TrainClassPaletteItem("SL", "Rs 649", "14", "Updated 2 mins ago");


        trainDet.add(new TrainDetails("DELHI - KOLKATA Rajdhani Express", "12301", "05:00 PM May 1", "07:30 AM May 2", "14hr 30min", "NDLS", "HWH",trainClassPaletteItems));
        trainDet.add(new TrainDetails("BENGALURU - CHENNAI Shatabdi Express", "12007", "06:00 AM May 1", "09:15 AM May 1", "3hr 15min", "SBC", "MAS",trainClassPaletteItems));
        trainDet.add(new TrainDetails("AHMEDABAD - MUMBAI Duronto Express", "12268", "10:30 PM April 30", "06:00 AM May 1", "7hr 30min", "ADI", "CSMT",trainClassPaletteItems));
        trainDet.add(new TrainDetails("CHENNAI - HYDERABAD Garib Rath Express", "12736", "09:45 PM May 1", "06:30 AM May 2", "8hr 45min", "MAS", "HYB",trainClassPaletteItems));
        trainDet.add(new TrainDetails("KOLKATA - MUMBAI Mail Express", "12322", "08:30 AM May 1", "10:00 PM May 1", "13hr 30min", "HWH", "CSMT",trainClassPaletteItems));
        trainDet.add(new TrainDetails("NEW DELHI - JAIPUR Double Decker Express", "12985", "06:00 AM May 1", "10:45 AM May 1", "4hr 45min", "NDLS", "JP",trainClassPaletteItems));
        trainDet.add(new TrainDetails("MUMBAI - KOLKATA Duronto Express", "12262", "11:45 AM May 1", "08:00 AM May 2", "20hr 15min", "CSMT", "HWH",trainClassPaletteItems));
        trainDet.add(new TrainDetails("BENGALURU - MYSURU Shatabdi Express", "12007", "06:00 AM May 1", "09:15 AM May 1", "3hr 15min", "SBC", "MYS",trainClassPaletteItems));
        trainDet.add(new TrainDetails("CHENNAI - TRIVANDRUM Superfast Express", "12623", "08:00 AM May 1", "07:30 PM May 1", "11hr 30min", "MAS", "TVC",trainClassPaletteItems));

        TrainPaletteCustomAdapter adapter = new TrainPaletteCustomAdapter(this, trainDet);

        trainDetailsListRecView.setAdapter(adapter);
        trainDetailsListRecView.setLayoutManager(new LinearLayoutManager(this));

    }
}
