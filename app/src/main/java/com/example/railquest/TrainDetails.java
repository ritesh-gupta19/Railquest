package com.example.railquest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TrainDetails implements Serializable {

    private String TrainName;
    private String TrainNumber;
    private String StartTimeDate;
    private String EndTimeDate;
    private String TimeDuration;
    private String BoardingStationName;
    private String DestinationStationName;
    private TrainClassPaletteItem[] trainClasses;

    public TrainDetails(String trainName, String trainNumber, String startTimeDate, String endTimeDate, String timeDuration, String boardingStationName, String destinationStationName, TrainClassPaletteItem[] trainClasses) {
        TrainName = trainName;
        TrainNumber = trainNumber;
        StartTimeDate = startTimeDate;
        EndTimeDate = endTimeDate;
        TimeDuration = timeDuration;
        BoardingStationName = boardingStationName;
        DestinationStationName = destinationStationName;
        this.trainClasses = trainClasses;
    }

    public TrainDetails(JSONObject traindetails, TrainClassPaletteItem[] trainClasses) {
        try {
            this.TrainNumber = traindetails.getString("train_number");
            this.TrainName = traindetails.getString("train_name");
            this.StartTimeDate = calculateStartTimeDate(traindetails.getString("from_std"), Integer.parseInt(traindetails.getString("from_day")));
            this.EndTimeDate = calculateEndTimeDate(traindetails.getString("to_std"), Integer.parseInt(traindetails.getString("to_day")));
            this.TimeDuration = traindetails.getString("duration");
            this.BoardingStationName = traindetails.getString("train_src");
            this.DestinationStationName = traindetails.getString("train_dstn");
            this.trainClasses = trainClasses;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String trainName) {
        TrainName = trainName;
    }

    public String getTrainNumber() {
        return TrainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        TrainNumber = trainNumber;
    }

    public String getStartTimeDate() {
        return StartTimeDate;
    }

    public void setStartTimeDate(String startTimeDate) {
        StartTimeDate = startTimeDate;
    }

    public String getEndTimeDate() {
        return EndTimeDate;
    }

    public void setEndTimeDate(String endTimeDate) {
        EndTimeDate = endTimeDate;
    }

    public String getTimeDuration() {
        return TimeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        TimeDuration = timeDuration;
    }

    public String getBoardingStationName() {
        return BoardingStationName;
    }

    public void setBoardingStationName(String boardingStationName) {
        BoardingStationName = boardingStationName;
    }

    public String getDestinationStationName() {
        return DestinationStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        DestinationStationName = destinationStationName;
    }

    public TrainClassPaletteItem[] getTrainClasses() {
        return trainClasses;
    }

    public void setTrainClasses(TrainClassPaletteItem[] trainClasses) {
        this.trainClasses = trainClasses;
    }

    @Override
    public String toString() {
        return "TrainDetails{" +
                "TrainName='" + TrainName + '\'' +
                ", TrainNumber='" + TrainNumber + '\'' +
                ", StartTimeDate='" + StartTimeDate + '\'' +
                ", EndTimeDate='" + EndTimeDate + '\'' +
                ", TimeDuration='" + TimeDuration + '\'' +
                ", BoardingStationName='" + BoardingStationName + '\'' +
                ", DestinationStationName='" + DestinationStationName + '\'' +
                '}';
    }

    private static String calculateStartTimeDate(String fromStd, int fromDay) {
        // Get current date
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(fromStd.split(":")[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(fromStd.split(":")[1]));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // Add days if needed
        if (fromDay != 0) {
            cal.add(Calendar.DATE, fromDay);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd MMM", Locale.getDefault());
        return sdf.format(cal.getTime());
    }

    private static String calculateEndTimeDate(String toStd, int toDay) {
        // Get current date
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(toStd.split(":")[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(toStd.split(":")[1]));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        // Add days if needed
        if (toDay != 0) {
            cal.add(Calendar.DATE, toDay);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd MMM", Locale.getDefault());
        return sdf.format(cal.getTime());
    }
}
