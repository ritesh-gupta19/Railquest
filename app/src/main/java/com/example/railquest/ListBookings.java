package com.example.railquest;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListBookings extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookingTicketListCustomAdapter adapter;
    private List<BookingTicket> bookingTickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_bookings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewBookingsList);
        bookingTickets = new ArrayList<>();

        // Sample data population
//        bookingTickets.add(new BookingTicket("Kyq Gtnr Express", "#15077", "8:00 PM, 30 Apr", "Kamakhya (KYQ)", "Basti (BST)", "6245358422", "18 Mar: 2024"));
//        bookingTickets.add(new BookingTicket("Sample Train 1", "#12345", "9:00 AM, 1 May", "Station A", "Station B", "9876543210", "19 Mar: 2024"));
//        bookingTickets.add(new BookingTicket("Sample Train 2", "#67890", "2:00 PM, 2 May", "Station X", "Station Y", "1357924680", "20 Mar: 2024"));

        adapter = new BookingTicketListCustomAdapter(this, bookingTickets);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchTicketsFromDataStore();
    }

    private void fetchTicketsFromDataStore() {
        // Assuming you have a method to fetch tickets from the data store
        // This method should asynchronously fetch tickets and update the bookingTickets list
        // For example:
        DBHandler.fetchTickets(new DBHandler.TicketsFetchListener() {
            @Override
            public void onTicketsFetched(List<Ticket> tickets) {
                // Clear the existing bookingTickets list
                bookingTickets.clear();
                // Add all fetched tickets to the bookingTickets list
                for (Ticket ticket : tickets) {
                    addBookingTicket(ticket);
                }
                // Notify the adapter that the dataset has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                // Handle error while fetching tickets
            }
        });
    }

    public void addBookingTicket(Ticket ticket) {
        bookingTickets.add(new BookingTicket(ticket));
        adapter.notifyDataSetChanged();
    }
}