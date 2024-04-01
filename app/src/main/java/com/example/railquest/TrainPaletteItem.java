package com.example.railquest;

public class TrainPaletteItem {
    private String trainName;
    private String trainNumber;
    private String startTimeDate;
    private String endTimeDate;
    private String timeDuration;
    private String boardingStationName;
    private String destinationStationName;
    private TrainClassPaletteItem[] trainClasses;

    public TrainPaletteItem(String trainName, String trainNumber, String startTimeDate, String endTimeDate, String timeDuration, String boardingStationName, String destinationStationName, TrainClassPaletteItem[] trainClasses) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.startTimeDate = startTimeDate;
        this.endTimeDate = endTimeDate;
        this.timeDuration = timeDuration;
        this.boardingStationName = boardingStationName;
        this.destinationStationName = destinationStationName;
        this.trainClasses = trainClasses;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getStartTimeDate() {
        return startTimeDate;
    }

    public String getEndTimeDate() {
        return endTimeDate;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public String getBoardingStationName() {
        return boardingStationName;
    }

    public String getDestinationStationName() {
        return destinationStationName;
    }

    public TrainClassPaletteItem[] getTrainClasses() {
        return trainClasses;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setStartTimeDate(String startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public void setEndTimeDate(String endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public void setBoardingStationName(String boardingStationName) {
        this.boardingStationName = boardingStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        this.destinationStationName = destinationStationName;
    }

    public void setTrainClasses(TrainClassPaletteItem[] trainClasses) {
        this.trainClasses = trainClasses;
    }
}
