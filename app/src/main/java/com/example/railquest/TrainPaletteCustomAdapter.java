package com.example.railquest;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraExtensionSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainPaletteCustomAdapter extends RecyclerView.Adapter<TrainPaletteCustomAdapter.ViewHolder> {

    private List<TrainDetails> trainDetailsItems;
    private Context context;

    public TrainPaletteCustomAdapter(Context context, List<TrainDetails> TrainDetailsItem) {
        this.context = context;
        this.trainDetailsItems = TrainDetailsItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainpalette, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainDetails item = trainDetailsItems.get(position);

        // Bind data to views in trainPalette.xml
        holder.txtTrainName.setText(item.getTrainName());
        holder.txtTrainNumber.setText(item.getTrainNumber());
        holder.txtStartTimeDate.setText(item.getStartTimeDate());
        holder.txtEndTimeDate.setText(item.getEndTimeDate());
        holder.txtTimeDuration.setText(item.getTimeDuration());
        holder.txtBoardingStationName.setText(item.getBoardingStationName());
        holder.txtDestinationStationName.setText(item.getDestinationStationName());

        // Assuming llTrainClasses is the LinearLayout inside trainPalette.xml
        LinearLayout llTrainClasses = holder.itemView.findViewById(R.id.llTrainClasses);

        // Clear the LinearLayout before adding new views
        llTrainClasses.removeAllViews();

        // Assuming trainClasses is a List<String> containing data for each train class
        for (TrainClassPaletteItem trainClass : item.getTrainClasses()) {
            // Inflating trainclasspalette.xml for each train class
            View trainClassView = LayoutInflater.from(context).inflate(R.layout.trainclasspalette, null);

            // Accessing TextViews inside trainclasspalette.xml
            TextView txtSeatClass = trainClassView.findViewById(R.id.txtSeatClass);
            TextView txtSeatPrice = trainClassView.findViewById(R.id.txtSeatPrice);
            TextView txtSeatsAvailable = trainClassView.findViewById(R.id.txtSeatsAvailale);
            TextView txtUpdatedTiming = trainClassView.findViewById(R.id.txtUpdatedTiming);

            // Setting data to TextViews
            txtSeatClass.setText(trainClass.getSeatClass());
            txtSeatPrice.setText(trainClass.getSeatPrice());
            txtSeatsAvailable.setText(trainClass.getSeatsAvailable());
            txtUpdatedTiming.setText(trainClass.getUpdatedTimings());

            // Add trainclasspalette.xml layout to the LinearLayout
            llTrainClasses.addView(trainClassView);
            trainClassView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Booking_Page_User_Details_Trip.class);

                    // variables to send as extras
                    intent.putExtra("trainName", holder.txtTrainName.getText().toString());
                    intent.putExtra("trainNumber", holder.txtTrainNumber.getText().toString());
                    intent.putExtra("startTimeDate", holder.txtStartTimeDate.getText().toString());
                    intent.putExtra("endTimeDate", holder.txtEndTimeDate.getText().toString());
                    intent.putExtra("timeDuration", holder.txtTimeDuration.getText().toString());
                    intent.putExtra("boardingStation", holder.txtBoardingStationName.getText().toString());
                    intent.putExtra("destinationStation", holder.txtDestinationStationName.getText().toString());
                    intent.putExtra("bookingTier", txtSeatClass.getText().toString());
                    intent.putExtra("bookingQuota", txtUpdatedTiming.getText().toString());
                    intent.putExtra("availableSeats", txtSeatsAvailable.getText().toString());
                    intent.putExtra("seatPrice", txtSeatPrice.getText().toString());

                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return trainDetailsItems.size();
    }

    public List<TrainDetails> getTrainDetailsItems() {
        return trainDetailsItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTrainName, txtTrainNumber, txtStartTimeDate, txtEndTimeDate, txtTimeDuration, txtBoardingStationName, txtDestinationStationName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTrainName = itemView.findViewById(R.id.txtTrainName);
            txtTrainNumber = itemView.findViewById(R.id.txtTrainNumber);
            txtStartTimeDate = itemView.findViewById(R.id.txtStartTimeDate);
            txtEndTimeDate = itemView.findViewById(R.id.txtEndTimeDate);
            txtTimeDuration = itemView.findViewById(R.id.txtTimeDuration);
            txtBoardingStationName = itemView.findViewById(R.id.txtBoardingStationName);
            txtDestinationStationName = itemView.findViewById(R.id.txtDestinationStationName);
        }
    }
}
