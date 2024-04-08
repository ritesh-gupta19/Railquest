package com.example.railquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstEntryPage extends AppCompatActivity {

    private Button book_tickets;
    private Button train_details;
    private Button view_bookings;
    private Button PNR_status;
    private Button cancel_tickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_entry_page);

        book_tickets = findViewById(R.id.bookTicketsButton);
        train_details = findViewById(R.id.getTrainDetailsButton);
        view_bookings = findViewById(R.id.getBookedTicketsButton);
        PNR_status = findViewById(R.id.retrievePNRButton);
        cancel_tickets = findViewById(R.id.cancelTicketsButton);
    }

    public void onClickBookTickets(View v) {
        //functionality for book tickets button....
        Intent intent = new Intent(this, search_page.class); // Replace NextActivity with the name of your next activity
        startActivity(intent);
    }

    public void onClickTrainDetails(View v) {
        //functionality for get train details button....
    }

    public void onClickYourBookings(View v) {
        // temporary functionality for dummy trains view
        Intent intent = new Intent(this, ListBookings.class);
        startActivity(intent);

        //functionality for your bookings button....
    }

    public void onClickPNRStatus(View v) {
        //functionality for check PNR status button....
        Intent intent = new Intent(this, PNR_status.class); // Replace NextActivity with the name of your next activity
        startActivity(intent);
    }

    public void onClickCancelTickets(View v) {
        //functionality for cancel tickets button....
//        public void openNewTraveller(View v) {
            Intent intent = new Intent(this, Booking_Page_User_Details_Trip.class);
            startActivity(intent);
//        }
    }
}