package com.example.railquest;

public class TrainClassPaletteItem {
    private String seatClass;
    private String seatPrice;
    private String seatsAvailable;
    private String updatedTimings;

    public TrainClassPaletteItem(String seatClass, String seatPrice, String seatsAvailable, String updatedTimings) {
        this.seatClass = seatClass;
        this.seatPrice = seatPrice;
        this.seatsAvailable = seatsAvailable;
        this.updatedTimings = updatedTimings;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(String seatPrice) {
        this.seatPrice = seatPrice;
    }

    public String getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(String seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getUpdatedTimings() {
        return updatedTimings;
    }

    public void setUpdatedTimings(String updatedTimings) {
        this.updatedTimings = updatedTimings;
    }
}
