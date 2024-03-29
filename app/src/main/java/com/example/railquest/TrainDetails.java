package com.example.railquest;

public class TrainDetails {

    private String TrainName;
    private String TrainNumber;
    private String StartTimeDate;
    private String EndTimeDate;
    private String TimeDuration;
    private String BoardingStationName;
    private String DestinationStationName;

    public TrainDetails(String trainName, String trainNumber, String startTimeDate, String endTimeDate, String timeDuration, String boardingStationName, String destinationStationName) {
        TrainName = trainName;
        TrainNumber = trainNumber;
        StartTimeDate = startTimeDate;
        EndTimeDate = endTimeDate;
        TimeDuration = timeDuration;
        BoardingStationName = boardingStationName;
        DestinationStationName = destinationStationName;
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
}
