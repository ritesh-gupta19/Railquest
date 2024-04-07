package com.example.railquest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ActivityListTrain extends AppCompatActivity {

    private RecyclerView trainDetailsListRecView;
    private ArrayList<TrainDetails> trainDetailsArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_train);

        trainDetailsListRecView = findViewById(R.id.recyclerViewTrainList);
        ArrayList<TrainDetails> trainDet = new ArrayList<>();

        trainDetailsArray = (ArrayList<TrainDetails>) getIntent().getSerializableExtra("trainDetailsArray");

        TrainClassPaletteItem[] trainClassPaletteItems = new TrainClassPaletteItem[1];
        trainClassPaletteItems[0]= new TrainClassPaletteItem("3A", "Rs 1529", "156", "Updated 2 mins ago");


        trainDet.add(new TrainDetails("MUMBAI CSMT - HAZRAT NIZAMUDDIN Rajdhani Express", "22221", "07:05 AM May 1", "08:10 PM May 1", "13hr 05min", "CSMT", "NZM",trainClassPaletteItems));
        trainDet.add(new TrainDetails("DELHI - KOLKATA Rajdhani Express", "12301", "05:00 PM May 1", "07:30 AM May 2", "14hr 30min", "NDLS", "HWH",trainClassPaletteItems));
        trainDet.add(new TrainDetails("BENGALURU - CHENNAI Shatabdi Express", "12007", "06:00 AM May 1", "09:15 AM May 1", "3hr 15min", "SBC", "MAS",trainClassPaletteItems));
        trainDet.add(new TrainDetails("AHMEDABAD - MUMBAI Duronto Express", "12268", "10:30 PM April 30", "06:00 AM May 1", "7hr 30min", "ADI", "CSMT",trainClassPaletteItems));
        trainDet.add(new TrainDetails("CHENNAI - HYDERABAD Garib Rath Express", "12736", "09:45 PM May 1", "06:30 AM May 2", "8hr 45min", "MAS", "HYB",trainClassPaletteItems));
        trainDet.add(new TrainDetails("KOLKATA - MUMBAI Mail Express", "12322", "08:30 AM May 1", "10:00 PM May 1", "13hr 30min", "HWH", "CSMT",trainClassPaletteItems));
        trainDet.add(new TrainDetails("NEW DELHI - JAIPUR Double Decker Express", "12985", "06:00 AM May 1", "10:45 AM May 1", "4hr 45min", "NDLS", "JP",trainClassPaletteItems));
        trainDet.add(new TrainDetails("MUMBAI - KOLKATA Duronto Express", "12262", "11:45 AM May 1", "08:00 AM May 2", "20hr 15min", "CSMT", "HWH",trainClassPaletteItems));
        trainDet.add(new TrainDetails("BENGALURU - MYSURU Shatabdi Express", "12007", "06:00 AM May 1", "09:15 AM May 1", "3hr 15min", "SBC", "MYS",trainClassPaletteItems));
        trainDet.add(new TrainDetails("CHENNAI - TRIVANDRUM Superfast Express", "12623", "08:00 AM May 1", "07:30 PM May 1", "11hr 30min", "MAS", "TVC",trainClassPaletteItems));

        TrainPaletteCustomAdapter adapter = new TrainPaletteCustomAdapter(this, trainDetailsArray);
        trainDetailsListRecView.setAdapter(adapter);
        trainDetailsListRecView.setLayoutManager(new LinearLayoutManager(this));

    }
}
