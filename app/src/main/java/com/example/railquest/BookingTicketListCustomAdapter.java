package com.example.railquest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingTicketListCustomAdapter extends RecyclerView.Adapter<BookingTicketListCustomAdapter.ViewHolder> {
    private List<BookingTicket> bookingTickets;
    private Context context;

    public BookingTicketListCustomAdapter(Context context, List<BookingTicket> bookingTickets) {
        this.context = context;
        this.bookingTickets = bookingTickets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_ticket_list, parent, false);
        return new ViewHolder(view, bookingTickets);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingTicket bookingTicket = bookingTickets.get(position);
        holder.bind(bookingTicket);
    }

    @Override
    public int getItemCount() {
        return bookingTickets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTrainName, txtTrainNumber, txtStartTimeDate, txtBoardingStationName, txtDestinationStationName, txtPNR, txtBookingDate;
        Context context;
        List<BookingTicket> bookingTickets;

        public ViewHolder(@NonNull View itemView, List<BookingTicket> bookingTickets) {
            super(itemView);
            context = itemView.getContext();
            txtTrainName = itemView.findViewById(R.id.txtTrainName);
            txtTrainNumber = itemView.findViewById(R.id.txtTrainNumber);
            txtStartTimeDate = itemView.findViewById(R.id.txtStartTimeDate);
            txtBoardingStationName = itemView.findViewById(R.id.txtBoardingStationName);
            txtDestinationStationName = itemView.findViewById(R.id.txtDestinationStationName);
            txtPNR = itemView.findViewById(R.id.txtPNR);
            txtBookingDate = itemView.findViewById(R.id.txtBookingDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        BookingTicket bookingTicket = bookingTickets.get(position);

                        Intent intent = new Intent(context, Booked_Details_Complete.class);

                        // add string extras

                    }
                }
            });
        }

        public void bind(BookingTicket bookingTicket) {
            txtTrainName.setText(bookingTicket.getTrainName());
            txtTrainNumber.setText(bookingTicket.getTrainNumber());
            txtStartTimeDate.setText(bookingTicket.getStartTimeDate());
            txtBoardingStationName.setText(bookingTicket.getBoardingStationName());
            txtDestinationStationName.setText(bookingTicket.getDestinationStationName());
            txtPNR.setText(bookingTicket.getPnr());
            txtBookingDate.setText(bookingTicket.getBookingDate());
        }
    }
}
