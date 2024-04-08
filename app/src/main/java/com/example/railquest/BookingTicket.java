package com.example.railquest;

public class BookingTicket {
    private String trainName;
    private String trainNumber;
    private String startTimeDate;
    private String boardingStationName;
    private String destinationStationName;
    private String pnr;
    private String bookingDate;
    private Ticket ticket;

    public BookingTicket(String trainName, String trainNumber, String startTimeDate, String boardingStationName, String destinationStationName, String pnr, String bookingDate) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.startTimeDate = startTimeDate;
        this.boardingStationName = boardingStationName;
        this.destinationStationName = destinationStationName;
        this.pnr = pnr;
        this.bookingDate = bookingDate;
    }

    public BookingTicket(Ticket ticket) {
        this.trainName = ticket.getTrainName();
        this.trainNumber = ticket.getTrainNumber();
        this.startTimeDate = ticket.getStartTimeDate();
        this.boardingStationName = ticket.getBoardingStationName();
        this.destinationStationName = ticket.getDestinationStationName();
        this.pnr = ticket.getPnr();
        this.bookingDate = "9 Apr: 2024";
        this.ticket = ticket;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(String startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public String getBoardingStationName() {
        return boardingStationName;
    }

    public void setBoardingStationName(String boardingStationName) {
        this.boardingStationName = boardingStationName;
    }

    public String getDestinationStationName() {
        return destinationStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        this.destinationStationName = destinationStationName;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
