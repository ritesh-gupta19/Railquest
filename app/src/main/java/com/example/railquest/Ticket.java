package com.example.railquest;

import java.io.Serializable;
import java.util.List;

import javax.xml.transform.sax.SAXResult;

public class Ticket implements Serializable {
    private String TrainName;
    private String TrainNumber;
    private String pnr;
    private String StartTimeDate;
    private String EndTimeDate;
    private String TimeDuration;
    private String BoardingStationName;
    private String DestinationStationName;
    private String BookingTier;
    private String BookingQuota;
    private String SeatPrice;
    private List<String> TravellerName_age_gender;
    private List<String> travellerBerth;
    private static int PNR = 100000;

    public Ticket() {}

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

    public String getBookingTier() {
        return BookingTier;
    }

    public void setBookingTier(String bookingTier) {
        BookingTier = bookingTier;
    }

    public String getBookingQuota() {
        return BookingQuota;
    }

    public void setBookingQuota(String bookingQuota) {
        BookingQuota = bookingQuota;
    }

    public String getSeatPrice() {
        return SeatPrice;
    }

    public void setSeatPrice(String seatPrice) {
        SeatPrice = seatPrice;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr() {
        this.pnr = String.valueOf(generatePNR());
    }

    public List<String> getTravellerName_age_gender() {
        return TravellerName_age_gender;
    }

    public void setTravellerName_age_gender(List<String> TravellerName_age_gender) {
        this.TravellerName_age_gender = TravellerName_age_gender;
    }

    public List<String> getTravellerBerth() {
        return travellerBerth;
    }

    public void setTravellerBerth(List<String> travellerBerth) {
        this.travellerBerth = travellerBerth;
    }

    public static int generatePNR() {
        return PNR++;
    }
}
