package com.example.railquest;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class search_page extends AppCompatActivity {
    private EditText editTextSourceStation;
    private EditText editTextDestinationStation;
    private EditText editTextDate;
    private OkHttpClient okHttpClient;
    private ArrayList<TrainDetails> trainDetailsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        editTextDate = findViewById(R.id.editTextDate);
        editTextSourceStation = findViewById(R.id.editTextSource);
        editTextDestinationStation = findViewById(R.id.editTextDestination);
        Button searchTrains = findViewById(R.id.buttonSearchTrains);
        View imageViewCalendar = findViewById(R.id.imageViewCalendar);
        okHttpClient = new OkHttpClient();

        imageViewCalendar.setOnClickListener(v -> showDatePickerDialog());
        searchTrains.setOnClickListener(view -> StartNextActivity());
    }

    private void showDatePickerDialog() {
        // Get current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new DatePickerDialog instance
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Set the selected date to the EditText
                        StringBuilder dateStringBuilder = new StringBuilder();
                        if (monthOfYear < 9) {
                            dateStringBuilder.append(year).append("-0").append(monthOfYear + 1).append("-").append(dayOfMonth);
                        } else {
                            dateStringBuilder.append(year).append("-").append(monthOfYear + 1).append("-").append(dayOfMonth);
                        }
                        editTextDate.setText(dateStringBuilder.toString());
                    }
                }, year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    private void makeApiRequest(Request request, final ApiResponseListener listener) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    listener.onFailure(new Exception(response.toString()));
                }

                assert response.body() != null;
                try {
                    JSONObject responseBody = new JSONObject(response.body().string());
                    listener.onSuccess(responseBody);
                } catch (JSONException e) {
                    listener.onFailure(e);
                }
            }
        });
    }

    interface ApiResponseListener {
        void onSuccess(JSONObject response);
        void onFailure(Exception e);
    }

    private void SearchTrainsBetweenStationOnDate() {
        // verify data
        // skipping due to time constraints

        // get data
//        String fromStationCode = editTextSourceStation.getText().toString();
//        String toStationCode = editTextDestinationStation.getText().toString();
//        String dateOfJourney = editTextDate.getText().toString();
        String fromStationCode = "BVI";
        String toStationCode = "NDLS";
        String dateOfJourney = "2024-04-22";

        String urlWithArgs = "https://irctc1.p.rapidapi.com/api/v3/trainBetweenStations" +
                "?fromStationCode=" + fromStationCode +
                "&toStationCode=" + toStationCode +
                "&dateOfJourney=" + dateOfJourney;

        // call api request object
        Request request = new Request.Builder()
                .url("https://irctc1.p.rapidapi.com/api/v3/trainBetweenStations?fromStationCode=BVI&toStationCode=NDLS&dateOfJourney=2024-04-22")
                .get()
                .addHeader("X-RapidAPI-Key", "ec8fcd17ebmshffdea8d2f2b56a0p147898jsn64a4bb596d83")
                .addHeader("X-RapidAPI-Host", "irctc1.p.rapidapi.com")
                .build();

        // Make the API request
        makeApiRequest(request, new ApiResponseListener() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    JSONArray trainList = response.getJSONArray("data");
                    trainDetailsArray = new ArrayList<>();

                    for (int i = 0; i< trainList.length(); i++) {
                        JSONObject trainDetailJSON = trainList.getJSONObject(i);

                        // to find fare and class available in train
                        // and to add to trainDetailJSON object
                        getFalseFareInformation(trainDetailJSON);
                    }

                    StartNextActivity();
                } catch (JSONException e) {
                    Log.e("JSON Exception", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("API Request", "Failed: " + e.getMessage());
            }
        });
    }

    private void getFalseFareInformation(JSONObject trainDetailJSON) throws JSONException {
        double distance = trainDetailJSON.getDouble("local_train_from_sta");
        double multiplier1AC = 3;
        double multiplier2AC = 2;
        double multiplier3AC = 1.3;
        double multiplierSL = 0.5;
        double finalCost = 0;

        JSONArray trainClasses = trainDetailJSON.getJSONArray("class_type");
        TrainClassPaletteItem[] trainClassPaletteItems = new TrainClassPaletteItem[trainClasses.length()];

        for (int i=0; i< trainClasses.length(); i++) {
            String currentTrainClass = trainClasses.getString(i);
            switch (currentTrainClass) {
                case "1A":
                    finalCost = distance * multiplier1AC;
                    finalCost = (int) finalCost;
                    trainClassPaletteItems[i] = new TrainClassPaletteItem("1A", String.valueOf(finalCost), "70", "Updated just now.");
                    break;
                case "2A":
                    finalCost = distance * multiplier2AC;
                    finalCost = (int) finalCost;
                    trainClassPaletteItems[i] = new TrainClassPaletteItem("2A", String.valueOf(finalCost), "70", "Updated just now.");
                    break;
                case "3A":
                    finalCost = distance * multiplier3AC;
                    finalCost = (int) finalCost;
                    trainClassPaletteItems[i] = new TrainClassPaletteItem("3A", String.valueOf(finalCost), "70", "Updated just now.");
                    break;
                case "SL":
                    finalCost = distance * multiplierSL;
                    finalCost = (int) finalCost;
                    trainClassPaletteItems[i] = new TrainClassPaletteItem("SL", String.valueOf(finalCost), "70", "Updated just now.");
                    break;
                default:
                    finalCost = distance;
                    finalCost = (int) finalCost;
                    trainClassPaletteItems[i] = new TrainClassPaletteItem("3A", String.valueOf(finalCost), "70", "Updated just now.");
                    break;
            }
        }
        trainDetailsArray.add(new TrainDetails(trainDetailJSON, trainClassPaletteItems));
    }

    private void getFareInformation(JSONObject trainDetailJSON) throws JSONException {
        String trainNumber = trainDetailJSON.getString("train_number");
        String fromStation = trainDetailJSON.getString("train_src");
        String toStation = trainDetailJSON.getString("train_dstn");

        String urlWithArgs2 = "https://irctc1.p.rapidapi.com/api/v2/getFare" +
                "?trainNo=" + trainNumber +
                "&fromStationCode=" + fromStation +
                "&toStationCode=" + toStation;

        Request request2 = new Request.Builder()
                .url(urlWithArgs2)
                .get()
                .addHeader("X-RapidAPI-Key", "ec8fcd17ebmshffdea8d2f2b56a0p147898jsn64a4bb596d83")
                .addHeader("X-RapidAPI-Host", "irctc1.p.rapidapi.com")
                .build();

        // Make the fare information API request
        makeApiRequest(request2, new ApiResponseListener() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    JSONArray trainDetailsArray = response.getJSONArray("data");

                    for (int i=0; i< trainDetailsArray.length(); i++) {
                        JSONObject trainDetail = trainDetailsArray.getJSONObject(i);
                    }
//                    JSONObject trainClassArray = response.getJSONObject("data");
//                    Iterator<String> trainClassArrayIterator = trainClassArray.keys();
//
//                    int trailClassCount = 0;
//                    while (trainClassArrayIterator.hasNext()) {
//                        String i = trainClassArrayIterator.next();
//                        trailClassCount += trainClassArray.getJSONArray(i).length();
//                    }
//                    if (trailClassCount == 0) {
//                        onFailure(new Exception("Trail class count is 0"));
//                        return; // Exit the onSuccess method
//                    }
//                    TrainClassPaletteItem[] trainClassPaletteItems = new TrainClassPaletteItem[trailClassCount];
//
//                    trainClassArrayIterator = trainClassArray.keys();
//                    while (trainClassArrayIterator.hasNext()) {
//                        String i = trainClassArrayIterator.next();
//                        JSONArray temp = trainClassArray.getJSONArray(i);
//                        for (int j=0; j< temp.length(); j++) {
//                            JSONObject temp2 = temp.getJSONObject(j);
//                            String seatClass = temp2.getString("classType");
//                            String seatPrice = temp2.getString("fare");
//                            String seatsAvailable = "69";
//                            String updatedTimings = "Updated just now";
//                            trainClassPaletteItems[j] = new TrainClassPaletteItem(seatClass, seatPrice, seatsAvailable, updatedTimings);
//                        }
//                    }

                    trainDetailsArray.add(new TrainDetails(trainDetailJSON, trainClassPaletteItems));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                TrainClassPaletteItem[] trainClassPaletteItems = new TrainClassPaletteItem[1];
                trainClassPaletteItems[0] = new TrainClassPaletteItem("3A", "Rs 1529", "156", "Updated -1 mins ago");
                trainDetailsArray.add(new TrainDetails(trainDetailJSON, trainClassPaletteItems));
            }
        });
    }


    private void StartNextActivity() {
        Intent intent = new Intent(this, ActivityListTrain.class);
        intent.putExtra("trainDetailsArray", trainDetailsArray);
        startActivity(intent);
    }
}
